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

import junit.framework.TestCase;
import org.junit.Test;

public class ElementXMLDeserializerTest extends TestCase {
    @Test
    public void testDeserialize() {
        String testFileName = "test.xml";

        Element correctRoot = new Element("root");
        Element child = correctRoot.newSubElement("child");
        Element foo = child.newSubElement("foo");
        Element bar = child.newSubElement("bar");
        Element baz = child.newSubElement("baz");
        baz.setText("Some text");
        Element child2 = correctRoot.newSubElement("child");
        Element foo2 = child2.newSubElement("foo");
        foo2.setText("Testing");
        foo2.setAttribute("key", "val");

        ElementXMLDeserializer esd = new ElementXMLDeserializer();
        Element root = esd.deserializeFromFile(testFileName);

        assertNotNull("Deserialized tree is null", root);
        assertTrue("Deserialized tree is wrong", root.equalTree(correctRoot));
    }
}