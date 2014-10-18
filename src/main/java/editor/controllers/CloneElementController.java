package editor.controllers;

import editor.views.CloneElementView;
import xml.Element;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * the CloneElementController handles events from the CloneElementView
 * @see editor.views.CloneElementView
 */
public class CloneElementController implements ActionListener {
    private CloneElementView view;

    public CloneElementController(CloneElementView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Element origElem = view.getElement();
        if (origElem != null && !origElem.isRoot()) {
            Element clonedElem = origElem.cloneWithoutChildren();
            Element parent = origElem.getParent();
            parent.addChild(clonedElem);
            parent.notifyObservers();

            view.dispose();
        }
    }
}
