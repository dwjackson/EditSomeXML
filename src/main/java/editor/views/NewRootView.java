/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
 */

package editor.views;

import editor.controllers.NewRootController;

import javax.swing.*;
import java.awt.*;

/**
 * The NewRootView is used to create the GUI that is used to create a new root
 * XML element.
 */
public class NewRootView extends JFrame {
    private JTextField rootTagField;

    /**
     * Create the view and set up the look of the GUI
     * @param newRootController The controller with which to associate the
     *                          view. This controller will update the model.
     */
    public NewRootView(NewRootController newRootController) {
        setSize(400, 80);
        setTitle("New Root Element");
        setLayout(new FlowLayout());

        add(new JLabel("Root Tag"));
        rootTagField = new JTextField("", 20);
        add(rootTagField);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(newRootController);
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
