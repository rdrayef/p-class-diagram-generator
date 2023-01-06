package org.mql.java.models;

import java.util.HashSet;
import java.util.Set;

public class Package {
	
	private String name;
	private Set<Model> models;
	
	public Package(String name) {
		this.name=name;
		models=new HashSet<>();
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

	public void addModel(Model newModel) {
		models.add(newModel);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("\nPackage : " + name);
		for(Model model : models) {
			sb.append("\n"+model);
		}
		return sb.toString();
	}
}
