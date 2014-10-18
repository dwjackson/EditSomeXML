package editor.views;

import editor.ElementTreeModel;
import editor.controllers.ElementTreeController;
import utility.Observer;
import xml.Element;

import javax.swing.*;

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
     * @param controller The controller for this tree view
     */
    public ElementTreeView(Element root, ElementTreeController controller) {
        root.registerObserver(this);
        this.root = root;

        model = new ElementTreeModel(root);
        tree = new JTree(model);
        add(tree);

        controller.setTree(tree);
        tree.addMouseListener(controller);

        setVisible(false);
    }

    /**
     * When the associated element tree root has changed, change the view
     */
    public void notifyObserver() {
        if (root != null) {
            model.elementChanged(root);
            if (!isVisible()) {
                setVisible(true);
            }
        }
    }

    /**
     * Get the currently-selected element
     * @return the currently-selected element
     */
    public Element getSelectedElement() {
        Element elem = (Element) tree.getLastSelectedPathComponent();
        return elem;
    }

    /**
     * Get the root element of the tree
     * @return the root element of the tree
     */
    public Element getRoot() {
        return root;
    }

}
