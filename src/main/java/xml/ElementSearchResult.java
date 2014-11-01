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
 * ElementSearchResults are returned by an ElementSearcher and consist of an
 * ordered set of results
 * @see ElementSearcher
 */
public class ElementSearchResult {
	private ArrayList<Element> results;
	private int currIndex;
	
	/**
	 * Initialize an empty set of results
	 */
	public ElementSearchResult() {
		results = new ArrayList<Element>();
		currIndex = 0;
	}
	
	/**
	 * Add an element to the results
	 * @param elem The element to add to the results
	 */
	public void add(Element elem) {
		results.add(elem);
	}
	
	/**
	 * Get the number of elements in the result set
	 * @return the number of elements in the result set
	 */
	public int size() {
		return results.size();
	}
	
	/**
	 * Get a particular element in the result set
	 * @param index The index of the element
	 * @return the element at index, if it exists; null if not
	 */
	public Element get(int index) {
		return results.get(index);
	}
	
	/**
	 * Get the next element in the search set (starts with first)
	 * @return the next element, null if at the end
	 */
	public Element getNext() {
		Element nextElem = null;
		if (currIndex + 1 < size()) {
			currIndex++;
			nextElem = results.get(currIndex);
		}
		return nextElem;
	}
	
	/**
	 * Get the previous element in the result set
	 * @return the previous element, null if at beginning
	 */
	public Element getPrev() {
		Element prevElem = null;
		if (currIndex-- >= 0) {
			currIndex--;
			prevElem = results.get(currIndex);
		}
		return prevElem;
	}
}
