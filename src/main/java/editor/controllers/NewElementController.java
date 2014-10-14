package editor.controllers;

import editor.views.NewElementView;
import xml.Element;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The NewElementController deals with the actions of the NewElementView
 * @see editor.views.NewElementView
 */
public class NewElementController implements ActionListener {
    private NewElementView view;
    private Element parent;

    public NewElementController(NewElementView view, Element parent) {
        this.view = view;
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Element child = new Element(view.getTag());
        parent.addChild(child);
        view.dispose();
        parent.notifyObservers();
    }
}
