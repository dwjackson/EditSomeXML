package editor.controllers;

import editor.views.ElementEditorView;
import xml.Element;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The ElementTreeController is used to deal with the various mouse actions
 * that happen to the element tree.
 */
public class ElementTreeController extends MouseAdapter {
    private JTree tree;
    private ElementEditorView elementEditorView;

    /**
     * Initialize the ElementTreeController
     */
    public ElementTreeController() {
        tree = null;
        elementEditorView = null;
    }

    /**
     * When the mouse is double-clicked, open the element for editing in the
     * editor pane.
     * @param e The mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int selRow = tree.getRowForLocation(e.getX(), e.getY());
        TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
        Element elem = (Element) selPath.getLastPathComponent();
        if (selRow != -1) {
            if (e.getClickCount() == 1) {
                System.out.println("[DEBUG] Single-click, tag = " + elem.getTag());
            } else if (e.getClickCount() == 2) {
                System.out.println("[DEBUG] Double-click, tag = " + elem.getTag());
                elementEditorView.populateWithElementData(elem);
            }
        }
    }

    public void setTree(JTree tree) {
        this.tree = tree;
    }

    public void setElementEditorView(ElementEditorView view) {
        elementEditorView = view;
    }
}
