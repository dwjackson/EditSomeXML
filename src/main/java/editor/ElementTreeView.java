package editor;

import utility.Observer;
import xml.Element;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * The ElementTreeView controls the section of the GUI that displays the tree
 * representation of an XML element tree.
 */
public class ElementTreeView extends JPanel implements Observer {
    private JTree tree;
    private Element root;
    private DefaultMutableTreeNode treeNode;
    private DefaultTreeModel model;

    /**
     * Create the tree view and associate it with a particular model (root of
     * the XML element tree)
     * @param root The root element of the XML element tree.
     */
    public ElementTreeView(Element root) {
        root.registerObserver(this);
        this.root = root;

        treeNode = new DefaultMutableTreeNode();
        tree = new JTree(treeNode);
        model = (DefaultTreeModel) tree.getModel();
        add(tree);

        setVisible(false);
    }

    /**
     * When the associated element tree root has changed, change the view
     */
    public void notifyObserver() {
        if (root != null) {
            // TODO: Change the tree
            treeNode.setUserObject("DEBUG_ROOT");
            model.nodeChanged(treeNode);
            System.out.println("[DEBUG] root has changed");
            setVisible(true);
        }
    }
}
