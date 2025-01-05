package software.ulpgc.arquitecture.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Barchart {
    private final Map<String, Integer> count;
    private final String title;
    private final String xAxis;
    private final String yAxis;

    public Barchart(String title, String xAxis, String yAxis) {
        this.count = new HashMap<>();
        this.title = title;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public int get(String category) {
        return count.get(category);
    }

    public void put(String key, int  value) {
        count.put(key, value);
    }

    public String title(){return title;}

    public String xAxis(){return xAxis;}

    public String yAxis(){return yAxis;}

    public Set<String> keySet(){return count.keySet();}
}
