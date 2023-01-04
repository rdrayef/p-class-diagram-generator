package org.mql.java.models;

import java.util.List;
import java.util.Set;

public class Package {
	
	private String name;
	private Set<Model> models;
	
	public Package(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Set<Model> getModels() {
		return models;
	}

	public void setModels(Set<Model> models) {
		this.models = models;
	}

	
	

}
