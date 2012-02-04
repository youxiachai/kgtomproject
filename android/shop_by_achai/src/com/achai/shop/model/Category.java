
/**
 * 下午8:57:53
 */
package com.achai.shop.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Tom_achai
 * @time 下午8:57:53
 */
@DatabaseTable(tableName="category")
public class Category {
	@DatabaseField(generatedId = true ,columnName="category_id")
	private int category_id;
	@DatabaseField
	private String name;
	
	public int getId() {
		return category_id;
	}

	public void setId(int id) {
		this.category_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category() {
		
	}
	
	@Override
	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("id=").append(id);
//		sb.append(", ").append("name=").append(name);
		return this.name;
		
	}
}
