package org.mql.java;

import java.net.MalformedURLException;

import org.mql.java.reflection.ProjectParser;

public class RunParser {

	public RunParser(String path) {
		try {
			new ProjectParser(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new RunParser("C:\\Users\\redou\\Documents\\MQL-2023\\Java\\projects\\DRAYEF Redouane-Generics\\bin\\org\\mql\\generics");
	}

}
