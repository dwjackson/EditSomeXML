package editor.views;

import javax.swing.*;
import java.awt.*;
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

    /**
     * Initialize the AttributesPanelView with no data
     */
    public AttributesPanelView() {
        attributeNameFields = new ArrayList<JTextField>();
        attributeValueFields = new ArrayList<JTextField>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Attributes"));

        attributesPanel = new JPanel();
        attributesPanel.setLayout(new GridLayout());
        addAttribute();
        add(attributesPanel);
    }

    /**
     * Add more space for another attribute to be added. This adds a field for
     * the attribute's name and another for its value.
     */
    public void addAttribute() {
        JTextField nameField = new JTextField("", 10);
        attributesPanel.add(nameField);
        attributeNameFields.add(nameField);

        JTextField valueField = new JTextField("", 10);
        attributesPanel.add(valueField);
        attributeValueFields.add(valueField);
    }
}
