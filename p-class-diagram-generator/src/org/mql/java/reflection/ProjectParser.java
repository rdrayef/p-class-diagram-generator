package org.mql.java.reflection;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ProjectParser{
	private String project;

	public ProjectParser(String project) throws MalformedURLException, ClassNotFoundException  {
		this.project=project;
		listPackages(project);
	}
	
	

	 public void listPackages(String dir) throws MalformedURLException, ClassNotFoundException {

		 File binDirectory = new File(dir);
		 URL[] classPath = { binDirectory.toURI().toURL() };
		 ClassLoader classLoader = new URLClassLoader(classPath);

		 // Get a list of all the files in the /bin directory
		 File[] files = binDirectory.listFiles();
		 List<String> packages = new ArrayList<>();
		 File binDir = new File(dir);
		 
		 System.out.println(Arrays.toString(getPackages(binDir).toArray()));

	    }
	 
	 public List<String> getPackages(File dir) {
		    // Create a list to store the packages and classes
		    List<String> packages = new Vector<>();
		    List<String> classes=new Vector<>();

		    // Get a list of files in the directory
		    File[] files = dir.listFiles();

		    // Iterate through the files and add directories to the list of packages
		    for (File file : files) {
		      if (file.isDirectory()) {
		        packages.add(file.getName());
		        // Call the recursive function with the directory as input
		        packages.addAll(getPackages(file));
		      }
		      
		      if (file.getName().endsWith(".class")) {
			         // Get the fully qualified class name
			         String className = file.getName().replace(".class", "");
			         className = className.replace("/", ".");
			         classes.add(className);
			        System.out.println(className);
			   }
		     
		    }

		    return packages;
		  }
		
	

	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
		new ProjectParser("C:\\Users\\redou\\Documents\\MQL-2023\\Java\\projects\\DRAYEF Redouane-Generics\\bin");
	}

}
