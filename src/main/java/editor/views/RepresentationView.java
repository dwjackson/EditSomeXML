/*
 * EditSomeXML is a graphical XML editor
 * 
 * Copyright (C) 2014  David Jackson
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package editor.views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.lang.model.element.ElementKind;
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
        JCheckBox setForAllCheckBox = new JCheckBox();
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
}
