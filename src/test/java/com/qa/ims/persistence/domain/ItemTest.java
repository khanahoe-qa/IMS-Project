package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ItemTest {

	@Test
	public void testSetId() {
		Item before = new Item("Widget", 1, 1.00f);
		Item after = new Item(2l, "Widget", 1, 1.00f);
		before.setId(2l);
		assertEquals(before, after);
	}
	
	@Test
	public void testSetName() {
		Item before = new Item("Widget", 1, 1.00f);
		Item after = new Item("Gadget", 1, 1.00f);
		before.setName("Gadget");
		assertEquals(before, after);
	}
	
	@Test
	public void testSetStock() {
		Item before = new Item("Widget", 1, 1.00f);
		Item after = new Item("Widget", 99, 1.00f);
		before.setStock(99);
		assertEquals(before, after);
	}
	
	@Test
	public void testSetPrice() {
		Item before = new Item("Widget", 1, 1.00f);
		Item after = new Item("Widget", 1, 9.99f);
		before.setPrice(9.99f);
		assertEquals(before, after);
	}
	
	@Test
	public void testSetQuantity() {
		Item before = new Item(1l, 1);
		Item after = new Item(1l, 99);
		before.setQuantity(99);
		assertEquals(before, after);
	}
}
