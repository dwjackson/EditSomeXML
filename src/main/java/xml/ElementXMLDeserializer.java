package xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * The ElementXMLDeserializer is used to read an XML file and translate it to
 * an Element tree for editing
 */
public class ElementXMLDeserializer {
    private Element root;
    private Element curr;

    public Element deserializeFromFile(String fileName) {
        root = null;
        curr = null;

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName,
                                         String qName, Attributes attributes)
                        throws SAXException {
                    Element elem = new Element();
                    elem.setTag(qName);
                    String name, value;
                    for (int i = 0; i < attributes.getLength(); i++) {
                        name = attributes.getQName(i);
                        value = attributes.getValue(name);
                        elem.setAttribute(name, value);
                    }
                    if (root == null) {
                        root = elem;
                    }
                    if (curr != null) {
                        curr.addChild(elem);
                    }
                    curr = elem;
                }

                @Override
                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {
                    curr = curr.getParent();
                }

                @Override
                public void characters(char ch[], int start, int length)
                        throws SAXException {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < length; i++) {
                        sb.append(ch[start+i]);
                    }
                    curr.setText(sb.toString());
                }
            };
            saxParser.parse(fileName, handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return root;
    }
}
