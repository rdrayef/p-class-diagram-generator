package org.mql.java.parsers;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Logger;

import org.mql.java.helpers.ParseHelper;
import org.mql.java.models.Model;
import org.mql.java.models.Package;
import org.mql.java.models.Project;
import org.mql.java.models.RelationShip;

public class ProjectParser {
	private final static Logger logger = Logger.getLogger(ProjectParser.class.getName());
	private String path;
	private Project parsedproject;

	public ProjectParser(String path)
			throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		this.path = path + "\\bin\\";
		parsedproject = parse();
		parsedproject.setRelations(parseRelations(parsedproject));;
		ProjectDomParser domParser = new ProjectDomParser(parsedproject);
		domParser.generate();
		System.out.println(parsedproject);

	}

	public Project parse()
			throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		File directory = new File(path);
		Project project = new Project(directory);

		Set<String> packageList = new HashSet<>();
		ParseHelper.getPackages(path, packageList);

		for (String packageName : packageList) {
			Package pck = new PackageParser(packageName, path).parse();
			project.addPackage(pck);
		}

		return project;
	}

	private List<RelationShip> parseRelations(Project project) {
		List<RelationShip> relations = new Vector<>();
		RelationShipParser reParser = new RelationShipParser();
		for (Model source : project.getModels()) {
			for (Model target : project.getModels()) {
				relations.addAll(reParser.parse(source, target));
			}
		}
		return relations;
	}

	public Project getParsedproject() {
		return parsedproject;
	}

}