package org.mql.java.parsers;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Logger;

import org.mql.java.helpers.CustomClassLoader;
import org.mql.java.helpers.ParseHelper;
import org.mql.java.models.Attribute;
import org.mql.java.models.Ennum;
import org.mql.java.models.Interface;
import org.mql.java.models.Method;
import org.mql.java.models.Model;

public class ClassParser {
	private static final Logger logger = Logger.getLogger(ClassParser.class.getName());
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
	
	public Set<Attribute> parseAttributes(){
		try {
			return ParseHelper.parseAttributes(classtoParse);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			logger.severe("Problem parsing  Model Attributes");
		}
		return null;
	}
	
	public Set<Method> parseConstructors(){
		return ParseHelper.parseConstructors(classtoParse);
	}
	
	public Set<Method> parseMethods(){
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
	
	
	public Model parse() {
		Model model;
		String cname=getClasstoParse().getSimpleName();
		
		//TODO:Not return null when its an annotation
		if(!isAnnotation()) {
			if(isInterface())
				model=new Interface(cname);
			else if(isEnnum())
				model=new Ennum(cname);
			else {
				model=new org.mql.java.models.Class(cname);
			}
			
			model.setAttributes(parseAttributes());
			if(!isEnnum()) {
				Set<Method> methods=new HashSet<>();
				methods.addAll(parseConstructors());
				methods.addAll(parseMethods());
				model.setMethods(methods);
			}
			return model;
		}
		return null;
		
	}
	
}
