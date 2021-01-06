package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {

	private final ItemDAO DAO = new ItemDAO();

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
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "shoes", 12, 22.50f));
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Item(1L, "shoes", 12, 22.50f), DAO.readLatest());
	}
	
	@Test
	public void testReadItem() {
		assertEquals(new Item(1L, "shoes", 12, 22.50f), DAO.readItem(1L));
	}
	
	@Test
	public void testReadItemFail() {
		assertEquals(null, DAO.readItem(99L));
	}
	
	@Test
	public void testCreate() {
		final Item created = new Item(2L, "hat", 99, 12.01f);
		assertEquals(created, DAO.create(created));
	}
	
	@Test
	public void testCreateFail() {
		final Item created = new Item(2L, "hat", 99, 9999999.01f);
		assertEquals(null, DAO.create(created));
	}
	
	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "hat", 99, 12.01f);
		assertEquals(updated, DAO.update(updated));
	}
	
	@Test
	public void testDelete() {
		final Item created = new Item(2L, "hat", 99, 12.01f);
		DAO.create(created);
		assertEquals(1, DAO.delete(2L));
	}
	
	@Test
	public void testDeleteFail() {
		assertEquals(0, DAO.delete(1L));
	}
}