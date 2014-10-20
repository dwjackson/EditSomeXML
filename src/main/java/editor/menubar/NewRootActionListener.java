package editor.menubar;

import editor.controllers.NewRootController;
import editor.views.NewRootView;
import xml.Element;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The NewRootActionListener is used to deal with the user action of creating
 * a new root element
 */
public class NewRootActionListener implements ActionListener {
    private Element root;

    public NewRootActionListener(Element root) {
        this.root = root;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        NewRootController controller = new NewRootController(root);
        NewRootView view = new NewRootView(controller);
        controller.setView(view);
    }
}
