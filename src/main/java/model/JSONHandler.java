package model;

import java.util.HashMap;
import java.util.Map;

public class JSONHandler {
    private Map<String, String> data;

    public JSONHandler() {
        this.data = new HashMap<>();
    }

    public void appendData(String key, String message) {
        data.put(key, message);
    }

    public String getJSON() {
        return getJSON(data);
    }

    public String getJSON(Map<String, String> newMap) {
        StringBuilder result = new StringBuilder();

        result.append("{");
        for (String i : newMap.keySet()) {
            result.append("\"")
                    .append(i)
                    .append("\": ")
                    .append("\"")
                    .append(newMap.get(i))
                    .append("\",");
        }
        result.deleteCharAt(result.length()-1); // deleting last comma
        result.append("}");
        System.out.println(result);
        return result.toString();


    }

    public Map<String, String> parse(String JSONString) {
        String[] rawData = JSONString.split("\"");
        for (int i = 1; i < rawData.length - 1;) {
            if (i % 2 == 1) {
                data.put(rawData[i], rawData[i+2]);
                i += 4;
            }
        }
        return data;
    }
}
