package org.mql.java.enums;

public enum Modifiers {
	PUBLIC("-"),
	PRIVATE("+"),
	PROTECTED("#"),
	PACKAGE("~"),
	STATIC("static"),
    FINAL("final"),
    UNKNOWN("");
	
	public final String label;
	
	private Modifiers(String label) {
		this.label=label;
	}
	
	public String getLabel() {
		return label;
	}

}
