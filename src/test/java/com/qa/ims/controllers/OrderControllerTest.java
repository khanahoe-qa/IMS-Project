package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.any;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)

public class OrderControllerTest {
	
	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;
	
	@Test
	public void testCreate() {
		final Long CUSTOMER_ID = 1l;
		final Long ITEM1_ID = 1l;
		final Long ITEM2_ID = 2l;
		final Integer QUANTITY1 = 1;
		final Integer QUANTITY2 = 2;
		
		Item item1 = new Item(ITEM1_ID, QUANTITY1);
		Item item2 = new Item(ITEM2_ID, QUANTITY2);
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		final Order created = new Order(CUSTOMER_ID, items);
		
		Mockito.when(utils.getLong()).thenReturn(CUSTOMER_ID, ITEM1_ID, ITEM2_ID);
		Mockito.when(utils.getInt()).thenReturn(QUANTITY1, QUANTITY2);
		Mockito.when(utils.getString()).thenReturn("q", "y", "n");
		Mockito.when(dao.create(any(Order.class))).thenReturn(created);
		
		assertEquals(created, controller.create());
		
		Mockito.verify(utils, Mockito.times(3)).getString();
		Mockito.verify(utils, Mockito.times(3)).getLong();
		Mockito.verify(utils, Mockito.times(2)).getInt();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}
	
	@Test
	public void testUpdateRemake() {
		final Long ORDER_ID = 1l;
		final Long CUSTOMER_ID = 1l;
		final Long ITEM1_ID = 1l;
		final Long ITEM2_ID = 2l;
		final Integer QUANTITY1 = 1;
		final Integer QUANTITY2 = 2;
		
		Item item1 = new Item(ITEM1_ID, QUANTITY1);
		Item item2 = new Item(ITEM2_ID, QUANTITY2);
		List<Item> oldItems = new ArrayList<>();
		List<Item> newItems = new ArrayList<>();
		oldItems.add(item1);
		newItems.add(item1);
		newItems.add(item2);
		final Order oldOrder = new Order(ORDER_ID, CUSTOMER_ID, oldItems);
		final Order updated = new Order(ORDER_ID, CUSTOMER_ID, newItems);
		
		Mockito.when(utils.getLong()).thenReturn(ORDER_ID, CUSTOMER_ID, ITEM1_ID, ITEM2_ID);
		Mockito.when(utils.getInt()).thenReturn(QUANTITY1, QUANTITY2);
		Mockito.when(utils.getString()).thenReturn("remake","q", "y", "n");
		Mockito.when(dao.readOrder(any(Long.class))).thenReturn(oldOrder);
		Mockito.when(dao.update(any(Order.class))).thenReturn(updated);
		
		assertEquals(updated, controller.update());
		
		Mockito.verify(utils, Mockito.times(4)).getString();
		Mockito.verify(utils, Mockito.times(4)).getLong();
		Mockito.verify(utils, Mockito.times(2)).getInt();
		Mockito.verify(dao, Mockito.times(1)).readOrder(ORDER_ID);
		Mockito.verify(dao, Mockito.times(1)).update(updated);
	}
	
	@Test
	public void testUpdateAdd() {
		final Long ORDER_ID = 1l;
		final Long CUSTOMER_ID = 1l;
		final Long ITEM1_ID = 1l;
		final Long ITEM2_ID = 2l;
		final Integer QUANTITY1 = 1;
		final Integer QUANTITY2 = 2;
		
		Item item1 = new Item(ITEM1_ID, QUANTITY1);
		Item item2 = new Item(ITEM2_ID, QUANTITY2);
		List<Item> oldItems = new ArrayList<>();
		List<Item> newItems = new ArrayList<>();
		oldItems.add(item1);
		newItems.add(item1);
		newItems.add(item2);
		final Order oldOrder = new Order(ORDER_ID, CUSTOMER_ID, oldItems);
		final Order updated = new Order(ORDER_ID, CUSTOMER_ID, newItems);
		
		Mockito.when(utils.getLong()).thenReturn(ORDER_ID, ITEM2_ID);
		Mockito.when(utils.getInt()).thenReturn(QUANTITY2);
		Mockito.when(utils.getString()).thenReturn("add");
		Mockito.when(dao.readOrder(any(Long.class))).thenReturn(oldOrder);
		Mockito.when(dao.update(any(Order.class))).thenReturn(updated);
		
		assertEquals(updated, controller.update());
		
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getInt();
		Mockito.verify(dao, Mockito.times(1)).readOrder(ORDER_ID);
		Mockito.verify(dao, Mockito.times(1)).update(updated);
		
	}
	
	@Test
	public void testUpdateRemove() {
		final Long ORDER_ID = 1l;
		final Long CUSTOMER_ID = 1l;
		final Long ITEM1_ID = 1l;
		final Long ITEM2_ID = 2l;
		final Integer QUANTITY1 = 1;
		final Integer QUANTITY2 = 2;
		
		Item item1 = new Item(ITEM1_ID, QUANTITY1);
		Item item2 = new Item(ITEM2_ID, QUANTITY2);
		List<Item> oldItems = new ArrayList<>();
		List<Item> newItems = new ArrayList<>();
		oldItems.add(item1);
		oldItems.add(item2);
		newItems.add(item2);
		final Order oldOrder = new Order(ORDER_ID, CUSTOMER_ID, oldItems);
		final Order updated = new Order(ORDER_ID, CUSTOMER_ID, newItems);
		
		Mockito.when(utils.getLong()).thenReturn(ORDER_ID, ITEM1_ID);
		Mockito.when(utils.getString()).thenReturn("remove");
		Mockito.when(dao.readOrder(any(Long.class))).thenReturn(oldOrder);
		Mockito.when(dao.update(any(Order.class))).thenReturn(updated);
		
		assertEquals(updated, controller.update());
		
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(1)).readOrder(ORDER_ID);
		Mockito.verify(dao, Mockito.times(1)).update(updated);
		
	}
	
	@Test
	public void testUpdateFail() {
		final Long ORDER_ID = 99l;
		
		Mockito.when(utils.getLong()).thenReturn(ORDER_ID);
		Mockito.when(dao.readOrder(any(Long.class))).thenReturn(null);
		
		assertEquals(null, controller.update());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
	}
	
	@Test
	public void testDelete() {
		final Long ORDER_ID = 1l;
		
		Mockito.when(utils.getLong()).thenReturn(ORDER_ID);
		Mockito.when(dao.delete(any(Long.class))).thenReturn(1);
		
		assertEquals(1, controller.delete());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(1l);
	}
	
	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, 1));
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, items));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}
}
