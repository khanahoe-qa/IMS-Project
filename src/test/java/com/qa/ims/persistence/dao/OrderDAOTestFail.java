package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTestFail {

	private final OrderDAO DAO = new OrderDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("uname", "pwd");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testReadAllFail() {
		assertEquals(new ArrayList<>(), DAO.readAll());
	}
	
	@Test
	public void testReadLatestFail() {
		assertEquals(null, DAO.readLatest());
	}
	
	@Test
	public void testReadOrderFail() {
		assertEquals(null, DAO.readOrder(1L));
	}
	
	@Test
	public void testCreateFail() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, 3));
		Order order = new Order(2L, 1L, items);
		assertEquals(null, DAO.create(order));
	}
	
	@Test
	public void testUpdateFail() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, 1));
		Order order = new Order(1L, 1L, items);
		assertEquals(null, DAO.update(order));
	}
	
	@Test
	public void testDeleteFail() {
		assertEquals(0, DAO.delete(1L));
	}
}
