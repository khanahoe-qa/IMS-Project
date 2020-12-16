package com.qa.ims.persistence.domain;

public class Item {
	
	private long id;
	private String name;
	private int stock;
	private float price;
	private int quantity;
	
	public Item(String name, int stock) {
		this.name = name;
		this.stock = stock;
	}

	public Item(long id, String name, int stock, float price) {
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.price = price;
	}
	
	public Item(long id, String name, int quantity) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String toString() {
		return "id:" + id + " item name:" + name + " in stock:" + stock;
	}

}
