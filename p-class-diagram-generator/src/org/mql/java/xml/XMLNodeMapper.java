package org.mql.java.xml;

import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Arrays;

import org.mql.java.helpers.ParseHelper;
import org.mql.java.models.Attribute;
import org.mql.java.models.Class;
import org.mql.java.models.Ennum;
import org.mql.java.models.Interface;
import org.mql.java.models.Method;
import org.mql.java.models.Model;
import org.mql.java.models.Package;
import org.mql.java.models.Project;
import org.mql.java.models.RelationShip;

public class XMLNodeMapper implements Mapper {

	public XMLNodeMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public XMLNode objectToXMLNode(Object obj) {
		XMLNode node = null;
		if (obj != null) {
			if (obj instanceof Project) {
				Project currentObj = (Project) obj;
				node = new XMLNode("project", 1);
				node.setAttribute("name", currentObj.getName());
				if(currentObj.getPackages().size()>0) {
					XMLNode packagesNode = new XMLNode("packages", 1);
					XMLNode packageNode;
					for (Package aPackage : currentObj.getPackages()) {
						packageNode = objectToXMLNode(aPackage);
						packagesNode.appendChild(packageNode);
					}
					node.appendChild(packagesNode);
				}
				
				if (currentObj.getRelations().size() > 0) {
					XMLNode relationsNode = new XMLNode("relations", 1);
					XMLNode relationNode;
					for (RelationShip aRelation : currentObj.getRelations()) {
						relationNode = new XMLNode("relation", 1);
						relationNode.setAttribute("type", aRelation.getRelationType().name());
						relationNode.setAttribute("parent", aRelation.getSource().getName());
						relationNode.setAttribute("child", aRelation.getTarget().getName());
						relationsNode.appendChild(relationNode);
					}
					node.appendChild(relationsNode);
				}

				return node;
			} else if (obj instanceof Package) {
				Package currentObj = (Package) obj;
				node = new XMLNode("package", 1);
				node.setAttribute("name", currentObj.getName());
				XMLNode classesNode = new XMLNode("classes", 1);
				XMLNode classNode;
				for (Model aModel : currentObj.getModels()) {
					classNode = objectToXMLNode(aModel);
					classesNode.appendChild(classNode);
				}
				node.appendChild(classesNode);
				return node;
			} else if (obj instanceof Model) {
				Model currentObj = (Model) obj;

				if (currentObj instanceof Ennum) {
					node = new XMLNode("enumeration", 1);
					XMLNode constantsNode = new XMLNode("constants", 1);
					XMLNode constantNode;
					for (Attribute aConsant : ((Ennum) currentObj).getAttributes()) {
						constantNode = new XMLNode("constant", 1);
						constantNode.setAttribute("value", aConsant.getName());
						constantsNode.appendChild(constantNode);
					}
					node.appendChild(constantsNode);
				} else {
					if (currentObj instanceof Interface) {
						node = new XMLNode("interface", 1);
					} else if(currentObj instanceof Class) {
						node = new XMLNode("class", 1);
					}
					if (currentObj.getAttributes().size() > 1
							|| (!currentObj.getName().contains("$") && currentObj.getAttributes().size() > 0)) {
						XMLNode fieldsNode = new XMLNode("fields", 1);
						XMLNode fieldNode;
						for (Attribute aField : currentObj.getAttributes()) {
							if (!aField.getName().contains("this$")) {
								fieldNode = new XMLNode("field", 1);
								fieldNode.setAttribute("name", aField.getName());
								fieldNode.setAttribute("type", ParseHelper.getShortForm(aField.getType()));
								fieldNode.setAttribute("visibility",
										ParseHelper.getModifiers(aField.getModifier()).label);
								fieldsNode.appendChild(fieldNode);
							}
						}
						node.appendChild(fieldsNode);
					}

					if (currentObj.getMethods().size() > 0) {
						XMLNode methodsNode = new XMLNode("methods", 1);
						XMLNode methodNode;
						for (Method aMethod : currentObj.getMethods()) {
							methodNode = new XMLNode("method", 1);
							methodNode.setAttribute("name", aMethod.getName());
							methodNode.setAttribute("visibility",
									ParseHelper.getModifiers(aMethod.getModifier()).label);

							if (aMethod.getReturntype() != null) {
								methodNode.setAttribute("returnType", aMethod.getReturntype());
							} else if (aMethod.isConstructor()) {
								methodNode.setAttribute("returnType", "");
							} else {
								methodNode.setAttribute("returnType", "void");
							}

							if (aMethod.getParameters().size() > 0) {
								XMLNode parameterTypes = new XMLNode("parameterTypes", 1);
								XMLNode parameterType;
								for (Parameter aParameter : aMethod.getParameters()) {
									parameterType = new XMLNode("parameterType", 1);
									parameterType.setAttribute("type", ParseHelper.getShortForm(aParameter.getType()));
									parameterTypes.appendChild(parameterType);
								}
								methodNode.appendChild(parameterTypes);
							}

							methodsNode.appendChild(methodNode);
						}
						node.appendChild(methodsNode);
					}

				}
				node.setAttribute("name", currentObj.getName());
				return node;
			}

		}

		return node;
	}

}
