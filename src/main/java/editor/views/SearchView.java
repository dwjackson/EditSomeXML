package editor.views;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * This view is used when the user wants to search for particular things
 * within the element tree
 */
public class SearchView extends JFrame {
	public SearchView() {
        setTitle("Search");
        setSize(450,200);
        setLayout(new FlowLayout());

        add(new JLabel("Search for:"));
        JTextField queryField = new JTextField("", 20);
        add(queryField);

        JButton searchButton = new JButton("Search");
        add(searchButton);

        JButton prevButton = new JButton("Prev");
        add(prevButton);

        JButton nextButton = new JButton("Next");
        add(nextButton);

        setVisible(true);
	}
}
