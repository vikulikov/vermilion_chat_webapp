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

        result.append("{\n");
        for (String i : newMap.keySet()) {
            result.append("\"").append(i).append("\": ").append("\"").append(newMap.get(i)).append("\"\n");
        }
        result.append("}");

        return result.toString();
    }
}
