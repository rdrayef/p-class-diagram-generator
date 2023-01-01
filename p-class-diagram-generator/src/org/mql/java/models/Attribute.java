package org.mql.java.models;

import java.lang.reflect.Type;

import org.mql.java.helpers.ParseHelper;

public class Attribute {
	private int modifier;
	private String name;
	private Object initialValue;
	private Type type;
	private boolean isStatic;
	private boolean isFinal;

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

	public Object getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(Object object) {
		this.initialValue = object;
	}

	

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public String getUMLString(){
		String initv=!"".equals(initialValue) && null!=initialValue ?" ="+initialValue:"";
		return ParseHelper.getModifiers(modifier).getLabel()+" "+name+" : "+type.getTypeName()+initv;
	}

}
