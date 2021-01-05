package com.qa.ims;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class IMSTest {

	@Mock
	private CustomerController customers;
	
	@Mock
	private ItemController items;
	
	@Mock
	private OrderController orders;
	
	@Mock
	private Utils utils;
	
	@InjectMocks
	private IMS ims;
	
	@Test
	public void testCreateCustomer() {
		
		Customer created = new Customer("Joe", "Bloggs");
		
		when(utils.getString()).thenReturn("uname", "pwd", "CUSTOMER", "CREATE", "RETURN", "STOP");
		when(customers.create()).thenReturn(created);
		
		ims.imsSystem();
		
		Mockito.verify(utils, Mockito.times(6)).getString();
		Mockito.verify(customers, Mockito.times(1)).create();

	}
	
	@Test
	public void testReadAllCustomer() {
		
		List<Customer> list = new ArrayList<>();
		
		when(utils.getString()).thenReturn("uname", "pwd", "CUSTOMER", "READ", "RETURN", "STOP");
		when(customers.readAll()).thenReturn(list);
		
		ims.imsSystem();
		
		Mockito.verify(utils, Mockito.times(6)).getString();
		Mockito.verify(customers, Mockito.times(1)).readAll();

	}
	
	@Test
	public void testUpdateCustomer() {
		
		Customer updated = new Customer(1L, "Joe", "Bloggs");
		
		when(utils.getString()).thenReturn("uname", "pwd", "CUSTOMER", "UPDATE", "RETURN", "STOP");
		when(customers.update()).thenReturn(updated);
		
		ims.imsSystem();
		
		Mockito.verify(utils, Mockito.times(6)).getString();
		Mockito.verify(customers, Mockito.times(1)).update();

	}
	
	@Test
	public void testDeleteCustomer() {

		when(utils.getString()).thenReturn("uname", "pwd", "CUSTOMER", "DELETE", "RETURN", "STOP");
		when(customers.delete()).thenReturn(0);
		
		ims.imsSystem();
		
		Mockito.verify(utils, Mockito.times(6)).getString();
		Mockito.verify(customers, Mockito.times(1)).delete();

	}
	
	@Test
	public void testCreateItem() {
		
		Item created = new Item("Widget", 11, 11.11f);
		
		when(utils.getString()).thenReturn("uname", "pwd", "ITEM", "CREATE", "RETURN", "STOP");
		when(items.create()).thenReturn(created);
		
		ims.imsSystem();
		
		Mockito.verify(utils, Mockito.times(6)).getString();
		Mockito.verify(items, Mockito.times(1)).create();

	}
	
	@Test
	public void testReadAllItem() {
		
		List<Item> list = new ArrayList<>();
		
		when(utils.getString()).thenReturn("uname", "pwd", "ITEM", "READ", "RETURN", "STOP");
		when(items.readAll()).thenReturn(list);
		
		ims.imsSystem();
		
		Mockito.verify(utils, Mockito.times(6)).getString();
		Mockito.verify(items, Mockito.times(1)).readAll();

	}
	
	@Test
	public void testUpdateItem() {
		
		Item updated = new Item(1L, "Widget", 11, 11.11f);
		
		when(utils.getString()).thenReturn("uname", "pwd", "ITEM", "UPDATE", "RETURN", "STOP");
		when(items.update()).thenReturn(updated);
		
		ims.imsSystem();
		
		Mockito.verify(utils, Mockito.times(6)).getString();
		Mockito.verify(items, Mockito.times(1)).update();

	}
	
	@Test
	public void testDeleteItem() {

		when(utils.getString()).thenReturn("uname", "pwd", "ITEM", "DELETE", "RETURN", "STOP");
		when(items.delete()).thenReturn(0);
		
		ims.imsSystem();
		
		Mockito.verify(utils, Mockito.times(6)).getString();
		Mockito.verify(items, Mockito.times(1)).delete();

	}
	
	@Test
	public void testCreateOrder() {
		
		List<Item> items = new ArrayList<>();
		Order created = new Order(1l, 1l, items);
		
		when(utils.getString()).thenReturn("uname", "pwd", "ORDER", "CREATE", "RETURN", "STOP");
		when(orders.create()).thenReturn(created);
		
		ims.imsSystem();
		
		Mockito.verify(utils, Mockito.times(6)).getString();
		Mockito.verify(orders, Mockito.times(1)).create();

	}
	
	@Test
	public void testReadAllOrder() {
		
		List<Order> list = new ArrayList<>();
		
		when(utils.getString()).thenReturn("uname", "pwd", "ORDER", "READ", "RETURN", "STOP");
		when(orders.readAll()).thenReturn(list);
		
		ims.imsSystem();
		
		Mockito.verify(utils, Mockito.times(6)).getString();
		Mockito.verify(orders, Mockito.times(1)).readAll();

	}
	
	@Test
	public void testUpdateOrder() {
		
		List<Item> items = new ArrayList<>();
		Order updated = new Order(1l, 1l, items);
		
		when(utils.getString()).thenReturn("uname", "pwd", "ORDER", "UPDATE", "RETURN", "STOP");
		when(orders.update()).thenReturn(updated);
		
		ims.imsSystem();
		
		Mockito.verify(utils, Mockito.times(6)).getString();
		Mockito.verify(orders, Mockito.times(1)).update();

	}
	
	@Test
	public void testDeleteOrder() {

		when(utils.getString()).thenReturn("uname", "pwd", "ORDER", "DELETE", "RETURN", "STOP");
		when(orders.delete()).thenReturn(0);
		
		ims.imsSystem();
		
		Mockito.verify(utils, Mockito.times(6)).getString();
		Mockito.verify(orders, Mockito.times(1)).delete();

	}
}
