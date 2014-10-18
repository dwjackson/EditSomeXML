package editor;

import editor.controllers.NewRootController;
import editor.views.CloneElementView;
import editor.views.ElementTreeView;
import editor.views.NewElementView;
import editor.views.NewRootView;
import xml.Element;
import xml.ElementXMLDeserializer;
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

    private class NewElementActionListener implements ActionListener {
        private ElementTreeView view;

        private NewElementActionListener(ElementTreeView view) {
            this.view = view;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new NewElementView(view);
        }
    }

    private class ImportActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFrame f = new JFrame();
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(f);
            String fileName = fc.getSelectedFile().getAbsolutePath();
            ElementXMLDeserializer deserializer = new ElementXMLDeserializer();
            Element elem = deserializer.deserializeFromFile(fileName);
            // TODO: Set the actual root element
        }
    }

    public EditSomeXMLMenuBar(Element root, final ElementTreeView elementTreeView) {
        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New...");
        newItem.addActionListener(new NewRootActionListener(root));
        fileMenu.add(newItem);

        fileMenu.addSeparator();

        JMenuItem exportItem = new JMenuItem("Export...");
        exportItem.addActionListener(new ExportActionListener(root));
        fileMenu.add(exportItem);
        JMenuItem importItem = new JMenuItem("Import...");
        importItem.addActionListener(new ImportActionListener());
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
