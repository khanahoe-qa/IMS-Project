package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class OrderTest {

	@Test
	public void testSetCustomerId() {
		List<Item> items = new ArrayList<>();
		Order before = new Order(1l, items);
		Order after = new Order(2l, items);
		before.setCustomer_id(2l);
		assertEquals(before, after);
	}
	
	@Test
	public void testSetItems() {
		List<Item> items = new ArrayList<>();
		List<Item> otherItems = new ArrayList<>();
		Item widget = new Item(1l, "Widget", 1, 1.00f);
		widget.setQuantity(1);
		otherItems.add(widget);
		Order before = new Order(1l, items);
		Order after = new Order(1l, otherItems);
		before.setItems(otherItems);
		assertEquals(before, after);
	}
	
	@Test
	public void testAddItem() {
		List<Item> items = new ArrayList<>();
		List<Item> otherItems = new ArrayList<>();
		Item widget = new Item(1l, "Widget", 1, 1.00f);
		widget.setQuantity(1);
		otherItems.add(widget);
		Order before = new Order(1l, items);
		Order after = new Order(1l, otherItems);
		before.addItem(widget);
		assertEquals(before, after);
	}
}
