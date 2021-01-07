package com.qa.ims.persistence.domain;

import java.util.List;

public class Order {

	private Long order_id;
	private Long customer_id;
	// List of items in the order
	private List<Item> items;
	
	// Generator for creating new orders
	public Order(Long customer_id, List<Item> items) {
		this.customer_id = customer_id;
		this.items = items;
	}
	
	// Generator for updating existing orders
	public Order(Long order_id, Long customer_id, List<Item> items) {
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.items = items;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	// Returns a string containing names of items and the number of each ordered
	@Override
	public String toString() {
		String orderDetails = "\norder id: " + order_id + ", customer id: " + customer_id;
		for(Item i: items) {
			orderDetails += "\nitem ID: "+i.getId()+", quantity: "+i.getQuantity();
		}
		return orderDetails;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	// Searches for an item id in items and removes the last item with that id, then returns the list of items
	public List<Item> removeItem(Long item_id){
		int index = 0;
		boolean found = false;
		for(int i = 0; i < items.size(); ++i) {
			
			if(items.get(i).getId() == item_id) {
				index = i;
				found = true;
			}
		}
		if(found) {
			items.remove(index);
		}
		return items;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (!customer_id.equals(other.customer_id))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}
	
}
