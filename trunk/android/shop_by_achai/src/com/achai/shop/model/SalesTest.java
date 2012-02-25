package com.achai.shop.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="shop_sales")
public class SalesTest {
	
	@DatabaseField(generatedId=true,columnName="id")
	private int id;
	
	@DatabaseField
	private String product_name;
	
	@DatabaseField
	private String salePrice;
	
	@DatabaseField
	private String fromPrice;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public String getFromPrice() {
		return fromPrice;
	}

	public void setFromPrice(String fromPrice) {
		this.fromPrice = fromPrice;
	}

	public String getRequirePrice() {
		return requirePrice;
	}

	public void setRequirePrice(String requirePrice) {
		this.requirePrice = requirePrice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@DatabaseField
	private String requirePrice;
	
	@DatabaseField
	private String date;
}
