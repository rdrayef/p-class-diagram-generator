package org.mql.java.models;

import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Vector;

import org.mql.java.helpers.ParseHelper;

public class Method {
	private String name;
	private List<Parameter> parameters;
	private int modifier;
	private boolean isConstructor;
	private String returntype;

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

	public String getReturntype() {
		return returntype;
	}

	public void setReturntype(String returntype) {
		this.returntype = returntype;
	}

	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(); 
		sb.append(ParseHelper.getModifiers(modifier).getLabel()+" ");
		sb.append(getName());
		    sb.append("(");
		    for (int i = 0; i < getParameters().size(); i++) {
		        sb.append(getParameters().get(i).getName());
		        sb.append(" "+ParseHelper.getShortForm(getParameters().get(i).getParameterizedType()));
		        if (i < getParameters().size() - 1) {
		            sb.append(", ");
		        }
		    }
		    sb.append(")");
		    if(!isConstructor)
		    sb.append(": "+returntype);
		    return sb.toString();
	}
	
	
	
	

}
