package com.example.hellotwodbs;

import com.j256.ormlite.field.DatabaseField;

/**
 * A simple demonstration object we are creating and persisting to the database.
 */
public class ComplexData {

	// id is generated by the database and set on the object automagically
	@DatabaseField(generatedId = true)
	int id;
	@DatabaseField
	long millis;
	@DatabaseField
	boolean odd;

	ComplexData() {
		// needed by ormlite
	}

	public ComplexData(long millis) {
		this.millis = millis / 1000;
		this.odd = ((this.millis % 2) == 1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id=").append(id);
		sb.append(", ").append("millis=").append(millis);
		sb.append(", ").append("odd=").append(odd);
		return sb.toString();
	}
}