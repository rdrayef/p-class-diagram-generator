package org.mql.java.models;

import java.util.List;

public class Project {
	private List<Package> packages;
	
	public Project() {
		// TODO Auto-generated constructor stub
	}

	public Project(List<Package> packages) {
		super();
		this.packages = packages;
	}

	public List<Package> getUmlPackages() {
		return packages;
	}

	
	

}
