package xml;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utility.GUIDValue;

public class ElementJSONDeserializer {
    private Element elementFromJSON(JSONObject obj, Element root) {
        Element elem = new Element();
        
        if (root == null) {
            root = elem;
        }
        
        if (obj.get("mirroring") == null) {
            elem.setTag((String) obj.get("tag"));
            
            JSONArray attsArray = (JSONArray) obj.get("attributes");
            if (attsArray != null) {
                JSONObject attObj;
                String attName, attVal;
                for (int i = 0; i < attsArray.size(); i++) {
                    attObj = (JSONObject) attsArray.get(i);
                    attName = (String) attObj.keySet().toArray()[0];
                    attVal = (String) attObj.get(attName);
                    elem.setAttribute(attName, attVal);
                }
            }
            
            elem.setText((String) obj.get("text"));
            
            JSONArray childrenArray = (JSONArray) obj.get("children");
            if (childrenArray != null) {
                JSONObject childObj;
                for (int i = 0; i < childrenArray.size(); i++) {
                    childObj = (JSONObject) childrenArray.get(i);
                    elem.addChild(elementFromJSON(childObj, root));
                }
            }
        } else {
            GUIDValue guid = new GUIDValue((Integer)obj.get("mirroring"));
            Element mirroredElement = root.getElementByGUID(guid);
            elem.mirrorElement(mirroredElement);
        }
        
        return elem;
    }
    
    public Element deserializeFromFile(String fileName) {
        Element root = null;
        
        JSONParser parser = new JSONParser();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            JSONObject obj = (JSONObject) parser.parse(reader);
            
            root = elementFromJSON(obj, null);
            
            reader.close();
        } catch (ParseException e) {
            e.printStackTrace();
            root = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            root = null;
        } catch (IOException e) {
            e.printStackTrace();
            root = null;
        }
        
        return root;
    }
}
