package org.mql.java.helpers;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.mql.java.enums.Modifiers;
import org.mql.java.models.Attribute;
import org.mql.java.models.Interface;
import org.mql.java.models.Method;

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
		default:
			symbol = Modifiers.PACKAGE;
			break;
		}
		return symbol;
	}

	public static void getPackages(String dir, Set<String> packages) {
		File directory = new File(dir);
		// Iterate through the files and add directories to the list of packages
		File[] filesList = directory.listFiles();
		for (File file : filesList) {
			if (isAValidClassFile(file)) {
				String path = file.getPath();
				String packName = path.substring(path.indexOf("bin") + 4, path.lastIndexOf('\\'));
				packages.add(packName.replace('\\', '.'));
			} else if (file.isDirectory()) {
				// Call the recursive method with the directory as input
				getPackages(file.getAbsolutePath(), packages);
			}
		}
	}

	public static Set<Attribute> parseAttributes(Class<?> classtoParse)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Set<Attribute> attributes = new HashSet<>();
		for (Field field : classtoParse.getDeclaredFields()) {
			if (!field.getName().contains("$")) {
				Attribute attr = new Attribute();
				attr.setModifier(field.getModifiers());
				attr.setName(field.getName());
				attr.setType(field.getGenericType());
				if (classtoParse.isEnum())
					attr.setConstant(true);
				// attr.setInitialValue(getFieldValue(obj,field.getName()));
				attributes.add(attr);
			}
		}
		return attributes;
	}

	public static Set<Method> parseConstructors(Class<?> classtoParse) {
		Set<Method> constructors = new HashSet<>();
		for (Constructor<?> c : classtoParse.getDeclaredConstructors()) {
			c.setAccessible(true);
			Method cst = new Method();
			cst.setConstructor(true);
			cst.setModifier(c.getModifiers());
			cst.setName(classtoParse.getSimpleName());
			cst.setReturntype(null);
			cst.setParameters(Arrays.asList(c.getParameters()));
			constructors.add(cst);
		}
		return constructors;
	}

	public static Set<Method> parseMethods(Class<?> classtoParse) {
		Set<Method> methodes = new HashSet<>();
		for (java.lang.reflect.Method m : classtoParse.getDeclaredMethods()) {
			m.setAccessible(true);
			Method met = new Method();
			met.setConstructor(false);
			met.setModifier(m.getModifiers());
			met.setName(m.getName());
			met.setReturntype(ParseHelper.getShortForm(m.getGenericReturnType()));
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

	public static Class<?> checkClassType(Class<?> c) {
		if (c.isInterface()) {
			return Interface.class;
		} else {
			return org.mql.java.models.Class.class;
		}

	}

	public static boolean isAValidClassFile(File file) {
		if (doesFileExists(file)) {
			if (file.isFile()) {
				String fileName = file.getAbsolutePath();
				if (fileName.endsWith(".class") && !fileName.matches(".*\\$[0-9]+.*")
						&& !fileName.equalsIgnoreCase("RunParser.class")) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean doesFileExists(File file) {
		if (!file.exists()) {
			return false;
		}
		return true;
	}

	public static String getShortForm(Type type) {
		if (type instanceof ParameterizedType) {
			// Get the generic type definition
			Type genericType = ((ParameterizedType) type).getRawType();

			// Get the generic arguments
			Type[] genericArguments = ((ParameterizedType) type).getActualTypeArguments();

			// Get the short form of the generic type
			String shortForm = ((Class<?>) genericType).getSimpleName();

			// Append the arguments to the name
			shortForm += "<";
			for (int i = 0; i < genericArguments.length; i++) {
				shortForm += getShortForm(genericArguments[i]);
				if (i < genericArguments.length - 1) {
					shortForm += ", ";
				}
			}
			shortForm += ">";

			return shortForm;
		} else if (type instanceof Class) {
			return ((Class<?>) type).getSimpleName();
		} else {
			return type.getTypeName();
		}
	}

}
