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

package utility;

import java.security.SecureRandom;
import java.util.HashSet;

/**
 * The GUIDCreator is used to assign globally unique identifiers to a set of
 * objects.
 */

public class GUIDCreator {
    private SecureRandom randGen;
    private HashSet<GUIDValue> inUseIDs;
    
    /**
     * Initialize a GUIDCreator with an empty set of GUIDs
     */
    public GUIDCreator() {
        randGen = new SecureRandom();
        randGen.generateSeed(8);
        inUseIDs = new HashSet<GUIDValue>();
    }
    
    private boolean isInUse(GUIDValue value) {
        return inUseIDs.contains(value);
    }
    
    /**
     * Get a new GUID that is guaranteed not to already be in use
     * @return a new GUID value
     */
    public GUIDValue getNewGUID() {
        GUIDValue val;
        do {
            val = new GUIDValue(randGen.nextInt());
        } while (isInUse(val));
        return val;
    }
    
    /**
     * Clear all in-use GUIDs
     */
    public void clear() {
        inUseIDs.clear();
    }
}
