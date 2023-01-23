package org.mql.java.ui.components;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import org.mql.java.helpers.ParseHelper;
import org.mql.java.models.Attribute;

public class AttributeUI extends JPanel{
	private static final long serialVersionUID = 1L;

	protected Attribute umlAttribute;
	
	protected CustomLabel signatureLabel;
	
	public AttributeUI() {
		// TODO Auto-generated constructor stub
	}
	
	public AttributeUI(Attribute umlAttribute) {
		this.umlAttribute = umlAttribute;
		
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		signatureLabel = new CustomLabel();
		
		if (umlAttribute.isConstant()) {
			signatureLabel.addText(umlAttribute.getName());
		}
		else {			
			if (umlAttribute.isStatic()) {
				signatureLabel.setUnderline();
			}
			
			signatureLabel.addText(ParseHelper.getModifiers(umlAttribute.getModifier()).getLabel());
			signatureLabel.addText(umlAttribute.getName());
			
			
			if (umlAttribute.getName() != null) {
				signatureLabel.addText(": " + umlAttribute.getName());				
			}
		}

		add(signatureLabel);
	}

}
