package org.mql.java.models;

import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Vector;

public class Method {
	private String name;
	private List<Parameter> parameters;
	private int modifier;
	private boolean isConstructor;
	private String type;

	public Method() {
		parameters=new Vector<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public int getModifier() {
		return modifier;
	}

	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	public boolean isConstructor() {
		return isConstructor;
	}

	public void setConstructor(boolean isConstructor) {
		this.isConstructor = isConstructor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getParameterizedUMLString(){
		StringBuffer umlStr = new StringBuffer();
		if(parameters != null){
			umlStr.append(Modifier.toString(modifier));
			umlStr.append(name + "(");
			for(int i=0; i < parameters.size(); i++){
				umlStr.append(parameters.get(i).getName() + ": " + parameters.get(i).getType());
			}
			umlStr.toString();
			//String.join(",", umlStr);
			umlStr.append(")");
		}else {
			umlStr.append(Modifier.toString(modifier) + name + "()");
		}
		
		return (type != null) ? umlStr.append(": " + type + "\n").toString() : umlStr.append(" \n").toString();
	}
	

}
