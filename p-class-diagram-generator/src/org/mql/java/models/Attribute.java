package org.mql.java.models;

import java.lang.reflect.Modifier;

public class Attribute {
	private int modifier;
	private String name;
	private String initialValue;
	private boolean isUMLClassType;
	private String type;

	public Attribute() {
		// TODO Auto-generated constructor stub
	}

	public int getModifier() {
		return modifier;
	}

	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}

	public boolean isUMLClassType() {
		return isUMLClassType;
	}

	public void setUMLClassType(boolean isUMLClassType) {
		this.isUMLClassType = isUMLClassType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getUMLString(){
		return Modifier.toString(modifier) + name + ": " + type + initialValue + "\n";
	}

}
