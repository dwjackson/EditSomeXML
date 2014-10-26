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

package xml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The ElementXMLSerializer is used to serialize an Element into the usual XML
 * format.
 *
 * @see xml.ElementSerializer
 * @see xml.Element
 */
public class ElementXMLSerializer implements ElementSerializer {
    protected final String ATTRIBUTE_FMT = " %s=\"%s\"";

    /**
     * Serialize an XML tree beginning with the root element to an XML string
     * @param root The root element of the tree
     * @return the String representation of the XML
     */
    public String serializeToString(Element root) {
        StringBuilder sb = new StringBuilder("<");
        sb.append(root.getTag());

        String val;
        for (String attName : root.attributeNames()) {
            val = root.getAttribute(attName);
            sb.append(String.format(ATTRIBUTE_FMT, attName, val));
        }

        if (root.hasText() || root.hasChildren()) {
            sb.append(">");
        } else {
            sb.append(" />");
        }

        if (root.hasText()) {
            sb.append(root.getText());
        }

        for (Element child : root.children()) {
            sb.append(serializeToString(child));
        }

        if (root.hasText() || root.hasChildren()) {
            sb.append(String.format("</%s>", root.getTag()));
        }

        return sb.toString();
    }

    /**
     * Serialize the XML tree beginning with the root, to a file
     * @param root The root of the element tree
     * @param fileName The name of the file into which to serialize the file
     */
    public void serializeToFile(Element root, String fileName) {
        String xmlStr = serializeToString(root);
        String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(xmlHeader);
            writer.write(xmlStr);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
