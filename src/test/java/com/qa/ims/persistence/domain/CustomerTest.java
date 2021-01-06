package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CustomerTest {

	@Test
	public void testSetId() {
		Customer before = new Customer("Joe", "Bloggs");
		Customer after = new Customer(2l, "Joe", "Bloggs");
		before.setId(2l);
		assertEquals(after, before);
	}
	
	@Test
	public void testFirstName() {
		Customer before = new Customer("Joe", "Bloggs");
		Customer after = new Customer("Fred", "Bloggs");
		before.setFirstName("Fred");
		assertEquals(after, before);
	}
	
	@Test
	public void testSetSurname() {
		Customer before = new Customer("Joe", "Bloggs");
		Customer after = new Customer("Joe", "Schmo");
		before.setSurname("Schmo");
		assertEquals(after, before);
	}
}
