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
        root = null;

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
                final JFrame newRootFrame = new JFrame();
                newRootFrame.setSize(400,60);
                newRootFrame.setTitle("New Root Element");
                newRootFrame.setLayout(new FlowLayout());
                newRootFrame.add(new JLabel("Root Tag"));
                final JTextField rootTagField = new JTextField("", 20);
                newRootFrame.add(rootTagField);
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        root = new Element(rootTagField.getText());
                        System.out.printf("[DEBUG] root.name = \"%s\"\n", root.getTag());
                        newRootFrame.setVisible(false);
                        newRootFrame.dispose();
                    }
                });
                newRootFrame.add(okButton);
                newRootFrame.setVisible(true);
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

        setVisible(true);
    }

    public static void main(String[] args) {
        new EditSomeXML();
    }
}
