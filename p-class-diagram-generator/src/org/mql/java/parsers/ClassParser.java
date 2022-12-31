package org.mql.java.parsers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.loader.CustomClassLoader;
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
		List<Attribute> attributes=new Vector<>();
		for(Field field:classtoParse.getDeclaredFields()) {
			Attribute attr=new Attribute();
			attr.setModifier(field.getModifiers());
			attr.setName(field.getName());
			attr.setType(attr.getType());
			attr.setInitialValue(attr.getInitialValue());
			attributes.add(attr);
		}
		return attributes;
	}
	
	public List<Method> parseConstructors(){
		List<Method> constructors=new Vector<>();
		for(Constructor<?> c:classtoParse.getDeclaredConstructors()) {
			Method cst=new Method();
			cst.setConstructor(true);
			cst.setModifier(c.getModifiers());
			cst.setName(c.getName());
			cst.setReturntype(null);
			cst.setParameters(Arrays.asList(c.getParameters()));
		}
		return constructors;
	}
	
	public List<Method> parseMethods(){
		List<Method> methodes=new Vector<>();
		for(java.lang.reflect.Method m:classtoParse.getDeclaredMethods()) {
			Method met=new Method();
			met.setConstructor(false);
			met.setModifier(m.getModifiers());
			met.setName(m.getName());
			met.setReturntype(m.getReturnType().getSimpleName());
			met.setParameters(Arrays.asList(m.getParameters()));
		}
		return methodes;
	}
	
	public boolean isInterface() {
		return classtoParse.isInterface();
	}
	
	public boolean isEnnum() {
		return classtoParse.isEnum();
	}
	
	public boolean isAnnotation() {
		return classtoParse.isAnnotation();
	}
}
