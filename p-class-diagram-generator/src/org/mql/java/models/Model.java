package org.mql.java.models;

import java.util.HashSet;
import java.util.Set;

public class Model {
	private String name;
	private Set<Attribute> attributes;
	private Set<Method> methods;



	public Model(String name) {
		this.name = name;
		attributes=new HashSet<>();
		methods=new HashSet<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Set<Method> getMethods() {
		return methods;
	}

	public void setMethods(Set<Method> methods) {
		this.methods = methods;
	}

	@Override
	public String toString() {
		String umlString = getName() + "\n";
		for(Attribute attr : attributes) {
			umlString = umlString + " " + attr + "\n";
		}
		for(Method method : methods) {
			umlString = umlString + " " + method + "\n";
		}
		return umlString;
	}
	
	
	
	
}
