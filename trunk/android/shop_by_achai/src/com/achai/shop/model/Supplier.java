/**
 * 
 */
package com.achai.shop.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Tom_achai
 *
 */
@DatabaseTable(tableName="supplier")
public class Supplier {
	@DatabaseField(generatedId=true,columnName="supplier_id")
	private int id;
	@DatabaseField()
	private String name;
	
	@DatabaseField()
	private String phone;

	@DatabaseField(columnName="lastsupply")	
	private Date lastSupply; 
	
	public int getId() {
		return id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getLastSupply() {
		return lastSupply;
	}



	public Supplier() {
		
	}
	
	public Supplier(long millis){
		this.lastSupply = new Date(millis);
	}
}
