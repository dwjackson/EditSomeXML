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
