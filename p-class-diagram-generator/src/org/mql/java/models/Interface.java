package org.mql.java.models;

import java.util.List;

public class Interface extends Model{
	private List<Interface> extendinterfaces;
	
	public Interface(String name) {
		super(name);
	}
	
	public Interface(String name,List<Interface> extendinterfaces) {
		super(name);
		this.extendinterfaces=extendinterfaces;
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
	
	public void setExtendinterfaces(List<Interface> extendinterfaces) {
		this.extendinterfaces = extendinterfaces;
	}
}
