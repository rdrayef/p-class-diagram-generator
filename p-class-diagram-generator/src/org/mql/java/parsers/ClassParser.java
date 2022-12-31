package org.mql.java.parsers;

import java.net.MalformedURLException;

import org.mql.java.loader.CustomClassLoader;

public class ClassParser {
	private Class<?> classtoParse;


	public ClassParser(String name,String path) throws MalformedURLException, ClassNotFoundException {
		this(CustomClassLoader.loadClass(name, path));
	}
	
	public ClassParser(Class<?> classtoparse) {
		this.classtoParse=classtoparse;
	}

	public Class<?> getClasstoParse() {
		return classtoParse;
	}

	public void setClasstoParse(Class<?> classtoParse) {
		this.classtoParse = classtoParse;
	}
	
	
	

}
