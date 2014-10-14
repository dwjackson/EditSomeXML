package editor.controllers;

import editor.views.NewElementView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The NewElementController deals with the actions of the NewElementView
 * @see editor.views.NewElementView
 */
public class NewElementController implements ActionListener {
    private NewElementView view;

    public NewElementController(NewElementView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    }
}
