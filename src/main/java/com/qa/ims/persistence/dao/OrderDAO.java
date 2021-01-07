package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order>{
	
	public static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Reads all orders from the database
	 * 
	 * @return A list of orders
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT oi.order_id, customer_id," +
				" item_id, quantity FROM orders o JOIN order_item oi ON" + " o.order_id=oi.order_id;");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			orders = collapseOrders(orders);
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT oi.order_id, customer_id," +
						" item_id, quantity FROM orders o JOIN order_item oi ON" + 
						" o.order_id=oi.order_id WHERE oi.order_id = "
						+ "(SELECT MAX(order_id) FROM orders);");) {
			List<Order> orders = new ArrayList<>();
			while(resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			orders = collapseOrders(orders);
			Order order = orders.get(0);
			return order;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Order readOrder(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT oi.order_id, customer_id," +
						" item_id, quantity FROM orders o JOIN order_item oi ON" + 
						" o.order_id=oi.order_id WHERE oi.order_id = " + id + ";");) {
			List<Order> orders = new ArrayList<>();
			while(resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			orders = collapseOrders(orders);
			Order order = orders.get(0);
			return order;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Creates an order in the database and a record in order_item for each item in the order
	 * 
	 * @param order - takes in an order object. Id will be ignored.
	 */
	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orders(customer_id) VALUES("+order.getCustomer_id()+");");
			ResultSet resultSet = statement.executeQuery("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
			resultSet.next();
			Long order_id = resultSet.getLong("order_id");
			order.setOrder_id(order_id);
			createOrderItem(order, statement);
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates an order in the database
	 * Currently deletes all order_item entries for the old order and creates new ones
	 * so updated order must include all items for that order
	 * 
	 * @param order - takes in an order object, the id field will be used to
	 *                update that order and the order_item table in the database.
	 * @return
	 */
	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE FROM order_item WHERE order_id = " + order.getOrder_id());
			statement.executeUpdate("UPDATE orders SET customer_id = " + order.getCustomer_id() + 
					" WHERE order_id = " + order.getOrder_id()+";");
			createOrderItem(order, statement);
			return readOrder(order.getOrder_id());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE FROM order_item WHERE order_id = " + id);
			return statement.executeUpdate("DELETE FROM orders WHERE order_id = " + id);
			
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		
		long order_id = resultSet.getLong("order_id");
		long customer_id = resultSet.getLong("customer_id");
		long item_id = resultSet.getLong("item_id");
		int quantity = resultSet.getInt("quantity");
		List<Item> items = new ArrayList<>();
		items.add(new Item(item_id, quantity));
		return new Order(order_id, customer_id, items);
	}
	
	public float getOrderCost(Order order) {
		float cost = 0f;
		for(Item i: order.getItems()) {
			Long item_id = i.getId();
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();) {
				ResultSet resultSet = statement.executeQuery("SELECT quantity, price FROM items i JOIN order_item oi "
						+ "ON i.item_id=oi.item_id WHERE oi.item_id = " + item_id + ";");
				resultSet.next();
				cost += resultSet.getInt("quantity") * resultSet.getFloat("price");
			} catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
				return 0;
			}
		}
		return cost;
	}
	
	// Iterate through the orders list and add items from orders with the same id
	// to the first order with that id, then delete all other orders with that id
	private List<Order> collapseOrders(List<Order> orders){
		
		List<Order> collapsedOrders = new ArrayList<>();
		Set<Long> order_ids = new HashSet<>();
		
		for(Order o: orders) {
			order_ids.add(o.getOrder_id());
		}
		
		for(long order_id: order_ids) {
			List<Order> singleOrder = new ArrayList<>();
			singleOrder = orders.stream().filter(o -> o.getOrder_id() == order_id).collect(Collectors.toList());
			List<Item> items = new ArrayList<>();
			for(Order item_order: singleOrder) {
				Item item = item_order.getItems().get(0);
				items.add(item);
			}
			Order order = new Order(order_id, singleOrder.get(0).getCustomer_id(), items);
			collapsedOrders.add(order);
		}
		return collapsedOrders;
	}
	
	// Creates entries in the order_item table for an order
	private void createOrderItem(Order order, Statement statement) {
		String insertStart = "INSERT INTO order_item(item_id, order_id, quantity) VALUES(";
		for(Item i: order.getItems()) {
			try {
			statement.executeUpdate(insertStart+i.getId()+","+order.getOrder_id()+","+i.getQuantity()+");");
			}
			catch(SQLException e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
		}
	}
}
