/**
 * @copyright : youxiachai
 */
package com.achai.shop.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * @author Tom_achai
 * @date 2012-2-11
 * @time 下午11:53:41
 */
public class StatisticsMonth {
	@DatabaseField(id=true)
	private long id;
	
	@DatabaseField
	private double cost_price;
	
	@DatabaseField
	private double sales_price;
	
	@DatabaseField
	private double profit_price;
	
	public StatisticsMonth(long date){
		this.id = date;
	}
	public StatisticsMonth(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getCost_price() {
		return cost_price;
	}

	public void setCost_price(double cost_price) {
		this.cost_price = cost_price;
	}

	public double getSales_price() {
		return sales_price;
	}

	public void setSales_price(double sales_price) {
		this.sales_price = sales_price;
	}

	public double getProfit_price() {
		return profit_price;
	}

	public void setProfit_price(double profit_price) {
		this.profit_price = profit_price;
	}
}
