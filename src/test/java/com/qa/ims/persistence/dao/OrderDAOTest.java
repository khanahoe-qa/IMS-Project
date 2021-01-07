package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	
	private final OrderDAO DAO = new OrderDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "root");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, 7));
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, items));
		assertEquals(orders, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, 7));
		assertEquals(new Order(1L, 1L, items), DAO.readLatest());
	}
	
	@Test
	public void testReadOrder() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, 7));
		assertEquals(new Order(1L, 1L, items), DAO.readOrder(1L));
	}
	
	@Test
	public void testCreate() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, 3));
		Order order = new Order(2L, 1L, items);
		assertEquals(order, DAO.create(order));
	}
	
	@Test
	public void testUpdate() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, 1));
		Order order = new Order(1L, 1L, items);
		assertEquals(order, DAO.update(order));
	}
	
	@Test
	public void testUpdateFail() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, 1));
		Order order = new Order(99L, 1L, items);
		assertEquals(null, DAO.update(order));
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1L));
	}
	
}
