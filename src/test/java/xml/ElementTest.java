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
}