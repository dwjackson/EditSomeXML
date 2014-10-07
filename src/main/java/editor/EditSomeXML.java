package editor;

import xml.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The EditSomeXML class is the main editor class of EditSomeXML.
 */
public class EditSomeXML extends JFrame {
    private Element root;

    public EditSomeXML() {
        root = new Element();

        // Set up the main window
        setSize(800,600);
        setTitle("EditSomeXML");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Set up the menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New...");
        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NewRootController controller = new NewRootController(root);
                NewRootView view = new NewRootView(controller);
                controller.setView(view);
            }
        });
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
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Set up the tree view
        add(new ElementTreeView(root));

        // Make the main frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new EditSomeXML();
    }
}
