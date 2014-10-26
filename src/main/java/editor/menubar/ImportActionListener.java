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

import editor.ElementTreeData;
import xml.Element;
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
        System.out.println("[DEBUG] imported root.tag = " + elem);
        data.setRoot(elem);
        data.notifyObservers();
    }
}
