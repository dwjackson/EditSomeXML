/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016  David Jackson
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
