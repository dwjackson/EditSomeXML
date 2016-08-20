/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016 David Jackson
 */

package editor.menubar.actionlisteners;

import editor.ElementTreeData;
import utility.Logger;
import xml.Element;
import xml.ElementEvent;
import xml.ElementXMLDeserializer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ImportActionListener is used to deal with the user action of importing
 * an XML file to use as the element tree
 */
public class ImportActionListener implements ActionListener {
    private ElementTreeData data;

    public ImportActionListener(ElementTreeData data) {
        this.data = data;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFrame f = new JFrame();
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(f);
        String fileName = fc.getSelectedFile().getAbsolutePath();
        ElementXMLDeserializer deserializer = new ElementXMLDeserializer();
        Element elem = deserializer.deserializeFromFile(fileName);
        data.setRoot(elem);
    }
}
