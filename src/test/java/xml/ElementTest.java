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

public class ElementTest extends TestCase {
    @Test
    public void testCreate() {
        Element elem = new Element("elem");
        assertNotNull("Element is null", elem);
    }

    @Test
    public void testEquals() {
        Element elem1 = new Element("Test");
        elem1.setText("Testing");
        elem1.setAttribute("foo", "bar");
        elem1.setAttribute("beep", "boop");

        Element elem2 = new Element("Test");
        elem2.setText("Testing");
        elem2.setAttribute("foo", "bar");
        elem2.setAttribute("beep", "boop");

        assertTrue("elem1 and elem2 are not equal", elem1.equals(elem2));
    }

    @Test
    public void testClone() {
        Element root = new Element("root");
        Element fst = root.newSubElement("fst");
        Element snd = fst.cloneWithoutChildren();

        assertNotNull("Clone is null", snd);
        assertTrue("Clone is not equal to original", snd.equals(fst));
        assertFalse("Clone is original", snd == fst);
    }

    @Test
    public void testAddChild() {
        Element root = new Element("root");
        Element child = new Element("child");

        root.addChild(child);

        Element parent = child.getParent();
        assertTrue("Child's parent is not set", parent.equals(root));
    }

    @Test
    public void testEqualTree() {
        Element root1 = new Element("root");
        Element root2 = new Element("root");

        root1.newSubElement("test");
        root2.newSubElement("test");

        assertTrue("Trees not equal", root1.equalTree(root2));
    }

    @Test
    public void testDeleteChild() {
        Element root = new Element("root");
        Element ch1 = root.newSubElement("child1");
        Element ch2 = root.newSubElement("child2");
        Element ch3 = root.newSubElement("child3");
        root.deleteChild(ch2);

        assertEquals("Number of children is wrong", root.getNumberOfChildren(), 2);
        for (Element child : root.children()) {
            assertTrue("Wrong child deleted", child.getTag() != "child2");
        }
    }

    @Test
    public void testAttributeRename() {
        Element elem = new Element("test");
        elem.setAttribute("att", "testing");
        assertEquals("Initial attribute not set", elem.getAttribute("att"), "testing");
        elem.renameAttribute("att", "test");
        assertNull("Old attribute still exists", elem.getAttribute("att"));
        assertEquals("New attribute not set properly", elem.getAttribute("test"), "testing");
        elem.renameAttribute(0, "testagain");
        assertEquals("New attribute not set properly from index", elem.getAttribute("testagain"), "testing");
    }

    @Test
    public void testDeleteSubelements() {
        Element root = new Element("root");
        Element child = root.newSubElement("child");
        child.newSubElement("grandchild");
        root.deleteSubelements();

        assertEquals("Wrong number of children", 0, root.getNumberOfChildren());
    }

    @Test
    public void testRemoveAttribute() {
        Element elem = new Element("test");
        elem.setAttribute("att", "test");
        elem.setAttribute("attribute", "testing");
        elem.removeAttribute("att");

        assertEquals("Wrong number of attributes", 1, elem.getNumberOfAttributes());
        assertNull("Deleted attribute still exists", elem.getAttribute("att"));
    }
}