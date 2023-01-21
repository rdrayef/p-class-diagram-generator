package org.mql.java.parsers;

import org.mql.java.models.Project;
import org.mql.java.xml.Mapper;
import org.mql.java.xml.XMLNode;
import org.mql.java.xml.XMLNodeMapper;

public class ProjectDomParser {
	private XMLNode root;

	public ProjectDomParser(Project proj) {
		Mapper mapper = new XMLNodeMapper();
		root = mapper.objectToXMLNode(proj);
	}

	public void generate() {
		root.generate();
	}

	public XMLNode getRoot() {
		return root;
	}

}
