package org.mql.java.ui.components;

import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import org.mql.java.models.Model;
import org.mql.java.models.Package;
import org.mql.java.models.Project;
import org.mql.java.models.RelationShip;

public class ProjectUI extends JPanel {
private static final long serialVersionUID = 1L;
	
	private Project project;
	private List<PackageUI> uiPackages;
	private List<RelationUI> uiRelations;
		
	public ProjectUI(Project project) {
		this.project = project;
		uiPackages = new Vector<>();
		uiRelations = new Vector<>();
		
		setLayout(null);
		
		drawPackages();
		drawRelations();
	}
	
	private void drawPackages() {
		PackageUI jumlPackage;
		for (Package umlPackage : project.getPackages()) {
			int x = 1, y = 1;
			
			jumlPackage = new PackageUI(umlPackage);
			jumlPackage.setLocation(x, y);
			add(jumlPackage);
			
			uiPackages.add(jumlPackage);
		}
	}
	
	private void drawRelations() {
		RelationUI jumlRelation;
		for (RelationShip relation : project.getRelations()) {
			jumlRelation = new RelationUI(relation, this);
			
			add(jumlRelation);
			uiRelations.add(jumlRelation);
		}
	}
	
	public ModelUI getJumlClassifier(Model model) {
		for (PackageUI uiPackage : uiPackages) {
			for (ModelUI modelUI : uiPackage.getModelUIs()) {
				if (modelUI.getModel().getName().equals(model.getName())) {
					return modelUI;
				}
			}
		}
		
		return null;
	}

}
