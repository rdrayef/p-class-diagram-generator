package org.mql.java.parsers;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.enums.Modifiers;
import org.mql.java.models.Attribute;import org.mql.java.models.Method;


public class ProjectParser{
	private String path;

	public ProjectParser(String path) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException  {
		this.path=path+"\\bin\\";
		//startProcess();
		listPackages();
	}
	
	
	

	 public void listPackages() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		// Get a list of all the files in the /bin directory
		List<String> packageList = new Vector<>();
		getPackages(path,packageList);
		for (String packageName : packageList) {
			PackageParser packageParser = new PackageParser(packageName,path);
			for (String className : packageParser.getSubClasses()) {
				if(!className.matches(".*\\$[0-9]+.*")) {
					ClassParser parser = new ClassParser(className,path);
					Class<?> current=parser.getClasstoParse();
					System.out.println(current.getSimpleName()+":");
					for(Attribute atr:parser.parseAttributes()) {
						System.out.println(atr.getUMLString());
					}
					for(Method met:parser.parseConstructors()) {
						System.out.println(met.getParameterizedUMLString());
					}
					for(Method met:parser.parseMethods()) {
						System.out.println(met.getParameterizedUMLString());
					}
				}
			}
		}
	 }
	 
	 
	private void getPackages(String dir, List<String> packages) {
		File directory = new File(dir);
		File[] filesList = directory.listFiles();
		// Iterate through the files and add directories to the list of packages
		for (File file : filesList) {
			if (file.isFile()) {
				String path = file.getPath();
				String packName = path.substring(path.indexOf("bin") + 4, path.lastIndexOf('\\'));
				packages.add(packName.replace('\\', '.'));
			} else if (file.isDirectory()) {
				// Call the recursive method with the directory as input
				getPackages(file.getAbsolutePath(), packages);
			}
		}
	}
	 

	


}