package editor.views.viewlisteners;

import editor.views.AttributesPanelView;
import xml.Element;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This listener deals with attemps to delete an attribute
 */
public class DeleteAttributeListener implements ActionListener {
    private int attIndex;
    private Element elem;
    private AttributesPanelView view;

    public DeleteAttributeListener(int attributeIndex, Element element,
                                   AttributesPanelView attributesPanel) {
        attIndex = attributeIndex;
        elem = element;
        view = attributesPanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String attName = elem.getAttributeName(attIndex);
        elem.removeAttribute(attName);
        view.resetAttributes();
        view.populate();
    }
}
