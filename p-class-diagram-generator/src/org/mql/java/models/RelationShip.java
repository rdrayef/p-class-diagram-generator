package org.mql.java.models;

import org.mql.java.enums.RelationType;

public class RelationShip {
	private Model source;
	private Model target;
	private RelationType relationType;

	public RelationShip() {

	}

	public RelationShip(Model source, Model target, RelationType relationType) {
		this.source = source;
		this.target = target;
		this.relationType = relationType;
	}

	public Model getSource() {
		return source;
	}

	public void setSource(Model source) {
		this.source = source;
	}

	public Model getTarget() {
		return target;
	}

	public void setTarget(Model target) {
		this.target = target;
	}

	public RelationType getRelationType() {
		return relationType;
	}

	public void setRelationType(RelationType relationType) {
		this.relationType = relationType;
	}

	@Override
	public String toString() {
		return source.getName() + " " + relationType.getSymbol() + " " + target.getName();
	}
}
