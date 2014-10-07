package editor;

import javax.swing.*;
import java.awt.*;

/**
 * The NewRootView is used to create the GUI that is used to create a new root
 * XML element.
 */
public class NewRootView extends JFrame {
    private NewRootController controller;
    private JTextField rootTagField;

    /**
     * Create the view and set up the look of the GUI
     * @param newRootController The controller with which to associate the
     *                          view. This controller will update the model.
     */
    public NewRootView(NewRootController newRootController) {
        controller = newRootController;

        setSize(400, 60);
        setTitle("New Root Element");
        setLayout(new FlowLayout());

        add(new JLabel("Root Tag"));
        rootTagField = new JTextField("", 20);
        add(rootTagField);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(controller);
        add(okButton);

        setVisible(true);
    }

    /**
     * Get the root tag as set by the text field
     * @return The value of the root tag text field
     */
    public String getRootTag() {
        return rootTagField.getText();
    }

    /**
     * Get rid of this view (close the window)
     */
    public void teardown() {
        setVisible(false);
        dispose();
    }
}
