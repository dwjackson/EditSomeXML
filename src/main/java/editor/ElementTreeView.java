package editor;

import utility.Observer;
import xml.Element;

import javax.swing.*;

/**
 * Created by davidjackson on 2014-10-06.
 */
public class ElementTreeView extends JPanel implements Observer {
    private JTree tree;
    private Element root;

    public ElementTreeView(Element root) {
        root.registerObserver(this);
        this.root = root;

        tree = new JTree();
        add(tree);

        setVisible(false);
    }

    public void notifyObserver() {
        if (root != null) {
            // TODO: Change the tree
            System.out.println("[DEBUG] root has changed");
            setVisible(true);
        }
    }
}
