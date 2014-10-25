package editor.views;

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

    /**
     * Initialize the AttributesPanelView with no data
     * @param element The element whose attributes this view represents
     */
    public AttributesPanelView(Element element) {
        elem = element;

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
     */
    public void addAttribute() {
        if (elem == null) {
            return; // Bail out if no element is set
        }

        System.out.println("[DEBUG] Adding attribute");

        int numRows = layout.getRows();
        if (getNumberOfAttributeRows() >  0) {
            layout.setRows(numRows + 1);
        }

        String attName;
        attName = DEFAULT_ATTRIBUTE_BASE_NAME + Integer.toString(numRows);
        JTextField nameField = new JTextField(attName, 10);
        attributesPanel.add(nameField);
        attributeNameFields.add(nameField);

        JTextField valueField = new JTextField(DEFAULT_ATTRIBUTE_VALUE, 10);
        attributesPanel.add(valueField);
        attributeValueFields.add(valueField);

        elem.setAttribute(attName, DEFAULT_ATTRIBUTE_VALUE);

        attributesPanel.updateUI();
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
        layout.setColumns(NUM_COLS);
        layout.setRows(MIN_NUM_ROWS);
    }

    /**
     * Change the element this panel refers to
     * @param element The new element whose attributes to represent
     */
    public void setElement(Element element) {
        elem = element;
        // TODO
    }

    /**
     * Depopulate all data and remove association with an element
     */
    public void unsetElement() {
        elem = null;
        resetAttributes();
    }
}
