/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016 David Jackson
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
