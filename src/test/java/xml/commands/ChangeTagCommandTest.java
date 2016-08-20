/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016 David Jackson
 */

package xml.commands;

import junit.framework.TestCase;
import org.junit.Test;
import xml.Element;

public class ChangeTagCommandTest extends TestCase {
    @Test
    public void testChangeTag() {
        Element elem = new Element("root");
        ChangeTagCommand cmd1 = new ChangeTagCommand(elem, "roo");
        elem.performCommand(cmd1);
        assertEquals("Tag modified incorrectly by first change", "roo", elem.getTag());

        ChangeTagCommand cmd2 = new ChangeTagCommand(elem, "ro");
        elem.performCommand(cmd2);
        assertEquals("Tag modified incorrectly by second change", "ro", elem.getTag());

        elem.undo();
        assertEquals("Combined undo tag incorrect", "root", elem.getTag());
    }
}
