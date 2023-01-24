package org.mql.java.models;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Project {
	private Set<Package> packages;
	private List<RelationShip> relations;
	private String name;
	private String path;

	public Project(File pathtoproject) {
		packages = new HashSet<>();
		relations = new Vector<>();
		this.name = pathtoproject.getName();
		this.path = pathtoproject.getAbsolutePath();
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

	public void addRelations(List<RelationShip> relations) {
		relations.addAll(relations);
	}

	public List<RelationShip> getRelations() {
		return relations;
	}
	
	public void setRelations(List<RelationShip> relations) {
		this.relations = relations;
	}

	public List<Model> getModels() {
		List<Model> models = new Vector<>();

		for (Package aPackage : packages) {
			for (Model model : aPackage.getModels()) {
				models.add(model);

			}
		}

		return models;
	}

	public Model getModel(String name) {
		for (Package aPackage : packages) {
			for (Model model : aPackage.getModels()) {
				return model;

			}
		}

		return null;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Project : " + name + "\n");
		sb.append("Packages : \n");
		for (Package pck : packages)
			sb.append(pck);
		sb.append("Relations : \n");
		for(RelationShip relShip:relations)
			sb.append(relShip);
		return sb.toString();
	}

}
