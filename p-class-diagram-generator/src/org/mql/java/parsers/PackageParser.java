package org.mql.java.parsers;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.mql.java.models.Package;

public class PackageParser {
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
			if (isValidClassFile(classe)) {
				classList.add(name+"."+classe.getName().replace(".class", ""));
			}
		}
		return classList;
	}
	
	
	private boolean isValidClassFile(File classfile) {
		if (classfile.isFile() && classfile.getName().endsWith(".class") && !classfile.getName().equalsIgnoreCase("RunParser.class")) {
				return true;
		}
		return false;
	}
	


}
