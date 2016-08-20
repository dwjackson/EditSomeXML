/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016  David Jackson
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
