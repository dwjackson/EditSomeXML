package editor;

import xml.Element;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The NewRootController is used to deal with actions initiated by the user in
 * the NewRootView GUI.
 */
public class NewRootController implements ActionListener {
    private Element root;
    private NewRootView view;

    /**
     * Create the controller and associate it with a root element. This
     * element is the model that is to be updated by this controller.
     * @param root The root element of the XML tree
     */
    public NewRootController(Element root) {
        this.root = root;
        view = null;
    }

    /**
     * Associate a view with this controller. The view is used to get input
     * from the user.
     * @param view The view with which to associate this controller
     */
    public void setView(NewRootView view) {
        this.view = view;
    }

    /**
     * Create the new root element
     * @param e The ActionEvent that triggers this action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        root.setTag(view.getRootTag());
        System.out.printf("[DEBUG] root.tag = \"%s\"\n", root.getTag());
        view.teardown();
    }
}
