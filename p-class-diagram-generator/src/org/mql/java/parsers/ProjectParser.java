package org.mql.java.parsers;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.mql.java.enums.Modifiers;
import org.mql.java.helpers.ParseHelper;
import org.mql.java.models.Attribute;
import org.mql.java.models.Interface;
import org.mql.java.models.Method;
import org.mql.java.models.Model;
import org.mql.java.models.Package;


public class ProjectParser{
	private String path;

	public ProjectParser(String path) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException  {
		this.path=path+"\\bin\\";
		listPackages();
	}
	
	
	

	 public void listPackages() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		// Get a list of all the files in the /bin directory
		Set<String> packageList = new HashSet<>();
		ParseHelper.getPackages(path,packageList);
		System.out.println(Arrays.toString(packageList.toArray()));
		for (String packageName : packageList) {
			
			PackageParser packageParser = new PackageParser(packageName,path);
			Package pck=new Package(packageName);
			Model model;
			Set<Model> models=new HashSet<>();
			
			for (String className : packageParser.getSubClasses()) {
				if(!className.matches(".*\\$[0-9]+.*")) {

					ClassParser parser = new ClassParser(className,path);
					Class<?> current=parser.getClasstoParse();
					
					if(ParseHelper.checkClassType(current)==Interface.class) {
						Interface intr=new Interface(current.getSimpleName());
						//TODO:get extended interfaces
						model=(Model) intr;
						
					}else {
						model=new org.mql.java.models.Class(current.getSimpleName());
					}
					model.setAttributes(parser.parseAttributes());
					List<Method> methods=new Vector<>();
					methods.addAll(parser.parseConstructors());
					methods.addAll(parser.parseMethods());
					model.setMethods(methods);
					
					System.out.println(model.getName()+":");
					for(Attribute atr:model.getAttributes()) {
						System.out.println(atr.getUMLString());
					}
					for(Method met:model.getMethods()) {
						System.out.println(met.getParameterizedUMLString());
					}
					
					models.add(model);
				}
			}
			
			pck.setModels(models);
		}
	 }
	 
	 
	
	 

	


}