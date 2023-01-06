package org.mql.java.models;

import java.util.Set;

public class Ennum extends Model{
	private Set<Attribute> constants;
	
	public Ennum(String name) {
		super(name);
	}
	
	@Override
	public Set<Attribute> getAttributes() {
		return super.getAttributes();
	}
	
	@Override
	public void setAttributes(Set<Attribute> attributes) {
		super.setAttributes(attributes);
	}
	
	public void addAttribute(Attribute newAttribute) {
		constants.add(newAttribute);
	}
	
	@Override
	public String toString() {
		return "ENNUM: "+super.toString();
	}

}
