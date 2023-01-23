package org.mql.java;

import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.mql.java.models.Project;
import org.mql.java.parsers.ProjectParser;
import org.mql.java.ui.components.ProjectUI;

public class RunParser extends JFrame{

	private static final long serialVersionUID = 1L;

	public RunParser(String path) {
		try {
			ProjectParser project=new ProjectParser(path);
			drawProject(project.getParsedproject());
			config();
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
	
	private void config() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	private void drawProject(Project project) {
		if (project != null) {
			JScrollPane panelPane = new JScrollPane(new ProjectUI(project));
			setContentPane(panelPane);
		}
	}

}
