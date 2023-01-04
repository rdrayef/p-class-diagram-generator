package org.mql.java.models;

import java.util.List;

public class Model {
	private String name;
	private List<Attribute> attributes;
	private List<Method> methods;

	public Model(String name, List<Attribute> attributes, List<Method> methods) {
		super();
		this.name = name;
		this.attributes = attributes;
		this.methods = methods;
	}

	public Model(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	@Override
	public String toString() {
		return "Model [name=" + name + ", attributes=" + attributes + ", methods=" + methods + "]";
	}
	
	
	
	
}
