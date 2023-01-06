package org.mql.java.models;

import java.util.Set;

public class Interface extends Model{
	private Set<Interface> extendinterfaces;
	
	public Interface(String name) {
		super(name);
	}
	
	public Interface(String name,Set<Interface> extendinterfaces) {
		super(name);
		this.extendinterfaces=extendinterfaces;
	}
	
	public Set<Attribute> getAttributes() {
		return super.getAttributes();
	}

	public void setAttributes(Set<Attribute> attributes) {
		super.setAttributes(attributes);
	}


	public Set<Method> getMethods() {
		return super.getMethods();
	}

	public void setMethods(Set<Method> methods) {
		super.setMethods(methods);
	}
	
	public Set<Interface> getExtendinterfaces() {
		return extendinterfaces;
	}
	
	public void setExtendinterfaces(Set<Interface> extendinterfaces) {
		this.extendinterfaces = extendinterfaces;
	}
	
	@Override
	public String toString() {
		return "INTERFACE: "+super.toString();
	}
}
