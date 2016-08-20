/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
 */

package editor.views;

import javax.swing.*;

import utility.Observer;
import xml.Element;
import xml.ElementEvent;
import editor.ElementTreeData;
import editor.ElementTreeModel;
import editor.controllers.ElementTreeController;

import java.awt.datatransfer.Transferable;

/**
 * The ElementTreeView controls the section of the GUI that displays the tree
 * representation of an XML element tree.
 */
public class ElementTreeView extends JPanel implements Observer {
    private JTree tree;
    private ElementTreeData data;
    private ElementTreeModel model;

    /**
     * Create the tree view and associate it with a particular model (root of
     * the XML element tree)
     * @param data The element tree data
     * @param controller The controller for this tree view
     */
    public ElementTreeView(ElementTreeData data,
    		ElementTreeController controller) {
        this.data = data;
        data.registerObserver(this);

        model = new ElementTreeModel(data);
        tree = new JTree(model);
        JScrollPane treeScrollPane = new JScrollPane(tree);
        add(treeScrollPane);

        controller.setTree(tree);
        tree.addMouseListener(controller);

        setVisible(false);
    }

    /**
     * When the associated element tree has changed, change the view. This
     * applies to any element in the tree, not just the root element
     */
    public void notifyObserver(Object obj) {
        if (data.getRoot() != null) {
        	ElementEvent ev = (ElementEvent) obj;
            model.elementChanged(ev.getElement(), ev);
            if (!isVisible()) {
                setVisible(true);
            }
        } else {
            System.out.println("[ERROR] data.root is null!");
        }
    }

    /**
     * Get the currently-selected element
     * @return the currently-selected element
     */
    public Element getSelectedElement() {
        return (Element) tree.getLastSelectedPathComponent();
    }

    /**
     * Get the root element of the tree
     * @return the root element of the tree
     */
    public Element getRoot() {
        return data.getRoot();
    }

}
