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

import javax.swing.JPanel;
import javax.swing.JTree;

import utility.Observer;
import xml.Element;
import xml.ElementEvent;
import editor.ElementTreeData;
import editor.ElementTreeModel;
import editor.controllers.ElementTreeController;

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
        add(tree);

        controller.setTree(tree);
        tree.addMouseListener(controller);

        setVisible(false);
    }

    /**
     * When the associated element tree has changed, change the view. This
     * applies to any element in the tree, not just the root element
     */
    // TODO: This tree needs to observe all elements in the tree
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
