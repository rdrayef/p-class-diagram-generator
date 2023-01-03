package org.mql.java.helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class CustomClassLoader extends ClassLoader{
	
	
	public static Class<?> loadClass(String name,String basePath) throws MalformedURLException, ClassNotFoundException {
		try {
			File file = new File(basePath);
			URL[] url = { file.toURI().toURL() };
			URLClassLoader urlcl = new URLClassLoader(url,Thread.currentThread().getContextClassLoader());
				return urlcl.loadClass(name);
			
		} catch (Exception ex) {
			System.out.println("Class Inexisstante");
			ex.printStackTrace();
		}
		return null;
	}

}
