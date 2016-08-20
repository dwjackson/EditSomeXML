/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016  David Jackson
 */

package xml;

import java.util.ArrayList;
import java.util.HashMap;

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
     * Get an Iterable to go through the names of the attributes
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
        if (!attributes.containsKey(key)) {
            attributeNames.add(key);
        }
        attributes.put(key, value);
    }

    /**
     * Remove an attribute from the collection
     * @param key The attribute's name
     */
    public void remove(String key) {
        attributes.remove(key);
        int idx = attributeNames.indexOf(key);
        attributeNames.remove(idx);
    }

    /**
     * Remove an attribute based on its index
     * @param index The attribute's index
     */
    public void remove(int index) {
        attributes.remove(index);
        attributeNames.remove(index);
    }

    /**
     * Get the number of attributes
     * @return the number of attributes
     */
    public int size() {
        return attributeNames.size();
    }

    /**
     * Rename an existing attribute
     * @param oldKey The attribute's old name
     * @param newKey The attribute's new name
     */
    public void rename(String oldKey, String newKey) {
        int idx = attributeNames.indexOf(oldKey);
        attributeNames.set(idx, newKey);

        String val = attributes.get(oldKey);
        attributes.put(newKey, val);
        attributes.remove(oldKey);
    }

    /**
     * Rename an attribute based on its index
     * @param index The attribute's index
     * @param newKey The attribute's new name
     */
    public void rename(int index, String newKey) {
        String oldKey = attributeNames.get(index);
        rename(oldKey, newKey);
    }

    /**
     * Get an attribute's name from its index
     * @param index The attribute's index
     * @return the attribute's name
     */
    public String getName(int index) {
        return attributeNames.get(index);
    }

    /**
     * Determine if this set of attributes contains an attribute with a
     * particular name
     * @param attributeName The name of the attribute for which to look
     * @return true if the attribute exists, false if not
     */
    public boolean contains(String attributeName) {
        if (attributes.containsKey(attributeName)) {
            return true;
        }
        return false;
    }

    /**
     * Set an attribute's name and value, based on the index of that attribute
     * @param index The attribute's index
     * @param key The attribute's name
     * @param value The attribute's value/content
     */
    public void setAttribute(int index, String key, String value) {
        if (index >= 0 && index < attributes.size()) {
            String attOrigName = attributeNames.get(index);
            if (!attOrigName.equals(key)) {
                attributeNames.set(index, key);
                attributes.remove(attOrigName);
            }
            attributes.put(key, value);
        }
    }
}
