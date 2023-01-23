package org.mql.java.ui.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.mql.java.helpers.UiUtils;
import org.mql.java.models.Model;
import org.mql.java.models.Package;
import org.mql.java.ui.BoxPanel;
import org.mql.java.ui.WrapLayout;

public class PackageUI extends BoxPanel implements Movable {

	private static final long serialVersionUID = 1L;

	private Package Package;
	private List<ModelUI> uiModels;

	private JPanel titlePanel;
	private JPanel modelsPanel;

	private int eX, eY;

	public PackageUI(Package Package) {
		this.Package = Package;
		uiModels = new Vector<>();

		setOpaque(false);
		drawTitle(4);
		drawModels(15);

		setSize(getPreferredSize());

		addMouseListener(new CustomMouseListener());
		addMouseMotionListener(new CustomMouseMotionListener());
	}

	private class CustomMouseListener implements MouseListener {

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

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
			// TODO Auto-generated method stub

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
	
	private void drawModels(int padding) {
		modelsPanel = new JPanel(null);
		
		modelsPanel.setLayout(new WrapLayout(FlowLayout.LEFT, padding, padding));
		modelsPanel.setBorder(new LineBorder(Color.black, 1));
		modelsPanel.setBackground(UiUtils.rgbColor(253, 239, 231));
		
		for (Model model : Package.getModels()) {
			ModelUI uiModel = new ModelUI(model);
			uiModel.setLocation(10, 10);
			modelsPanel.add(uiModel);
			
			uiModels.add(uiModel);
		}
		
		modelsPanel.setSize(600, 200);
		add(modelsPanel);
	}
	
	private void drawTitle(int padding) {
		titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		titlePanel.setSize(100, 100);
		CustomLabel titleLabel = new CustomLabel(Package.getName());
		
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEFT, padding, padding));	
		p.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.black));
		p.setBackground(UiUtils.rgbColor(253, 239, 231));
		p.add(titleLabel);
		
		titlePanel.add(p);
		
		add(titlePanel);
	}
	
	public List<ModelUI> getModelUIs() {
		return uiModels;
	}
	

	@Override
	public void move(MouseEvent e) {
		setLocation((getX() + e.getX() - eX), (getY() + e.getY() - eY));


	}

}
