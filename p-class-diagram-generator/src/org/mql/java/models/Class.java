package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class Class {
	private List<Attribute> attributes;
	private List<Method> umlMethods;
	private boolean isInterface;
	private String name;
	private List<String> parents;
	
	public Class() {
		name = "";
		isInterface = false;
		attributes = new Vector<>();
		umlMethods = new Vector<>();
		parents = new Vector<>();
	}

	public List<Attribute> getUmlVariables() {
		return attributes;
	}

	public void setUmlVariables(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public List<Method> getUmlMethods() {
		return umlMethods;
	}

	public void setUmlMethods(List<Method> umlMethods) {
		this.umlMethods = umlMethods;
	}

	public boolean isInterface() {
		return isInterface;
	}

	public void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getParents() {
		return parents;
	}

	public void setParents(List<String> parents) {
		this.parents = parents;
	}
	
	
	public void addParent(String parent){
		this.parents.add(parent);
	}
	
	
	public boolean hasVariable(String attribute){
		for(Attribute attr : attributes){
			if(attr.getName().equalsIgnoreCase(attribute)){
				return true;
			}
		}
		return false;
	}

}
