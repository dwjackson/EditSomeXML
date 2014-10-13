package editor.views;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * The AttributesPanelView is used to show all of the attributes of an XML
 * element in an editable form.
 * @see xml.Element
 */
public class AttributesPanelView extends JPanel {
    private JPanel attributesPanel;

    /**
     * Initialize the AttributesPanelView with no data
     */
    public AttributesPanelView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Attributes"));

        attributesPanel = new JPanel();
        attributesPanel.setLayout(new GridLayout());
        attributesPanel.add(new JTextField("", 10));
        attributesPanel.add(new JTextField("", 10));
        add(attributesPanel);
    }
}
