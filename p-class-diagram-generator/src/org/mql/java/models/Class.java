package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class Class {
	private List<Attribute> attributes;
	private List<Method> methods;
	private boolean isInterface;
	private boolean isEnnum;
	private boolean isAnnotation;
	private String name;
	private String parent;
	private List<Class> implinterfaces;
	private List<Class> innerclasses;
	
	
	public Class() {
		name = "";
		isInterface = false;
		attributes = new Vector<>();
		methods = new Vector<>();
		parent = "";
	}

	public List<Attribute> getUmlVariables() {
		return attributes;
	}

	public void setUmlVariables(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public List<Method> getUmlMethods() {
		return methods;
	}

	public void setUmlMethods(List<Method> methods) {
		this.methods = methods;
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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public List<Class> getImplinterfaces() {
		return implinterfaces;
	}

	public void setImplinterfaces(List<Class> implinterfaces) {
		this.implinterfaces = implinterfaces;
	}

	public List<Class> getInnerclasses() {
		return innerclasses;
	}

	public void setInnerclasses(List<Class> innerclasses) {
		this.innerclasses = innerclasses;
	}

	public boolean hasVariable(String attribute){
		for(Attribute attr : attributes){
			if(attr.getName().equalsIgnoreCase(attribute)){
				return true;
			}
		}
		return false;
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

	public boolean isEnnum() {
		return isEnnum;
	}

	public void setEnnum(boolean isEnnum) {
		this.isEnnum = isEnnum;
	}

	public boolean isAnnotation() {
		return isAnnotation;
	}

	public void setAnnotation(boolean isAnnotation) {
		this.isAnnotation = isAnnotation;
	}
	
	

}
