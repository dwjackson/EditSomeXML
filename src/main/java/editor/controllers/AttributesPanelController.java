package editor.controllers;

import editor.views.AttributesPanelView;
import xml.Element;

/**
 * This controller is used to deal with events in the AttributesPanelView
 */
public class AttributesPanelController {
    private AttributesPanelView view;

    public AttributesPanelController(AttributesPanelView view) {
        this.view = view;
    }

    public void attachAttributeToElement(String attName, String attVal) {
        Element elem = view.getElement();
        elem.setAttribute(attName, attVal);
    }
}
