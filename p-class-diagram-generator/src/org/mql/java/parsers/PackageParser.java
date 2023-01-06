package org.mql.java.parsers;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import org.mql.java.helpers.ParseHelper;
import org.mql.java.models.Model;
import org.mql.java.models.Package;

public class PackageParser {
	private static final Logger logger = Logger.getLogger(PackageParser.class.getName());
	private String name;
	private String path;
	
	
	public PackageParser(String name,String path) {
		this.name=name;
		this.path=path;

	}
	
	
	public List<String> getSubPackages(){
		List<String> packgList = new Vector<String>();
		File dir = new File(path+ name.replace(".", "/"));
		File packFiles[] = dir.listFiles();

		for (File pck : packFiles) {
			if (pck.isDirectory()) {
				for(File f:pck.listFiles()) {
					if(f.isFile() && f.getName().endsWith(".class")) {
						packgList.add(name+"."+pck.getName());
						break;
					}
				}
			}
		}
		return packgList;
	}
	
	
	public List<String> getSubClasses(){
		List<String> classList = new Vector<String>();
		File dir = new File(path+ name.replace(".", "/"));
		File classFiles[] = dir.listFiles();

		for (File classe : classFiles) {
			if (ParseHelper.isAValidClassFile(classe)) {
				classList.add(name+"."+classe.getName().replace(".class", ""));
			}
		}
		return classList;
	}
	
	
	public Package parse() {
		Package pck=new Package(name);
			for(String classFileName:getSubClasses()) {
				Model model;
				try {
					model = new ClassParser(classFileName,path).parse();
					pck.addModel(model);
				} catch (MalformedURLException | ClassNotFoundException e) {
					logger.severe("Problem Parsing Package");
				}
			}
		return pck;
	}
	


}
