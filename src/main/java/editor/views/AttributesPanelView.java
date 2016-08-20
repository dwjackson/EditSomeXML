/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
 */

package editor.views;

import editor.controllers.AttributesPanelController;
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
    private ArrayList<MonospaceJTextField> attributeNameFields;
    private ArrayList<MonospaceJTextField> attributeValueFields;
    private ArrayList<JButton> deleteButtons;
    private GridLayout layout;
    private final int NUM_COLS = 3;
    private final int MIN_NUM_ROWS = 1;
    private ArrayList<AttributeDocumentListener> listeners;
    private AttributesPanelController controller;
    private ElementEditorView editorView;

    /**
     * Initialize the AttributesPanelView with no data
     * @param element The element whose attributes this view represents
     * @param editorView The editor with which these attributes are associated
     */
    public AttributesPanelView(Element element, ElementEditorView editorView) {
        elem = element;
        this.editorView = editorView;

        listeners = new ArrayList<AttributeDocumentListener>();

        attributeNameFields = new ArrayList<MonospaceJTextField>();
        attributeValueFields = new ArrayList<MonospaceJTextField>();
        deleteButtons = new ArrayList<JButton>();

        controller = new AttributesPanelController(this);

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

        editorView.stopObserving();

        // Notify controller to add attribute to element model
        controller.attachAttributeToElement(name, value);

        // Add the attribute to the UI
        if (getNumberOfAttributeRows() >  0) {
            layout.setRows(layout.getRows() + 1);
        }

        MonospaceJTextField nameField = new MonospaceJTextField(name, 10);
        attributesPanel.add(nameField);
        attributeNameFields.add(nameField);

        MonospaceJTextField valueField = new MonospaceJTextField(value, 10);
        attributesPanel.add(valueField);
        attributeValueFields.add(valueField);

        int attIndex = layout.getRows() - 1;

        AttributeDocumentListener listener;
        listener = new AttributeDocumentListener(elem, attIndex, nameField,
                valueField, editorView);
        nameField.getDocument().addDocumentListener(listener);
        valueField.getDocument().addDocumentListener(listener);
        listeners.add(listener);

        JButton deleteButton = new JButton("Delete Attribute");
        deleteButton.addActionListener(new DeleteAttributeListener(attIndex, elem, this));
        deleteButtons.add(deleteButton);
        attributesPanel.add(deleteButton);

        attributesPanel.updateUI();

        editorView.startObserving();
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
        for (MonospaceJTextField field : attributeNameFields) {
            attributesPanel.remove(field);
        }
        for (MonospaceJTextField field : attributeValueFields) {
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

    /**
     * Get the element associated with this panel
     */
    public Element getElement() {
        return elem;
    }
}
