package org.mql.java;

import java.net.MalformedURLException;

import org.mql.java.parsers.ProjectParser;

public class RunParser {

	public RunParser(String path) {
		try {
			new ProjectParser(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new RunParser("C:\\Users\\redou\\Documents\\MQL-2023\\Java\\projects\\p03-annotation-reflection");
	}

}
