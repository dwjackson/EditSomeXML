/*
 * EditSomeXML is a graphical XML editor
 * 
 * Copyright (C) 2014  David Jackson
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
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
        setSize(400, 60);
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
