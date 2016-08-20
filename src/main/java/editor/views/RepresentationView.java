/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
 */

package editor.views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import xml.Element;
import editor.controllers.RepresentationController;

public class RepresentationView extends JFrame {
	private final String TAG_STR = "Tag";
	private final String ATTRIBUTE_VALUE_STR = "Attribute Value";
	
	private class RepComboBoxActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String repChoice = (String) repComboBox.getSelectedItem();
			boolean attBoxVisible = attributeComboBox.isVisible();
			if (repChoice.equals(ATTRIBUTE_VALUE_STR) && !attBoxVisible) {
				attributeComboBox.setVisible(true);
			} else if (!repChoice.equals(ATTRIBUTE_VALUE_STR)) {
				attributeComboBox.setVisible(false);
			}
		}
	}
	
	private JComboBox<String> repComboBox;
	private JComboBox<String> attributeComboBox;
	private Element elem;
	private JCheckBox setForAllCheckBox;
	
	public RepresentationView(Element elem) {
		this.elem = elem;
		
        setTitle("Element Representation");
        setSize(300, 200);
        setLayout(new FlowLayout());

        add(new JLabel("Element"));
        JTextField elemField = new JTextField(elem.getTag(), 20);
        elemField.setEditable(false);

        add(new JLabel("Representation"));
        String[] repChoices = {TAG_STR, ATTRIBUTE_VALUE_STR};
        repComboBox = new JComboBox<String>(repChoices);
        RepComboBoxActionListener listener = new RepComboBoxActionListener();
        repComboBox.addActionListener(listener);
        if (elem.getNumberOfAttributes() == 0) {
        	repComboBox.setEnabled(false);
        }
        add(repComboBox);

        add(new JLabel("Set for all of this type of element"));
        setForAllCheckBox = new JCheckBox();
        add(setForAllCheckBox);
        
        String[] atts = new String[elem.getNumberOfAttributes()];
        int idx = 0;
        for (String att : elem.attributeNames()) {
        	atts[idx] = att;
        	idx++;
        }
        attributeComboBox = new JComboBox<String>(atts);
        attributeComboBox.setVisible(false);
        add(attributeComboBox);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new RepresentationController(this));
        add(okButton);

        setVisible(true);
	}
	
	public Element getElement() {
		return elem;
	}
	
	public Element.RepresentationType getRepresentationType() {
		Element.RepresentationType repType;
		String repStr = (String) repComboBox.getSelectedItem();
		if (repStr.equalsIgnoreCase(TAG_STR)) {
			repType = Element.RepresentationType.TAG;
		} else if (repStr.equals(ATTRIBUTE_VALUE_STR)) {
			repType = Element.RepresentationType.ATTRIBUTE_VALUE;
		} else {
			repType = Element.RepresentationType.NONE;
		}
		return repType;
	}
	
	public String getAttributeName() {
		String attName = null;
		Element.RepresentationType repType = getRepresentationType();
		if (repType == Element.RepresentationType.ATTRIBUTE_VALUE) {
			attName = (String) attributeComboBox.getSelectedItem();
		}
		return attName;
	}
	
	public boolean setForAll() {
		return setForAllCheckBox.isSelected();
	}
}
