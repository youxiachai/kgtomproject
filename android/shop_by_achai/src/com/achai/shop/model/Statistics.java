/**
 * @copyright : youxiachai
 */
package com.achai.shop.model;




import com.j256.ormlite.field.DatabaseField;

/**
 * @author Tom_achai
 * @date 2012-2-5
 * @time 下午10:20:08
 */
public class Statistics {
	@DatabaseField(id=true)
	private String id;
	
	@DatabaseField
	private double cost_price;
	
	@DatabaseField
	private double sales_price;
	
	@DatabaseField
	private double profit_price;
	
	public Statistics(String date){
		
		
		this.id = date;
	}
	public Statistics(){
		
	}



	public String getId() {
		return id;
	}
	public double getProfit_price() {
		return profit_price;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setProfit_price(double profit_price) {
		this.profit_price = profit_price;
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


}
