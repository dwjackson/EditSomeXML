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

import utility.GUIDCreator;

public class ElementJSONSerializer implements ElementSerializer {
    private final String TAG_FMT = "\"tag\": \"%s\"";
    private final String ATTRIBUTE_FMT = "{\"%s\": \"%s\"}";
    
    private GUIDCreator guidCreator;
    
    public ElementJSONSerializer() {
        guidCreator = new GUIDCreator();
    }

    @Override
    public String serializeToString(Element root) {
        if (root == null) {
            return null;
        }
        
        if (root.getGUID() == null) {
            root.setGUID(guidCreator.getNewGUID());
        }
        
        StringBuilder sb = new StringBuilder("{");
        
        if (!root.isMirroring()) {
            sb.append(String.format(TAG_FMT, root.getTag()));
            
            if (root.getNumberOfAttributes() > 0) {
                sb.append(", \"attributes\": [");
                String attName, attVal;
                for (int i = 0; i < root.getNumberOfAttributes(); i++) {
                    attName = root.getAttributeName(i);
                    attVal = root.getAttribute(attName);
                    sb.append(String.format(ATTRIBUTE_FMT, attName, attVal));
                    if (i + 1 < root.getNumberOfAttributes()) {
                        sb.append(", ");
                    }
                }
                sb.append("]");
            }
            
            if (root.hasChildren()) {
                sb.append(", \"children\": [");
                for (Element child : root.children()) {
                    sb.append(serializeToString(child));
                }
                sb.append("]");
            }
        } else {
            Element mirroredElem = root.getMirroredElement();
            String guid = mirroredElem.getGUID();
            sb.append(String.format("\"mirroring\": \"%s\"", guid));
        }
        
        sb.append("}");
        return sb.toString();
    }

    @Override
    public void serializeToFile(Element root, String fileName) {
        // TODO Auto-generated method stub

    }

}
