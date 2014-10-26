package xml;

import junit.framework.TestCase;
import org.junit.Test;

public class ElementSearcherTest extends TestCase {
    @Test
    public void testSearch() {
        Element root = new Element("root");
        Element child = root.newSubElement("child");
        child.setText("This is a test");
        Element grandchild = child.newSubElement("grandchild");
        grandchild.setAttribute("test", "testing");
        Element grandchild2 = child.newSubElement("test");
        Element child2 = root.newSubElement("child-again");
        child2.setAttribute("att", "test");

        String query = "test";
        ElementSearcher searcher = new ElementSearcher(root, query);

        assertTrue("Search has no results", searcher.hasResults());
        assertEquals("Number of results is wrong", 4, searcher.getNumberOfResults());
        assertNotNull("nextResult() returns null for first result", searcher.nextResult());
        assertNull("prevResult() doesn't return null for first result", searcher.prevResult());
    }
}