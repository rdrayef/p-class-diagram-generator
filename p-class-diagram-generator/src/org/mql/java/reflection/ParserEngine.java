package org.mql.java.reflection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;

public class ParserEngine {
	private Wrapper wrapper;

	public ParserEngine() {
		wrapper=Wrapper.getInstance();
	}
	
	/**
	 * Parsing begins here for each input file
	 * @param files
	 */
	public void parseFiles(List<File> files){
		try{
			for(File file : files){
				System.out.println("Parsing " + file.getAbsolutePath() + " file...");
				//CompilationUnit compliationUnit = JavaParser.parse(file);
				createUMLClass(file);
			}
			//counselor.removeUnneccessaryMethods();
		}catch(FileNotFoundException ex){
			System.err.println("Error: File not found. Trace: "+ ex.getMessage());
		}catch(IOException ex){
			System.err.println("Error: IO Exception. Trace: "+ ex.getMessage());
		}catch(ParseException ex){
			System.err.println("Error: Parse exception. Trace: "+ ex.getMessage());
		}
	}

	private void createUMLClass(File file) throws FileNotFoundException,IOException,ParseException{
		List<Class<?>> classes;
		if (file.getName().endsWith(".class")) {
			classes=new Vector<>();
	         String className = file.getName().replace(".class", "");
	         className = className.replace("/", ".");
	         Class<?> c=className.getClass();
	         classes.add(c);
	        System.out.println(className);
	   }
		

		}
		
	}


