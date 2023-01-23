package org.mql.java.ui.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;

import org.mql.java.models.Attribute;
import org.mql.java.models.Ennum;
import org.mql.java.models.Interface;
import org.mql.java.models.Method;
import org.mql.java.models.Model;
import org.mql.java.ui.BoxPanel;

public class ModelUI extends BoxPanel implements Movable {
	private static final long serialVersionUID = 1L;

	private Color color;

	private Model model;

	private TitlePanel titlePanel;
	private SectionPanel attributesPanel;
	private SectionPanel operationsPanel;

	private int eX, eY;

	public ModelUI(Model model) {
		this(model, Color.BLACK);
		drawTitlePanel();
		drawAttributesPanel();
		drawOperationsPanel();

		if (model instanceof Interface) {
			setBackground(new Color(184, 249, 168));
		} else if (model instanceof Ennum) {
			setBackground(new Color(209, 166, 253));
		} else {
			setBackground(Color.white);
		}

		setSize(getPreferredSize());
	}

	public ModelUI(Model model, Color color) {
		this.model = model;
		this.color = color;

		addMouseListener(new CustomMouseListener());
		addMouseMotionListener(new CustomMouseMotionListener());
	}

	private class CustomMouseListener implements MouseListener {

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			eX = e.getX();
			eY = e.getY();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}
	}

	private class CustomMouseMotionListener implements MouseMotionListener {
		@Override
		public void mouseMoved(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			move(e);
		}
	}

	private void drawTitlePanel() {
		titlePanel = new TitlePanel();
		add(titlePanel);
	}

	private void drawAttributesPanel() {
		attributesPanel = new SectionPanel();

		for (Attribute umlMember : model.getAttributes()) {
			attributesPanel.add(new AttributeUI(umlMember));
		}

		add(attributesPanel);
	}

	private void drawOperationsPanel() {
		operationsPanel = new SectionPanel();

		for (Method umlMember : model.getMethods()) {
			operationsPanel.add(new MethodUI(umlMember));

		}
		add(operationsPanel);
	}

	private class TitlePanel extends BoxPanel {
		private static final long serialVersionUID = 1L;

		public TitlePanel() {
			setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, color));

			if (model instanceof Interface) {
				add(new CustomLabel("<<interface>>"));
			} else if (model instanceof Ennum) {
				CustomLabel titleLabel = new CustomLabel("<<enum>>");
				add(titleLabel);
			}

			add(new CustomLabel(model.getName()));

			setOpaque(false);
		}
	}

	private class SectionPanel extends BoxPanel {
		private static final long serialVersionUID = 1L;

		public SectionPanel() {
			setBorder(1);
			setBorderTop(0);

			setOpaque(false);
		}
	}

	@Override
	public void move(MouseEvent e) {
		setLocation((getX() + e.getX() - eX), (getY() + e.getY() - eY));

	}

	public Model getModel() {
		return model;
	}

}
