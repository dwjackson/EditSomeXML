package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The EditSomeXML class is the main editor class of EditSomeXML.
 */
public class EditSomeXML extends JFrame {
    public EditSomeXML() {
        setSize(800,600);
        setTitle("EditSomeXML");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New...");
        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame newRootFrame = new JFrame();
                newRootFrame.setSize(400,60);
                newRootFrame.setTitle("New Root Element");
                newRootFrame.setLayout(new FlowLayout());
                newRootFrame.add(new JLabel("Root Tag"));
                JTextField rootTagField = new JTextField("", 20);
                newRootFrame.add(rootTagField);
                JButton okButton = new JButton("OK");
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
