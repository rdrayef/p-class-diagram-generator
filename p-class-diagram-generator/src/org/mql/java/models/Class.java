package org.mql.java.models;

import java.util.List;

public class Class extends Model{

	private String parent;
	private List<Class> implinterfaces;
	private List<Class> innerclasses;
	
	
	public Class(String name) {
		super(name);
		
	}

	public String getName() {
		return super.getName();
	}

	public void setName(String name) {
		super.setName(name);
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

	

	public List<Attribute> getAttributes() {
		return super.getAttributes();
	}

	public void setAttributes(List<Attribute> attributes) {
		super.setAttributes(attributes);
	}


	public List<Method> getMethods() {
		return super.getMethods();
	}

	public void setMethods(List<Method> methods) {
		super.setMethods(methods);
	}
}