/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016 David Jackson
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
