package editor.views;

import editor.controllers.CloneElementController;
import xml.Element;

import javax.swing.*;
import java.awt.*;

/**
 * The CloneElementView is the window that pops up when the user wants to
 * clone (deep-copy) and element in the XML tree
 */
public class CloneElementView extends JFrame {
    Element elem;

    public CloneElementView(ElementTreeView elementTreeView) {
        elem = null;

        setTitle("Clone Element");
        setSize(new Dimension(500, 100));
        setLayout(new FlowLayout());

        add(new JLabel("Element to Clone:"));
        JTextField tagField = new JTextField("", 20);
        tagField.setEditable(false);
        add(tagField);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new CloneElementController(this));
        add(okButton);

        elem = elementTreeView.getSelectedElement();
        if (elem != null) {
            tagField.setText(elem.getTag());
        } else {
            okButton.setEnabled(false);
        }

        add(tagField);
        setVisible(true);
    }

    public Element getElement() {
        return elem;
    }
}
