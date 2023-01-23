package org.mql.java.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import org.mql.java.models.RelationShip;

public class RelationUI extends JPanel implements Movable {
	
private static final long serialVersionUID = 1L;
	
	private RelationShip relation;
	private ProjectUI project;
	
	private Point p1, p2;
	

	public RelationUI(RelationShip relationShip,ProjectUI projectUI) {
		this.relation = relationShip;
		this.project = projectUI;
		setSize(500, 500);
		drawRelation();
	}

	private void drawRelation() {
		ModelUI childJumlClassifier = project.getJumlClassifier(relation.getSource());
		ModelUI parentJumlClassifier = project.getJumlClassifier(relation.getTarget());
				
		p1 = new Point(childJumlClassifier.getX(), childJumlClassifier.getY());
		p2 = new Point(parentJumlClassifier.getX(), parentJumlClassifier.getY());
		
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		

		g.setColor(Color.black);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
	}

	@Override
	public void move(MouseEvent e) {
		
	}

}
