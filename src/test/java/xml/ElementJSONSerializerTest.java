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

public class ElementJSONSerializerTest extends TestCase {
    @Test
    public void testSerializeToString() {
        final String BASE_FAIL = "Single element not serialized to JSON correctly";
        final String ATTRIBUTES_FAIL = "Attributes not serialized correctly";
        
        ElementJSONSerializer serializer = new ElementJSONSerializer();
        Element root = new Element("test");
        String jsonStr = serializer.serializeToString(root);
        System.out.println("[TEST] root = " + jsonStr);
        assertEquals(BASE_FAIL, "{\"tag\": \"test\"}", jsonStr);
        
        root.setAttribute("att", "val");
        root.setAttribute("attribute", "value");
        jsonStr = serializer.serializeToString(root);
        String correct = "{\"tag\": \"test\", \"attributes\": [{\"att\": \"val\"}, {\"attribute\": \"value\"}]}";
        assertEquals(ATTRIBUTES_FAIL, correct, jsonStr);
        
        root.newSubElement("child");
        correct = "{\"tag\": \"test\", \"attributes\": [{\"att\": \"val\"}, {\"attribute\": \"value\"}], \"children\": [{\"tag\": \"child\"}]}";
        jsonStr = serializer.serializeToString(root);
        assertEquals("Child not serializing correctly", correct, jsonStr);
    }
}
