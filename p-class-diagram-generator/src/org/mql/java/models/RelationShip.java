package org.mql.java.models;

public class RelationShip {
	private Class parent;
	private Class child;
	private String type;
	private String parentCardinality;
	private String childCardinality;

	public RelationShip() {
		// TODO Auto-generated constructor stub
	}
	
	public Class getParent() {
		return parent;
	}

	public void setParent(Class parent) {
		this.parent = parent;
	}

	public Class getChild() {
		return child;
	}

	public void setChild(Class child) {
		this.child = child;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentCardinality() {
		return parentCardinality;
	}

	public void setParentCardinality(String parentCardinality) {
		this.parentCardinality = parentCardinality;
	}

	public String getChildCardinality() {
		return childCardinality;
	}

	public void setChildCardinality(String childCardinality) {
		this.childCardinality = childCardinality;
	}
	
	

}
