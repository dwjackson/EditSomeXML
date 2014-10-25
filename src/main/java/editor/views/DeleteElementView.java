package editor.views;

import editor.controllers.DeleteElementController;
import xml.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This view is used in the deletion of an element of the tree
 */
public class DeleteElementView extends JFrame {
    private ElementTreeView elementTreeView;

    public DeleteElementView(ElementTreeView elementTreeView) {
        this.elementTreeView = elementTreeView;
        Element elem = elementTreeView.getSelectedElement();

        setSize(400,80);
        setLayout(new FlowLayout());
        setTitle("Delete Element");

        add(new JLabel("Delete element:"));
        JTextField tagField = new JTextField(elem.getTag(), 20);
        tagField.setEditable(false);
        add(tagField);

        JButton okButton = new JButton("OK");
        DeleteElementController controller;
        controller = new DeleteElementController(this, elem.getParent());
        okButton.addActionListener(controller);
        add(okButton);

        setVisible(true);
    }

}
