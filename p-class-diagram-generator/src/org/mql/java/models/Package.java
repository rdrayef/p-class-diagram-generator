package org.mql.java.models;

import java.util.List;

public class Package {
	
	private String name;
	private List<Class> classes;
	private List<Interface> interfaces;
	private List<Annotation> annotations;
	private List<Ennum> enumerations;
	
	public Package(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Class> getClasses() {
		return classes;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	public List<Interface> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	public List<Ennum> getEnumerations() {
		return enumerations;
	}

	public void setEnumerations(List<Ennum> enumerations) {
		this.enumerations = enumerations;
	}
	
	

}
