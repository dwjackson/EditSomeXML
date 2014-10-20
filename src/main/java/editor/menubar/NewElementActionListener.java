package editor.menubar;

import editor.views.ElementTreeView;
import editor.views.NewElementView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The NewElementActionListener is used to deal with the user action of adding
 * a new element to the tree
 */
public class NewElementActionListener implements ActionListener {
    private ElementTreeView view;

    public NewElementActionListener(ElementTreeView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new NewElementView(view);
    }
}
