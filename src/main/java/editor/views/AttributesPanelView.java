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

import editor.views.viewlisteners.AttributeDocumentListener;
import editor.views.viewlisteners.DeleteAttributeListener;
import xml.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The AttributesPanelView is used to show all of the attributes of an XML
 * element in an editable form.
 * @see xml.Element
 */
public class AttributesPanelView extends JPanel {
    private final String DEFAULT_ATTRIBUTE_BASE_NAME = "attribute";
    private final String DEFAULT_ATTRIBUTE_VALUE = "";

    private Element elem;
    private JPanel attributesPanel;
    private ArrayList<JTextField> attributeNameFields;
    private ArrayList<JTextField> attributeValueFields;
    private ArrayList<JButton> deleteButtons;
    private GridLayout layout;
    private final int NUM_COLS = 3;
    private final int MIN_NUM_ROWS = 1;
    private ArrayList<AttributeDocumentListener> listeners;

    /**
     * Initialize the AttributesPanelView with no data
     * @param element The element whose attributes this view represents
     */
    public AttributesPanelView(Element element) {
        elem = element;
        listeners = new ArrayList<AttributeDocumentListener>();

        attributeNameFields = new ArrayList<JTextField>();
        attributeValueFields = new ArrayList<JTextField>();
        deleteButtons = new ArrayList<JButton>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Attributes"));

        attributesPanel = new JPanel();
        layout = new GridLayout();
        layout.setColumns(NUM_COLS);
        attributesPanel.setLayout(layout);
        add(attributesPanel);

        JButton newAttributeButton = new JButton("Add Attribute");
        newAttributeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addAttribute();
            }
        });
        add(newAttributeButton);
    }

    /**
     * Add more space for another attribute to be added. This adds a field for
     * the attribute's name and another for its value.
     *
     * @param name The attribute's name
     * @param value The attribute's value
     */
    public void addAttribute(String name, String value) {
        if (elem == null) {
            return; // Bail out if no element is set
        }

        System.out.println("[DEBUG] Adding attribute");

        int numRows = layout.getRows();
        if (getNumberOfAttributeRows() >  0) {
            layout.setRows(numRows + 1);
        }

        JTextField nameField = new JTextField(name, 10);
        attributesPanel.add(nameField);
        attributeNameFields.add(nameField);

        JTextField valueField = new JTextField(value, 10);
        attributesPanel.add(valueField);
        attributeValueFields.add(valueField);

        AttributeDocumentListener listener;
        listener = new AttributeDocumentListener(elem, numRows-1, nameField,
                valueField);
        nameField.getDocument().addDocumentListener(listener);
        valueField.getDocument().addDocumentListener(listener);
        listeners.add(listener);

        int attIndex = numRows - 1;
        JButton deleteButton = new JButton("Delete Attribute");
        deleteButton.addActionListener(new DeleteAttributeListener(attIndex, elem, this));
        deleteButtons.add(deleteButton);
        attributesPanel.add(deleteButton);

        attributesPanel.updateUI();
    }

    /**
     * Add a default attribute
     */
    public void addAttribute() {
        int numRows = layout.getRows();
        String attName;
        attName = DEFAULT_ATTRIBUTE_BASE_NAME + Integer.toString(numRows);
        addAttribute(attName, DEFAULT_ATTRIBUTE_VALUE);
    }

    /**
     * Get the number of attribute rows available
     * @return the number of attribute rows available
     */
    public int getNumberOfAttributeRows() {
        return attributeNameFields.size();
    }

    public void resetAttributes() {
        for (JTextField field : attributeNameFields) {
            attributesPanel.remove(field);
        }
        for (JTextField field : attributeValueFields) {
            attributesPanel.remove(field);
        }
        for (JButton button : deleteButtons) {
            attributesPanel.remove(button);
        }
        attributeNameFields.clear();
        attributeValueFields.clear();
        deleteButtons.clear();
        listeners.clear();
        layout.setColumns(NUM_COLS);
        layout.setRows(MIN_NUM_ROWS);

        attributesPanel.updateUI();
    }

    /**
     * Change the element this panel refers to
     * @param element The new element whose attributes to represent
     */
    public void setElement(Element element) {
        elem = element;
        populate();
    }

    /**
     * Depopulate all data and remove association with an element
     */
    public void unsetElement() {
        elem = null;
        resetAttributes();
    }

    /**
     * Draw the attributes for the attached element
     */
    public void populate() {
        if (elem.getNumberOfAttributes() > 0) {
            for (String attName : elem.attributeNames()) {
                String attVal = elem.getAttribute(attName);
                addAttribute(attName, attVal);
            }
        }
    }
}
