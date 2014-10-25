package editor.controllers;

import editor.views.DeleteElementView;
import xml.Element;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This controller is used in the deletion of elements
 */
public class DeleteElementController implements ActionListener {
    private DeleteElementView view;
    private Element parent;

    public DeleteElementController(DeleteElementView view, Element parent) {
        this.view = view;
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        parent.deleteSubelements();
        parent.notifyObservers();
        view.dispose();
    }
}
