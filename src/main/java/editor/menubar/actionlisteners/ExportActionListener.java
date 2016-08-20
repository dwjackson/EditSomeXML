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
import xml.ElementXMLSerializer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ExportActionListener is used to deal with the user action of exporting
 * an element tree to XML
 */
public class ExportActionListener implements ActionListener {
    private ElementTreeData data;

    public ExportActionListener(ElementTreeData data) {
        this.data = data;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Element root = data.getRoot();
        ElementXMLSerializer exs = new ElementXMLSerializer();
        JFrame exportFileChooserFrame = new JFrame();
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(exportFileChooserFrame);
        String fileName = fc.getSelectedFile().getAbsolutePath();
        Logger.getInstance().write("Writing XML file: " + fileName);
        exs.serializeToFile(root, fileName);
    }
}
