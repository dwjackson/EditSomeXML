/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
 */

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
