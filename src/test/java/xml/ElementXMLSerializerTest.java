package xml;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ElementXMLSerializerTest {
    @Test
    public void testSerializeToString() {
        Element root = new Element("test");
        ElementXMLSerializer exs = new ElementXMLSerializer();
        String str = exs.serializeToString(root);
        String correctStr = "<test />";
        assertEquals("Single element XML string incorrect", correctStr, str);
    }
}