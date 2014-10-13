package editor.views;

import editor.ElementTreeModel;
import utility.Observer;
import xml.Element;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The ElementTreeView controls the section of the GUI that displays the tree
 * representation of an XML element tree.
 */
public class ElementTreeView extends JPanel implements Observer {
    private JTree tree;
    private Element root;
    private ElementTreeModel model;

    /**
     * Create the tree view and associate it with a particular model (root of
     * the XML element tree)
     * @param root The root element of the XML element tree.
     */
    public ElementTreeView(Element root) {
        root.registerObserver(this);
        this.root = root;

        model = new ElementTreeModel(root);
        tree = new JTree(model);
        add(tree);

        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = tree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                Element elem = (Element) selPath.getLastPathComponent();
                if (selRow != -1) {
                    if (e.getClickCount() == 1) {
                        System.out.println("[DEBUG] Single-click, tag = " + elem.getTag());
                    } else if (e.getClickCount() == 2) {
                        System.out.println("[DEBUG] Double-click, tag = " + elem.getTag());
                    }
                }
            }
        };
        tree.addMouseListener(ml);

        setVisible(false);
    }

    /**
     * When the associated element tree root has changed, change the view
     */
    public void notifyObserver() {
        if (root != null) {
            model.elementChanged(root);
            setVisible(true);
        }
    }
}
