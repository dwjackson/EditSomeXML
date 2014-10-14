package xml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The ElementXMLSerializer is used to serialize an Element into the usual XML
 * format.
 *
 * @see xml.ElementSerializer
 * @see xml.Element
 */
public class ElementXMLSerializer implements ElementSerializer {
    protected final String ATTRIBUTE_FMT = " %s=\"%s\"";

    /**
     * Serialize an XML tree beginning with the root element to an XML string
     * @param root The root element of the tree
     * @return the String representation of the XML
     */
    public String serializeToString(Element root) {
        StringBuilder sb = new StringBuilder("<");
        sb.append(root.getTag());

        String val;
        for (String attName : root.attributeNames()) {
            val = root.getAttribute(attName);
            sb.append(String.format(ATTRIBUTE_FMT, attName, val));
        }

        if (root.hasText()) {
            sb.append(root.getText());
        }

        if (root.hasChildren()) {
            sb.append(">");
            for (Element child : root.children()) {
                sb.append(serializeToString(child));
            }
            sb.append(String.format("</%s>", root.getTag()));
        } else {
            sb.append(" />");
        }

        return sb.toString();
    }

    /**
     * Serialize the XML tree beginning with the root, to a file
     * @param root The root of the element tree
     * @param fileName The name of the file into which to serialize the file
     */
    public void serializeToFile(Element root, String fileName) {
        String xmlStr = serializeToString(root);
        String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(xmlHeader);
            writer.write(xmlStr);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
