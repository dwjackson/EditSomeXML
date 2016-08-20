/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016 David Jackson
 */

package editor.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import xml.Element;
import xml.ElementSearcher;
import editor.ElementTreeData;
import editor.views.ElementEditorView;
import editor.views.ElementTreeView;
import editor.views.SearchView;

public class SearchController {
	private class NextActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Element nextElem = searcher.nextResult();
			if (nextElem != null) {
				elementEditorView.populateWithElementData(nextElem);
				// TODO: Set the selected element in the tree
			}
		}
	}
	
	private class PrevActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Element prevElem = searcher.prevResult();
			if (prevElem != null) {
				elementEditorView.populateWithElementData(prevElem);
				// TODO: Set the selected element in the tree
			}
		}
	}
	
	private class SearchActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setQuery(view.getQuery());
		}
	}
	
	private SearchView view;
	private NextActionListener nextActionListener;
	private PrevActionListener prevActionListener;
	private SearchActionListener searchActionListener;
	private String query;
	private ElementSearcher searcher;
	private ElementEditorView elementEditorView;
	private ElementTreeData data;
	
	public SearchController(SearchView searchView,
			ElementEditorView elementEditorView, ElementTreeData data) {
		view = searchView;
		nextActionListener = new NextActionListener();
		prevActionListener = new PrevActionListener();
		searchActionListener = new SearchActionListener();
		query = null;
		searcher = null;
		this.elementEditorView = elementEditorView;
		this.data = data;
	}
	
	public ActionListener getSearchActionListener() {
		return searchActionListener;
	}
	
	public ActionListener getPrevActionListener() {
		return prevActionListener;
	}
	
	public ActionListener getNextActionListener() {
		return nextActionListener;
	}
	
	private void setQuery(String query) {
		this.query = query;
		search();
	}
	
	private void search() {
		if (query != null) {
			Element root = data.getRoot();
			searcher = new ElementSearcher(root, query);
			view.enablePrevAndNextButtons();
		}
	}
}
