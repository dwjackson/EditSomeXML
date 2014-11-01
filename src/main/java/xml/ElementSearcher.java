/*
 * EditSomeXML is a graphical XML editor
 *
 * Copyright (C) 2014  David Jackson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package xml;

import java.util.ArrayList;

/**
 * This class is used to search the content of an element tree
 */
public class ElementSearcher {
	private ElementSearchResult results;
    private String query;
    int resultIndex;

    /**
     * Initialize this searcher with the root of the tree to search as well as
     * the query (the string to look for)
     * @param root The root of the tree
     * @param query The string for which to look
     */
    public ElementSearcher(Element root, String query) {
        results = new ElementSearchResult();
        this.query = query.toLowerCase();
        generateResultsList(root);
        resultIndex = -1;
    }

    /**
     * Generate the list of results
     * @param elem The element to search through
     */
    protected void generateResultsList(Element elem) {
        if (elem != null) {
            if (queryInElement(elem)) {
                results.add(elem);
            }
            for (Element child : elem.children()) {
                generateResultsList(child);
            }
        }
    }

    /**
     * Determine if the search returned any results
     * @return true if the search has results, false if not
     */
    public boolean hasResults() {
        if (results.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Determine if the query is found anywhere in the element
     * @param elem The element in which to search
     * @return true if the query is found in the element, false if not
     */
    protected boolean queryInElement(Element elem) {
        if (queryInTag(elem) || queryInAttributes(elem)
                || queryInText(elem)) {
            return true;
        }
        return false;
    }

    /**
     * Determine if the query exists in the element's tag
     * @param elem The element whose tag to search
     * @return true if the query exists in the element's tag, false if not
     */
    protected boolean queryInTag(Element elem) {
        if (elem.getTag() != null) {
            if (elem.getTag().toLowerCase().contains(query)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if the query exists in the element's attributes
     * @param elem The element whose attributes to search
     * @return true if the query exists in the attributes, false if not
     */
    protected boolean queryInAttributes(Element elem) {
        for (String attName : elem.attributeNames()) {
            String attVal = elem.getAttribute(attName);
            if (attName.toLowerCase().contains(query)
                    || attVal.toLowerCase().contains(query)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if the query can be found in the element's text
     * @param elem The element to search
     * @return true if the query exists in the text, false if not
     */
    protected boolean queryInText(Element elem) {
        if (elem.getText() != null) {
            if (elem.getText().toLowerCase().contains(query)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the next result in the result list
     * @return the next result, null if none is found
     */
    public Element nextResult() {
        Element result = null;
        if (resultIndex + 1 < results.size()) {
            resultIndex++;
            result = results.get(resultIndex);
        }
        return result;
    }

    /**
     * Get the previous result in the result list
     * @return the previous result, null if none is found
     */
    public Element prevResult() {
        Element result = null;
        if (resultIndex - 1 >= 0 && results.size() > 0) {
            resultIndex--;
            result = results.get(resultIndex);
        }
        return result;
    }

    /**
     * Get the number of results in the result list
     * @return the number of results
     */
    public int getNumberOfResults() {
        return results.size();
    }
}
