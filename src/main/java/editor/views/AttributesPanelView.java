package editor.views;

import editor.views.viewlisteners.AttributeDocumentListener;
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
    private GridLayout layout;
    private final int NUM_COLS = 2;
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
        attributeNameFields.clear();
        attributeValueFields.clear();
        listeners.clear();
        layout.setColumns(NUM_COLS);
        layout.setRows(MIN_NUM_ROWS);
    }

    /**
     * Change the element this panel refers to
     * @param element The new element whose attributes to represent
     */
    public void setElement(Element element) {
        elem = element;
        // TODO: Load the attributes
        if (elem.getNumberOfAttributes() > 0) {
            for (String attName : elem.attributeNames()) {
                String attVal = elem.getAttribute(attName);
                addAttribute(attName, attVal);
            }
        }
    }

    /**
     * Depopulate all data and remove association with an element
     */
    public void unsetElement() {
        elem = null;
        resetAttributes();
    }
}
