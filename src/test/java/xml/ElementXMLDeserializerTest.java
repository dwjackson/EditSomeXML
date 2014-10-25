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