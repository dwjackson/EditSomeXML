package editor.menubar.actionlisteners;

import xml.Element;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This listener is used to deal with "redo" events
 */
public class RedoActionListener implements ActionListener {
    private Element root;

    public RedoActionListener(Element root) {
        this.root = root;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (root.canRedo()) {
            root.redo();
        }
    }
}
