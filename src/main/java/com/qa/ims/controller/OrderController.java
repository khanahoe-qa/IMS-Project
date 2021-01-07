package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}
	
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
			LOGGER.info("Total cost of order = "+orderDAO.getOrderCost(order));
		}
		return orders;
	}

	@Override
	public Order create() {
		LOGGER.info("\nPlease enter the customer id");
		long customer_id = utils.getLong();
		boolean cont = true;
		List<Item> items = new ArrayList<>();
		while(cont) {
			
			LOGGER.info("\nPlease enter an item id");
			long item_id = utils.getLong();
			LOGGER.info("\nPlease enter the quantity");
			int quantity = utils.getInt();
			Item item = new Item(item_id, quantity);
			items.add(item);
			boolean invalid = true;
			while(invalid) {
				LOGGER.info("\nAdd another item to the order (Y/N)?");
				String choice = utils.getString();
				if(choice.toLowerCase().equals("n")) {
					invalid = false;
					cont = false;
				}
				else if(choice.toLowerCase().equals("y")) {
					invalid = false;
				}
				else {
					LOGGER.info("\nPlease enter y or n.");
				}
			}
		}
		Order order = new Order(customer_id, items);
		Order created = orderDAO.create(order);
		LOGGER.info("\nOrder created");
		return created;
	}

	@Override
	public Order update() {
		LOGGER.info("\nPlease enter the ID of the order you would like to update");
		long order_id = utils.getLong();
		Order order = orderDAO.readOrder(order_id);
		if(order == null) {
			LOGGER.info("\nOrder not found");
			return order;
		}
		OrderSelection selection = null;
		LOGGER.info("\nWhat would you like to do with the order");
		OrderSelection.printSelection();
		selection = OrderSelection.getSelection(utils);
		switch (selection) {
		case ADD:
			order = addItem(order);
			break;
		case REMOVE:
			order = removeItem(order);
			break;
		case REMAKE:
			order = remakeOrder(order);
			break;
		default:
			break;
		}
		order = orderDAO.update(order);
		return order;
	}

	@Override
	public int delete() {
		LOGGER.info("\nPlease enter the id of the order you would like to delete");
		Long id = utils.getLong();
		LOGGER.info("\nOrder "+id+" deleted.");
		return orderDAO.delete(id);
	}
	
	private Order addItem(Order order) {
		LOGGER.info("\nPlease enter an item id");
		long item_id = utils.getLong();
		LOGGER.info("\nPlease enter the quantity");
		int quantity = utils.getInt();
		Item item = new Item(item_id, quantity);
		order.addItem(item);
		return order;
	}
	
	private Order removeItem(Order order) {
		LOGGER.info("\nPlease enter an item id");
		long item_id = utils.getLong();
		order.removeItem(item_id);
		return order;
	}
	
	private Order remakeOrder(Order order) {
		LOGGER.info("\nPlease enter the customer id");
		long customer_id = utils.getLong();
		boolean cont = true;
		List<Item> items = new ArrayList<>();
		while(cont) {
			
			LOGGER.info("\nPlease enter an item id");
			long item_id = utils.getLong();
			LOGGER.info("\nPlease enter the quantity");
			int quantity = utils.getInt();
			Item item = new Item(item_id, quantity);
			items.add(item);
			boolean invalid = true;
			while(invalid) {
				LOGGER.info("\nAdd another item to the order (Y/N)?");
				String choice = utils.getString();
				if(choice.toLowerCase().equals("n")) {
					invalid = false;
					cont = false;
				}
				else if(choice.toLowerCase().equals("y")) {
					invalid = false;
				}
				else {
					LOGGER.info("\nPlease enter y or n.");
				}
			}
		}
		order.setCustomer_id(customer_id);
		order.setItems(items);
		return order;
	}
}
