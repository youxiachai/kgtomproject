/**
 * @copyright : youxiachai
 */
package com.achai.shop.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Tom_achai
 * @date 2012-1-20
 * @time 下午11:07:18
 */
@DatabaseTable(tableName="sales")
public class Sales {
	
	@DatabaseField(generatedId=true,columnName="sales_id")
	private int id;
	
	@DatabaseField(foreign=true)
	private Product product;
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public Date getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}

	public int getId() {
		return id;
	}

	//价格
	@DatabaseField()
	private double price;
	
	//利润
	@DatabaseField()
	private double profit;
	
	//出售时间
	@DatabaseField()
	private Date salesDate;
	
	public Sales() {
		
	}
	
	public Sales(long millis){
		this.salesDate = new Date(millis);
	}
	
	
}
