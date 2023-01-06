package org.mql.java.parsers;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.mql.java.helpers.ParseHelper;
import org.mql.java.models.Package;
import org.mql.java.models.Project;


public class ProjectParser{
	private final static Logger logger = Logger.getLogger(ProjectParser.class.getName());
	private String path;

	public ProjectParser(String path) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException  {
		this.path=path+"\\bin\\";
		Project parsedproject=parse();
		System.out.println(parsedproject);
	}
	

	 public Project parse() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		File directory = new File(path);
		Project project=new Project(directory);
		
		Set<String> packageList = new HashSet<>();
		ParseHelper.getPackages(path,packageList);
		
		for (String packageName : packageList) {
			Package pck= new PackageParser(packageName,path).parse();
			project.addPackage(pck);
		}
		
		return project;
	 }

}