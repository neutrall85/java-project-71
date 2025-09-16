package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class DifferenceCalc {
    private static final String VALUE = "value";

    private DifferenceCalc() { }

    static List<Map<String, Object>> calculateDifferences(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        List<Map<String, Object>> result = new ArrayList<>();

        for (String key : keys) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("key", key);
            if (!map1.containsKey(key)) {
                entry.put("type", "added");
                entry.put(VALUE, map2.get(key));
            } else if (!map2.containsKey(key)) {
                entry.put("type", "deleted");
                entry.put(VALUE, map1.get(key));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                entry.put("type", "unchanged");
                entry.put(VALUE, map1.get(key));
            } else {
                entry.put("type", "changed");
                entry.put("value1", map1.get(key));
                entry.put("value2", map2.get(key));
            }
            result.add(entry);
        }
        return result;
    }
}
