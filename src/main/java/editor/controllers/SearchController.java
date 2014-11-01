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
