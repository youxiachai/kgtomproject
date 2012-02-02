package com.achai.shop.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Tom_achai
 *
 */
@DatabaseTable(tableName="stock")
public class Stock {
	@DatabaseField(generatedId=true,columnName="stock_id")
	private int id;
	
	@DatabaseField(columnName="product_id",foreign=true)
	private Product product;
	
	@DatabaseField(columnName="instock")
	private int instock;
	
	@DatabaseField(columnName="update_time")
	private Date updateTime;

	public Stock() {
		
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getInstock() {
		return instock;
	}

	public void setInstock(int instock) {
		this.instock = instock;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getId() {
		return id;
	}
	
	
}
