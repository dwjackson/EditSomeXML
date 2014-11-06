package editor.menubar;

import editor.views.ElementTreeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The view menu is used to change how things look in the UI
 */
public class ViewMenu extends JMenu {
    private ElementTreeView elementTreeView;

    public ViewMenu(ElementTreeView elementTreeView) {
        this.elementTreeView = elementTreeView;

        setText("View");

        JMenuItem representationItem;
        representationItem = new JMenuItem("Element Representation");
        RepresentationActionListener listener;
        listener = new RepresentationActionListener(elementTreeView);
        representationItem.addActionListener(listener);
        add(representationItem);
    }
}
