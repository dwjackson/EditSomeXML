package editor.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The AttributesPanelView is used to show all of the attributes of an XML
 * element in an editable form.
 * @see xml.Element
 */
public class AttributesPanelView extends JPanel {
    private JPanel attributesPanel;
    private ArrayList<JTextField> attributeNameFields;
    private ArrayList<JTextField> attributeValueFields;
    private GridLayout layout;
    private final int NUM_COLS = 2;

    /**
     * Initialize the AttributesPanelView with no data
     */
    public AttributesPanelView() {
        attributeNameFields = new ArrayList<JTextField>();
        attributeValueFields = new ArrayList<JTextField>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Attributes"));

        attributesPanel = new JPanel();
        layout = new GridLayout();
        layout.setColumns(NUM_COLS);
        attributesPanel.setLayout(layout);
        addAttribute();
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
        System.out.println("[DEBUG] Adding attribute");

        if (getNumberOfAttributes() >  0) {
            int numRows = layout.getRows();
            layout.setRows(numRows + 1);
        }

        JTextField nameField = new JTextField("", 10);
        attributesPanel.add(nameField);
        attributeNameFields.add(nameField);

        JTextField valueField = new JTextField("", 10);
        attributesPanel.add(valueField);
        attributeValueFields.add(valueField);

        attributesPanel.updateUI();
    }

    public int getNumberOfAttributes() {
        return attributeNameFields.size();
    }
}
