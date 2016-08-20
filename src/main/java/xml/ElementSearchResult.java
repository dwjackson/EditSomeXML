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
