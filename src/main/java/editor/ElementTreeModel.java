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

package editor;

import xml.Element;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;

/**
 * The ElementTreeModel is used to map the Element class onto a JTree
 * @see javax.swing.tree.TreeModel
 * @see xml.Element
 * @see editor.ElementTreeData
 * @see javax.swing.JTree
 */
public class ElementTreeModel implements TreeModel {
    private ElementTreeData data;
    private ArrayList<TreeModelListener> listeners;

    /**
     * Initialize the ElementTreeModel by supplying it with element tree data
     * @param data The element tree data
     */
    public ElementTreeModel(ElementTreeData data) {
        this.data = data;
        listeners = new ArrayList<TreeModelListener>();
    }

    /**
     * Get the root Element of the ElementTreeModel
     * @return the root Element of the ElementTreeModel
     */
    @Override
    public Object getRoot() {
        return data.getRoot();
    }

    /**
     * Get the child Element of parent Element at index
     * @param parent The parent Element in which to look for the child
     * @param index The index at which to look for the child
     * @return the child at index, or null if not found
     */
    @Override
    public Object getChild(Object parent, int index) {
        Element parentElem = (Element) parent;
        return parentElem.getChildAt(index);
    }

    /**
     * Get the number of children for Element parent
     * @param parent the parent Element
     * @return the number of children that parent has
     */
    @Override
    public int getChildCount(Object parent) {
        Element elem = (Element)parent;
        return elem.getNumberOfChildren();
    }

    /**
     * Determine if this element has children
     * @param node The tree node (Element) to check for children
     * @return fase if the node has children, true if not
     */
    @Override
    public boolean isLeaf(Object node) {
        Element elem = (Element)node;
        return !elem.hasChildren();
    }

    @Override
    public void valueForPathChanged(TreePath treePath, Object o) {
        // TODO
    }

    /**
     * Get the index of a child Element of a particular parent
     * @param parent The parent element
     * @param child The child element
     * @return The index of the child element if found, -1 otherwise
     */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        int idx = -1;
        Element parentElem = (Element) parent;
        Element childElem = (Element) child;
        if (parent != null && child != null) {
            idx = parentElem.getIndexOfChild(childElem);
        }
        return idx;
    }

    @Override
    public void addTreeModelListener(TreeModelListener treeModelListener) {
        listeners.add(treeModelListener);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener treeModelListener) {
        listeners.remove(treeModelListener);
    }

    public void elementChanged(Element elem) {
        fireTreeStructureChanged(elem);
    }

    public void fireTreeStructureChanged(Element oldRoot) {
        TreeModelEvent e = new TreeModelEvent(this, new Object[] {oldRoot});
        for (TreeModelListener tml : listeners) {
            tml.treeStructureChanged(e);
        }
    }
}
