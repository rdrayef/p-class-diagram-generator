package org.mql.java.reflection;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Vector;

import org.mql.java.models.Class;
import org.mql.java.models.RelationShip;

public class Wrapper {
	private static Wrapper wrapper;
	private List<RelationShip> relationships;
	private List<Class> umlClasses;

	public static Wrapper getInstance() {
		if(wrapper==null)
			wrapper=new Wrapper();
		return wrapper;
	}
	
	private Wrapper() {
		relationships=new Vector<>();
		umlClasses=new Vector<>();
	}
	
	

	
	
}
