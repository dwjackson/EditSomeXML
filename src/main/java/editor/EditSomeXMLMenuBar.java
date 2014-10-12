package editor;

import editor.controllers.NewRootController;
import editor.views.NewElementView;
import editor.views.NewRootView;
import xml.Element;
import xml.ElementXMLSerializer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the menu bar for EditSomeXML
 */
public class EditSomeXMLMenuBar extends JMenuBar {
    private class NewRootActionListener implements ActionListener {
        private Element root;

        public NewRootActionListener(Element root) {
            this.root = root;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            NewRootController controller = new NewRootController(root);
            NewRootView view = new NewRootView(controller);
            controller.setView(view);
        }
    }

    private class ExportActionListener implements ActionListener {
        private Element root;

        public ExportActionListener(Element root) {
            this.root = root;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ElementXMLSerializer exs = new ElementXMLSerializer();
            String fileName = "tree.xml";
            System.out.printf("[DEBUG] writing file: %s\n", fileName);
            exs.serializeToFile(root, fileName);
        }
    }

    public EditSomeXMLMenuBar(Element root) {
        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New...");
        newItem.addActionListener(new NewRootActionListener(root));
        fileMenu.add(newItem);

        fileMenu.addSeparator();

        JMenuItem exportItem = new JMenuItem("Export...");
        exportItem.addActionListener(new ExportActionListener(root));
        fileMenu.add(exportItem);

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
        newElementItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new NewElementView();
            }
        });
        editMenu.add(newElementItem);
        add(editMenu);
    }
}
