package editor.menubar.actionlisteners;

import xml.Element;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This listener is used to deal with "undo" actions
 */
public class UndoActionListener implements ActionListener {
    private Element root;

    public UndoActionListener(Element root) {
        this.root = root;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (root.canUndo()) {
            root.undo();
        }
    }
}
