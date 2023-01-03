package org.mql.java.parsers;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.List;
import org.mql.java.helpers.CustomClassLoader;
import org.mql.java.helpers.ParseHelper;
import org.mql.java.models.Attribute;
import org.mql.java.models.Method;

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
	
	public List<Attribute> parseAttributes(){
		try {
			return ParseHelper.parseAttributes(classtoParse);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Method> parseConstructors(){
		return ParseHelper.parseConstructors(classtoParse);
	}
	
	public List<Method> parseMethods(){
		return ParseHelper.parseMethods(classtoParse);
	}
	
	
	public boolean isInterface() {
		return ParseHelper.isInterface(classtoParse);
	}
	
	public boolean isEnnum() {
		return ParseHelper.isEnnum(classtoParse);
	}
	
	public boolean isAnnotation() {
		return ParseHelper.isAnnotation(classtoParse);
	}
	
	
}
