/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016  David Jackson
 */

package utility;

/**
 * The GUIDValue class is used to signify the value of a globally-unique
 * identifier for an object.
 */
public class GUIDValue {
    private int value;
    
    public GUIDValue(int value) {
        this.value = value;
    }
    
    public int getIntegerValue() {
        return value;
    }
    
    public String getStringValue() {
        return Integer.toString(value);
    }
}
