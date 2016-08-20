/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016  David Jackson
 */

package xml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(serializeToString(root));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
