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
	private int supplier_id;
	@DatabaseField()
	private String name;
	
	@DatabaseField()
	private String phone;

	@DatabaseField(columnName="lastsupply")	
	private Date lastSupply; 
	
	public int getId() {
		return supplier_id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastSupply(Date lastSupply) {
		this.lastSupply = lastSupply;
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
