package editor.views;

import editor.ElementTreeData;
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
    private ElementTreeData data;
    private ElementTreeModel model;

    /**
     * Create the tree view and associate it with a particular model (root of
     * the XML element tree)
     * @param data The element tree data
     * @param controller The controller for this tree view
     */
    public ElementTreeView(ElementTreeData data, ElementTreeController controller) {
        this.data = data;
        data.registerObserver(this);

        model = new ElementTreeModel(data);
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
        if (data.getRoot() != null) {
            model.elementChanged(data.getRoot()); // TODO: Fix this
            if (!isVisible()) {
                setVisible(true);
            }
        } else {
            System.out.println("[ERROR] data.root is null!");
        }
    }

    /**
     * Get the currently-selected element
     * @return the currently-selected element
     */
    public Element getSelectedElement() {
        return (Element) tree.getLastSelectedPathComponent();
    }

    /**
     * Get the root element of the tree
     * @return the root element of the tree
     */
    public Element getRoot() {
        return data.getRoot();
    }

}
