package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;


public class ItemDAOFail {

	private final ItemDAO DAO = new ItemDAO();
	
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
	public void testUpdateFail() {
		final Item updated = new Item(1L, "hat", 99, 12.01f);
		assertEquals(null, DAO.update(updated));
	}
}
