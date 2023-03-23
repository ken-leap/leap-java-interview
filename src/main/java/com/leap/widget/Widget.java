package com.leap.widget;

import java.math.BigDecimal;
import java.util.Date;

public class Widget {
	private int id;
	private String name;
	private String category;
	private BigDecimal price;
	private Date purchaseDate;

	public Widget() {
	}

	public Widget(int id, String name, String category, BigDecimal price, Date purchaseDate) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.purchaseDate = purchaseDate;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
}
