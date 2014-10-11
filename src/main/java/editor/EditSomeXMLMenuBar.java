package editor;

import xml.Element;

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

    public EditSomeXMLMenuBar(Element root) {
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New...");
        newItem.addActionListener(new NewRootActionListener(root));
        fileMenu.add(newItem);
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
    }
}
