package editor;

import editor.controllers.ElementTreeController;
import editor.menubar.EditSomeXMLMenuBar;
import editor.views.ElementEditorView;
import editor.views.ElementTreeView;
import xml.Element;

import javax.swing.*;
import java.awt.*;

/**
 * The EditSomeXML class is the main editor class of EditSomeXML.
 */
public class EditSomeXML extends JFrame {
    public EditSomeXML() {
        ElementTreeData data = new ElementTreeData(new Element());

        // Set up the main window
        setSize(800,600);
        setTitle("EditSomeXML");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Set up the tree view
        ElementTreeController elementTreeController;
        elementTreeController = new ElementTreeController();
        ElementTreeView elementTreeView;
        elementTreeView = new ElementTreeView(data, elementTreeController);
        add(elementTreeView);

        // Set up the element editor view
        ElementEditorView elementEditorView = new ElementEditorView();
        elementTreeController.setElementEditorView(elementEditorView);
        add(elementEditorView);

        // Set up the menu bar
        EditSomeXMLMenuBar menuBar = new EditSomeXMLMenuBar(data, elementTreeView);
        setJMenuBar(menuBar);

        // Make the main frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new EditSomeXML();
    }
}
