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

    /**
     * Initialize the attributes as an empty list
     */
    public ElementAttributes() {
        attributes = new HashMap<String, String>();
        attributeNames = new ArrayList<String>();
    }

    /**
     * Get an iterable to go through the names of the attributes
     * @return An Iterable for the attribute names
     */
    public Iterable<String> names() {
        return attributeNames;
    }

    /**
     * Get the value of an attribute
     * @param key The attribute's name
     * @return The attribute's value, null if it doesn't exist
     */
    public String getValue(String key) {
        if (attributeNames.contains(key)) {
            return attributes.get(key);
        }
        return null;
    }

    /**
     * Set the value of an attribute
     * @param key The attribute's name
     * @param value The attribute's value
     */
    public void set(String key, String value) {
        attributes.put(key, value);
        if (!attributes.containsKey(key)) {
            attributeNames.add(key);
        }
    }

    /**
     * Remove an attribute from the collection
     * @param key The attribute's name
     */
    public void remove(String key) {
        attributes.remove(key);
    }

    /**
     * Get the number of attributes
     * @return the number of attributes
     */
    public int size() {
        return attributeNames.size();
    }

    public void rename(String oldKey, String newKey) {
        int idx = attributeNames.indexOf(oldKey);
        attributeNames.set(idx, newKey);

        String val = attributes.get(oldKey);
        attributes.put(newKey, val);
        attributes.remove(oldKey);
    }
}
