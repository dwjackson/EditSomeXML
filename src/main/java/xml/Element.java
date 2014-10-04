package xml;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Element class is used to represent a node in an XML tree. It must have
 * a tag (name) which identifies it. It may optionally have textual content,
 * attributes (key/value pairs) and children (other elements).
 */
public class Element {
    private String tag;
    private String text;
    private HashMap<String, String> attributes;
    private ArrayList<Element> children;

    /**
     * Create a new element, initialize it with a tag
     * @param tagName The name of the tag to give to this element
     */
    public Element(String tagName) {
        tag = tagName;
        text = null;
        attributes = new HashMap<String, String>();
        children = new ArrayList<Element>();
    }
}
