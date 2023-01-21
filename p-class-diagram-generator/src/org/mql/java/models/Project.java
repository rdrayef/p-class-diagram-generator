package org.mql.java.models;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Project {
	private Set<Package> packages;
	private Set<RelationShip> relations;
	private String name;
	private String path;
	
	public Project(File pathtoproject) {
		packages=new HashSet<>();
		relations=new HashSet<>();
		this.name=pathtoproject.getName();
		this.path=pathtoproject.getAbsolutePath();
	}

	public Project(Set<Package> packages) {
		super();
		this.packages = packages;
	}

	public Set<Package> getUmlPackages() {
		return packages;
	}

	public Set<Package> getPackages() {
		return packages;
	}

	public void setPackages(Set<Package> packages) {
		this.packages = packages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void addPackage(Package newpackage) {
		packages.add(newpackage);
	}
	
	
	public void addRelation(RelationShip relation) {
		relations.add(relation);
	}

	public Set<RelationShip> getRelations() {
		return relations;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Project : " + name+"\n");
		sb.append("Packages : \n");
		for(Package pck : packages)
			sb.append(pck);
		return sb.toString();
	}
	
}
