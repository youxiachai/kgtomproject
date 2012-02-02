package com.achai.shop.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Tom_achai
 *
 */
@DatabaseTable(tableName="product")
public class Product {
	@DatabaseField(columnName="product_id",id=true)
	private int id;
	public void setId(int id) {
		this.id = id;
	}

	@DatabaseField(columnName="product_name")
	private String productName;
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@DatabaseField()
	private int productId;
	
	@DatabaseField(foreign=true)
	private Category category;
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	@DatabaseField(foreign=true)
	private Supplier supplier;
	
	@DatabaseField
	private double price;
}
