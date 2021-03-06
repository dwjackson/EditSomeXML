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

import xml.commands.AddChildCommand;
import xml.commands.ChangeTagCommand;
import xml.commands.ChangeTextCommand;

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
        root.newSubElement("child1");
        Element ch2 = root.newSubElement("child2");
        root.newSubElement("child3");
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

    @Test
    public void testMirroring() {
        Element orig = new Element("test");
        Element mirroring = new Element();
        mirroring.mirrorElement(orig);
        Element origChild = new Element("child");
        orig.addChild(origChild);
        Element origChild2 = new Element("secondChild");
        orig.addChild(origChild2);

        orig.setTag("testing");
        orig.setAttribute("att", "val");
        orig.setText("Test.");

        assertEquals("Mirror tag is wrong", orig.getTag(), mirroring.getTag());
        assertEquals("Mirror attribute is wrong", orig.getAttribute("att"), mirroring.getAttribute("att"));
        assertEquals("Mirror text is wrong", orig.getText(), mirroring.getText());
        assertEquals("Wrong number of mirrored children", 2, mirroring.getNumberOfChildren());

        orig.deleteChild(origChild2);
        assertEquals("Wrong number of mirrored children", 1, mirroring.getNumberOfChildren());
    }
    
    @Test
    public void testGetAncestry() {
    	Element root = new Element("root");
    	Element child = root.newSubElement("child");
    	Element grandchild = child.newSubElement("grandchild");
    	
    	Element[] ancestry = grandchild.getAncestry();
    	System.out.println("[TEST] getAncestry() done");
    	
    	assertNotNull("Ancestry array is null", ancestry);
    	System.out.println("[TEST] array null test done");
    	System.out.println("[TEST] array.length = " + ancestry.length);
    	assertEquals("Ancestry array is the wrong length", 3, ancestry.length);
    	assertEquals("root is not first element in ancestry", root, ancestry[0]);
    	assertEquals("child is not secon element in ancestry", child, ancestry[1]);
    	assertEquals("grandchild is not third element in ancestry", grandchild, ancestry[2]);
    }
    
    @Test
    public void testMoveElement() {
    	Element root = new Element("root");
    	Element child = root.newSubElement("child");
    	Element grandchild = child.newSubElement("grandchild");
    	
    	child.moveElement(grandchild, root);
    	
    	assertEquals("Child element still has children", 0, child.getNumberOfChildren());
    	assertEquals("Root doesn't have enough children", 2, root.getNumberOfChildren());
    	assertTrue("Grandchild's parent is still Child and not Root", grandchild.getParent().equals(root));
    }

    @Test
    public void testRepresentation() {
        String tag = "elem";
        Element elem = new Element(tag);
        assertEquals("Initial representation is not the element's tag", tag, elem.toString());

        String attName = "name";
        String attVal = "testing";
        elem.setAttribute(attName, attVal);
        elem.setRepresentationToAttributeValue(attName, false, elem.getTag());
        assertEquals("Representation has not been changed correctly", attVal, elem.toString());

        elem.setRepresentationToTag(false);
        assertEquals("Changing representation back to tag didn't work", tag, elem.toString());

        elem = new Element();
        elem.setTag(tag);
        assertEquals("Setting the tag on an element doesn't set representation", tag, elem.toString());
    }
    
    @Test
    public void testChangeTagCommand() {
    	String tag = "elem";
    	String testingTag = "testing";
    	Element elem = new Element(tag);
    	
    	assertEquals("Original tag not set", tag, elem.getTag());
    	
    	elem.performCommand(new ChangeTagCommand(elem, testingTag));
    	assertEquals("Tag not set correctly", testingTag, elem.getTag());
    	
    	elem.undo();
    	assertEquals("Tag setting not undone", tag, elem.getTag());
    	
    	elem.redo();
    	assertEquals("Tag setting not redone", testingTag, elem.getTag());
    }
    
    @Test
    public void testChangeTextCommand() {
    	String text = "This is a test";
    	String newText = "This is the text after a change";
    	Element elem = new Element("elem");
    	
    	elem.setText(text);
    	assertEquals("Original text not set correctly", text, elem.getText());
    	
    	elem.performCommand(new ChangeTextCommand(elem, newText));
    	assertEquals("Text not set correctly by command", newText, elem.getText());
    	
    	elem.undo();
    	assertEquals("Text change not undone", text, elem.getText());
    	
    	elem.redo();
    	assertEquals("Text change not redone", newText, elem.getText());
    }
    
    @Test
    public void testAddChildCommand() {
    	Element elem = new Element("element");
    	Element child = new Element("child");
    	
    	assertEquals("Wrong initial number of children", 0, elem.getNumberOfChildren());
    	
    	elem.performCommand(new AddChildCommand(elem, child));
    	assertEquals("Wrong number of children after add", 1, elem.getNumberOfChildren());
    	assertEquals("Child has wrong parent", elem, child.getParent());
    	
    	elem.undo();
    	assertEquals("Wrong number of children after undo of add", 0, elem.getNumberOfChildren());
    	assertNull("Child's parent is not null", child.getParent());
    	
    	elem.redo();
    	assertEquals("Wrong number of children after redo of add", 1, elem.getNumberOfChildren());
    	assertEquals("Child's parent is not correct", elem, child.getParent());
    }
}
