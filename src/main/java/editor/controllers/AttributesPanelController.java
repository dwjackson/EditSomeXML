/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016 David Jackson
 */

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
