package org.mql.java.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ProjectParser{
	private String path;

	public ProjectParser(String path) throws MalformedURLException, ClassNotFoundException  {
		this.path=path+"\\bin\\";
		//startProcess();
		listPackages();
	}
	
	
	

	 public void listPackages() throws MalformedURLException, ClassNotFoundException {
		// Get a list of all the files in the /bin directory
		List<String> packageList = new Vector<>();
		getPackages(path,packageList);

		
		for (String packageName : packageList) {
			PackageParser packageParser = new PackageParser(packageName,path);
			for (String className : packageParser.getSubClasses()) {
				ClassParser parser = new ClassParser(className,path);
				System.out.println("Classe::"+parser.getClasstoParse());
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