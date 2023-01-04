package org.mql.java.helpers;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.mql.java.enums.Modifiers;
import org.mql.java.models.Attribute;
import org.mql.java.models.Interface;
import org.mql.java.models.Method;
import org.mql.java.models.Model;

public class ParseHelper {

	public ParseHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public static Modifiers getModifiers(int modifier) {
		Modifiers symbol;
		switch (modifier) {
        case 1:
            symbol = Modifiers.PUBLIC;
            break;
        case 2:
            symbol = Modifiers.PRIVATE;
            break;
        case 4:
            symbol = Modifiers.PROTECTED;
            break;
        case 8:
            symbol = Modifiers.STATIC;
            break;
        case 16:
            symbol = Modifiers.FINAL;
            break;
        default:
            symbol = Modifiers.UNKNOWN;
            break;
		}
		return symbol;
	}
	
	public static void getPackages(String dir, Set<String> packages) {
		File directory = new File(dir);
		File[] filesList = directory.listFiles();

		// Iterate through the files and add directories to the list of packages
		for (File file : filesList) {
			if (file.isFile() && file.getName().endsWith(".class")) {
				String path = file.getPath();
				String packName = path.substring(path.indexOf("bin") + 4, path.lastIndexOf('\\'));
				packages.add(packName.replace('\\', '.'));
			} else if (file.isDirectory()) {
				// Call the recursive method with the directory as input
				getPackages(file.getAbsolutePath(), packages);
			}
		}
	}
	
	public static List<Attribute> parseAttributes(Class<?> classtoParse) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		List<Attribute> attributes=new Vector<>();
		for(Field field:classtoParse.getDeclaredFields()) {
			Attribute attr=new Attribute();
			attr.setModifier(field.getModifiers());
			attr.setName(field.getName());
			attr.setType(field.getGenericType());
			//attr.setInitialValue(getFieldValue(obj,field.getName()));
			attributes.add(attr);
		}
		return attributes;
	}
	
	public static List<Method> parseConstructors(Class<?> classtoParse){
		List<Method> constructors=new Vector<>();
		for(Constructor<?> c:classtoParse.getDeclaredConstructors()) {
			c.setAccessible(true);
			Method cst=new Method();
			cst.setConstructor(true);
			cst.setModifier(c.getModifiers());
			cst.setName(c.getName());
			cst.setReturntype(null);
			cst.setParameters(Arrays.asList(c.getParameters()));
			constructors.add(cst);
		}
		return constructors;
	}
	
	public static List<Method> parseMethods(Class<?> classtoParse){
		List<Method> methodes=new Vector<>();
		for(java.lang.reflect.Method m:classtoParse.getDeclaredMethods()) {
			m.setAccessible(true);
			Method met=new Method();
			met.setConstructor(false);
			met.setModifier(m.getModifiers());
			met.setName(m.getName());
			met.setReturntype(m.getReturnType().getSimpleName());
			met.setParameters(Arrays.asList(m.getParameters()));
			methodes.add(met);
		}
		return methodes;
	}
	
	public static boolean isInterface(Class<?> classtoParse) {
		return classtoParse.isInterface();
	}
	
	public static boolean isEnnum(Class<?> classtoParse) {
		return classtoParse.isEnum();
	}
	
	public static boolean isAnnotation(Class<?> classtoParse) {
		return classtoParse.isAnnotation();
	}
	
	private static Object getFieldValue(Object obj, String fieldName) {
	    try {
	        Class<?> c = obj.getClass();
	        Field f = c.getDeclaredField(fieldName);
	        f.setAccessible(true);  // make the field accessible
	        return f.get(obj);  // get the value of the field
	    } catch (NoSuchFieldException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static Class<?> checkClassType(Class<?> c) {
		if(c.isInterface()) {
			return Interface.class;
		}else {
			return org.mql.java.models.Class.class;
		}
		
	}

}
