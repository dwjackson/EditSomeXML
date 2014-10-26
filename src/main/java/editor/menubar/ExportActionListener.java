/*
 * EditSomeXML is a graphical XML editor
 * 
 * Copyright (C) 2014  David Jackson
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package editor.menubar;

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
    private Element root;

    public ExportActionListener(Element root) {
        this.root = root;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ElementXMLSerializer exs = new ElementXMLSerializer();
        JFrame exportFileChooserFrame = new JFrame();
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(exportFileChooserFrame);
        String fileName = fc.getSelectedFile().getAbsolutePath();
        System.out.printf("[DEBUG] writing file: \"%s\"\n", fileName);
        exs.serializeToFile(root, fileName);
    }
}
