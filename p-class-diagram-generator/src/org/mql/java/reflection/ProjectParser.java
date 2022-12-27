package org.mql.java.reflection;

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
	private String project;

	public ProjectParser(String project) throws MalformedURLException, ClassNotFoundException  {
		this.project=project;
		startProcess();
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
	 
	 
	 public void startProcess() {
		 if (project.indexOf(".zip") != -1) {
			try {
				unzipAndProcess(project);
					
			} catch (IOException e) {
				System.out.println("Failed to unzip the folder. \nNote: Keep the Java files in root folder of zip:\n"+ e.getMessage());
			}
		} else {
			File folder = new File(project);
			if (folder == null || !folder.isDirectory()) {
			System.out.println("Folder path provided is not valid, please check -> " + project);
			return;
			}
			processFiles(getFileListFromFolder(folder));
		}
			
}


	private void processFiles(List<File> files) {
		if (files.size() == 0) {
			System.out.println(
					"Folder path has no .java files, program works only for Java files. Check and re-run the program\n"
					+ "If you are using Zip file make sure the Java files are in home directory of Zip file");
			return;
		}
		ParserEngine obj = new ParserEngine();
		obj.parseFiles(files);
		/*
		 * GenerateUML generateUML = new GenerateUML();
		 * generateUML.createGrammar(outputFileName);
		 */
		
	}



	private List<File> getFileListFromFolder(File folder) {
		List<File> files = new Vector<>();
		File[] filesInFolder = folder.listFiles();
		for (int i = 0; filesInFolder != null && i < filesInFolder.length; i++) {
			File file = filesInFolder[i];
			if (isValidFile(file)) {
				files.add(file);
			}
		}
		return files;
	}



	private boolean isValidFile(File file) {
		if (file.isFile()) {
			if (file.getName().endsWith("class") && !file.getName().equalsIgnoreCase("RunParser.java")) {
				return true;
			}
		}else {
			System.out.println("Errrorrr file format: "+file.getName());

		}
		return false;
	}



	private void unzipAndProcess(String zipFilePath) throws IOException {
		String destDirectory = "test";
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
        	destDir.mkdir();
        }
        
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            }else {
            	// if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
        
        File file = new File(destDir.getAbsolutePath());
        processFiles(getFileListFromFolder(file));
	}



	private void extractFile(ZipInputStream zipIn, String filePath) {
		// TODO Auto-generated method stub
		
	}
}