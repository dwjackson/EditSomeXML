package editor.menubar;

import editor.ElementTreeData;
import editor.views.CloneElementView;
import editor.views.ElementTreeView;
import xml.Element;
import xml.ElementXMLDeserializer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the menu bar for EditSomeXML
 */
public class EditSomeXMLMenuBar extends JMenuBar {
    public EditSomeXMLMenuBar(ElementTreeData data, final ElementTreeView elementTreeView) {
        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New...");
        newItem.addActionListener(new NewRootActionListener(data.getRoot()));
        fileMenu.add(newItem);

        fileMenu.addSeparator();

        JMenuItem exportItem = new JMenuItem("Export...");
        exportItem.addActionListener(new ExportActionListener(data.getRoot()));
        fileMenu.add(exportItem);
        JMenuItem importItem = new JMenuItem("Import...");
        importItem.addActionListener(new ImportActionListener(data));
        fileMenu.add(importItem);

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);
        add(fileMenu);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem newElementItem = new JMenuItem("New Element...");
        NewElementActionListener listener;
        listener = new NewElementActionListener(elementTreeView);
        newElementItem.addActionListener(listener);
        editMenu.add(newElementItem);
        JMenuItem cloneElementItem = new JMenuItem("Clone Element...");
        cloneElementItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CloneElementView(elementTreeView);
            }
        });
        editMenu.add(cloneElementItem);
        add(editMenu);
    }
}
