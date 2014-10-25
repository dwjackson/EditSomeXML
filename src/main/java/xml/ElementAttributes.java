package xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The ElementAttributes holds the attributes of an Element
 * @see xml.Element
 */
public class ElementAttributes {
    private HashMap<String, String> attributes;
    private ArrayList<String> attributeNames;

    public ElementAttributes() {
        attributes = new HashMap<String, String>();
        attributeNames = new ArrayList<String>();
    }

    public Iterable<String> names() {
        return attributeNames;
    }

    public String getValue(String key) {
        if (attributeNames.contains(key)) {
            return attributes.get(key);
        }
        return null;
    }

    public void set(String key, String value) {
        attributes.put(key, value);
        if (!attributes.containsKey(key)) {
            attributeNames.add(key);
        }
    }

    public void remove(String key) {
        attributes.remove(key);
    }

    public int size() {
        return attributeNames.size();
    }
}
