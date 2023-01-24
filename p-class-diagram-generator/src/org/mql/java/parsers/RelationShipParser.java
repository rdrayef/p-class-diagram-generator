package org.mql.java.parsers;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.enums.RelationType;
import org.mql.java.helpers.ParseHelper;
import org.mql.java.models.Attribute;
import org.mql.java.models.Interface;
import org.mql.java.models.Method;
import org.mql.java.models.Model;
import org.mql.java.models.RelationShip;

public class RelationShipParser {

	public RelationShipParser() {
		// TODO Auto-generated constructor stub
	}
	
	public List<RelationShip> parse(Model target,Model source){

		if (source != null && target != null) {
			List<RelationShip> relations = new Vector<>();

			if (!source.equals(target)) {
                RelationShip dependency = parseDependency(source, target);
                if (dependency != null) {
                    relations.add(dependency);
                }
                
                RelationShip association = parseAssociation(source, target);
                if (association != null) {
                    relations.remove(dependency);
                    relations.add(association);
                }
                
                RelationShip aggregation = null;
                if (association != null) {
                    aggregation = parseAggregation(source, target);
                    if (aggregation != null) {
                        relations.remove(association);
                        relations.add(aggregation);
                    }                   
                }
                
                RelationShip composition = parseComposition(source, target);
                if (composition != null) {
                    relations.remove(aggregation);
                    relations.add(composition);
                }
                
                RelationShip realization = parseRealization(source, target);
                if (realization != null) {
                    relations.add(realization);
                }
                
                RelationShip generalization = parseGeneralization(source, target);
                if (generalization != null) {
                    relations.add(generalization);
                }
            }
			return relations;

		}
		return null;

	}

	public  RelationShip parseGeneralization(Model source, Model target) {
		if (target.getParent() != null) {
			if (target.getParent().equals(source.getName())) {
				System.out.println("GENERALIZATION");
				return new RelationShip(target, source, RelationType.GENERALIZATION);
			}
		}
		return null;
	}

	public  RelationShip parseRealization(Model source, Model target) {
		if (target instanceof org.mql.java.models.Class && source instanceof Interface) {
			org.mql.java.models.Class clazz = (org.mql.java.models.Class) target;
			if (clazz.getImplinterfaces() != null) {
				if (clazz.getImplinterfaces().contains(target.getName())) {
					System.out.println("REALIZATION");
					return new RelationShip(target, source, RelationType.REALIZATION);
				}
			}

		}

		return null;
	}

	public  RelationShip parseComposition(Model source, Model target) {
		if (target instanceof org.mql.java.models.Class && source instanceof org.mql.java.models.Class) {

			if (target.getName().contains(source.getName())) {
				System.out.println("COMPOSITION");
				return new RelationShip(target, source, RelationType.COMPOSITION);
			} else {
				Attribute attribute = ParseHelper.childInParentAttributes(target, source);
				if (attribute != null && attribute.isMultiple() && attribute.isFinal()) {
					if (ParseHelper.parameterInAtLeastOneConstructor(attribute.getType().getTypeName(), source)) {
						System.out.println("COMPOSITION");
						return new RelationShip(target, source, RelationType.COMPOSITION);
					}
				}
			}
		}

		return null;
	}

	public  RelationShip parseAggregation(Model source, Model target) {
		if (target instanceof org.mql.java.models.Class && source instanceof org.mql.java.models.Class) {
			Attribute attribute = ParseHelper.childInParentAttributes(target, source);

			if (attribute != null && attribute.isMultiple()) {
				if (ParseHelper.parameterInAtLeastOneConstructor(attribute.getType().getTypeName(), source)) {
					System.out.println("AGGREGATION");
					return new RelationShip(target, source, RelationType.AGGREGATION);
				}
			}
		}

		return null;
	}

	public  RelationShip parseAssociation(Model source, Model target) {
		if (target instanceof org.mql.java.models.Class && source instanceof org.mql.java.models.Class) {
			if (ParseHelper.childInParentAttributes(target, source) != null) {
				System.out.println("ASSOCIATION");
				return new RelationShip(target, source, RelationType.ASSOCIATION);
			}
		}

		return null;
	}

	public  RelationShip parseDependency(Model source, Model target) {
		RelationShip dependency = null;
		for (Method method : source.getMethods()) {
			if (ParseHelper.isMethodParameter(target.getName(), method)) {
				System.out.println("DEPENDENCY");
				dependency = new RelationShip(target, source, RelationType.DEPENDENCY);
			}

		}

		return dependency;
	}


}
