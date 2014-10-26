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

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ElementXMLSerializerTest {
    private ElementXMLSerializer exs;

    @Before
    public void initialize() {
        exs = new ElementXMLSerializer();
    }

    @Test
    public void testSingleElementSerializeToString() {
        Element root = new Element("test");
        String result = exs.serializeToString(root);
        String correctResult = "<test />";
        assertEquals("Single element XML string incorrect", correctResult, result);
    }

    @Test
    public void testMultipleElementSerializeToString() {
        Element root = new Element("test");
        Element first = root.newSubElement("first");
        first.setText("Testing");
        root.newSubElement("second");
        String result = exs.serializeToString(root);
        String correctResult = "<test><first>Testing</first><second /></test>";
        assertEquals("Multiple element XML string incorrect", correctResult, result);
    }
}