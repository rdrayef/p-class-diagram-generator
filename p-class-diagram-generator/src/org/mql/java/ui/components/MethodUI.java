package org.mql.java.ui.components;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import org.mql.java.helpers.ParseHelper;
import org.mql.java.models.Method;

public class MethodUI extends JPanel {

	private static final long serialVersionUID = 1L;

	protected Method umlMethod;

	protected CustomLabel signatureLabel;

	public MethodUI() {
		// TODO Auto-generated constructor stub
	}

	public MethodUI(Method umlMethod) {
		this.umlMethod = umlMethod;

		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		signatureLabel = new CustomLabel();

		signatureLabel.addText(ParseHelper.getModifiers(umlMethod.getModifier()).getLabel());
		signatureLabel.addText(umlMethod.getName());

		signatureLabel.addText("(");

		for (int i = 0; i < umlMethod.getParameters().size(); i++) {
			signatureLabel.addText(ParseHelper.getShortForm(umlMethod.getParameters().get(i).getType()));
			if (i < umlMethod.getParameters().size() - 1) {
				signatureLabel.addText(", ");
			}
		}

		signatureLabel.addText(")");

		if (umlMethod.getName() != null) {
			signatureLabel.addText(": " + umlMethod.getName());
		}

		add(signatureLabel);

	}
}
