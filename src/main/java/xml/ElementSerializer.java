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

/**
 * Classes that implement the ElementSerializer format are used to transform
 * Elements into formats that can be stored and transmitted.
 *
 * @see Element
 */
public interface ElementSerializer {
    /**
     * Serialize the element tree to a string
     * @param root The root element of the tree
     * @return A string representation of the element
     */
    String serializeToString(Element root);

    /**
     * Serialize the element tree to a file using the same format as that
     * which is created by the serializeToString() method.
     * @param root The root of the element tree
     * @param fileName The name of the file into which to serialize the file
     */
    void serializeToFile(Element root, String fileName);
}
