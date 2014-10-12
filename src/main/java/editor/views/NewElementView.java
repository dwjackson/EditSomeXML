package editor.views;

import javax.swing.*;
import java.awt.*;

/**
 * The NewElementView is used to control the GUI of the window that creates a
 * new element
 */
public class NewElementView extends JFrame {
    private JTextField tagField;
    private JTextField parentTagField;

    public NewElementView() {
        setSize(500,100);
        setLayout(new FlowLayout());
        setTitle("New Element");

        add(new JLabel("Tag"));
        tagField = new JTextField("", 20);
        add(tagField);

        add(new JLabel("Parent:"));
        parentTagField = new JTextField("", 20);
        parentTagField.setEditable(false);
        add(parentTagField);
        JButton chooseParentButton = new JButton("Choose Parent");
        add(chooseParentButton);

        setVisible(true);
    }
}
