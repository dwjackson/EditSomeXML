package editor.menubar;

import editor.views.ElementTreeView;
import xml.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This deals with events where the user wants to change the representation of
 * an element
 */
public class RepresentationActionListener implements ActionListener {
    private ElementTreeView elementTreeView;

    public RepresentationActionListener(ElementTreeView elementTreeView) {
        this.elementTreeView = elementTreeView;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Element elem = elementTreeView.getSelectedElement();

        if (elem != null) {
            JFrame repFrame = new JFrame();
            repFrame.setTitle("Element Representation");
            repFrame.setSize(300, 200);
            repFrame.setLayout(new FlowLayout());

            repFrame.add(new JLabel("Element"));
            JTextField elemField = new JTextField(elem.getTag(), 20);
            elemField.setEditable(false);

            repFrame.add(new JLabel("Representation"));
            String[] repChoices = {"Tag", "Attribute Value"};
            JComboBox repComboBox = new JComboBox(repChoices);
            repFrame.add(repComboBox);

            repFrame.add(new JLabel("Set for all of this type of element"));
            JCheckBox setForAllCheckBox = new JCheckBox();
            repFrame.add(setForAllCheckBox);

            JButton okButton = new JButton("OK");
            repFrame.add(okButton);

            repFrame.setVisible(true);
        } else {
            // TODO: Show an error window
        }
    }
}
