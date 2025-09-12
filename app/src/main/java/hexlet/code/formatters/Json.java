package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.JsonSerializationException;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public final class Json {

    private static final String STATUS = "status";
    private static final String VALUE = "value";

    private Json() { }

    public static String createJson(Map<String, Object> first, Map<String, Object> second) {
        Map<String, Object> result = new TreeMap<>();
        processMap(result, first, second);
        return toJson(result);
    }

    private static void processMap(Map<String, Object> result,
                                   Map<String, Object> first,
                                   Map<String, Object> second) {
        Set<String> keys = new TreeSet<>(first.keySet());
        keys.addAll(second.keySet());

        for (String key : keys) {
            processKey(result, key, first, second);
        }
    }

    private static void processKey(Map<String, Object> result,
                                   String key,
                                   Map<String, Object> first,
                                   Map<String, Object> second) {
        if (!first.containsKey(key)) {
            result.put(key, createAddedMap(second.get(key)));
        } else if (!second.containsKey(key)) {
            result.put(key, createRemovedMap(first.get(key)));
        } else {
            processExistingKey(result, key, first, second);
        }
    }

    private static void processExistingKey(Map<String, Object> result,
                                           String key,
                                           Map<String, Object> first,
                                           Map<String, Object> second) {
        Object val1 = first.get(key);
        Object val2 = second.get(key);

        if (val1 == null || val2 == null) {
            handleNullValues(result, key, val1, val2);
        } else if (!val1.equals(val2)) {
            result.put(key, createChangedMap(val1, val2));
        } else {
            result.put(key, createUnchangedMap(val1));
        }
    }

    private static void handleNullValues(Map<String, Object> result,
                                         String key,
                                         Object val1,
                                         Object val2) {
        if (val1 == null && val2 == null) {
            result.put(key, createUnchangedMap(null));
        } else if (val1 == null) {
            result.put(key, createAddedMap(val2));
        } else {
            result.put(key, createRemovedMap(val1));
        }
    }

    private static Map<String, Object> createAddedMap(Object value) {
        return Map.of(STATUS, "added", VALUE, value);
    }

    private static Map<String, Object> createRemovedMap(Object value) {
        return Map.of(STATUS, "removed", VALUE, value);
    }

    private static Map<String, Object> createChangedMap(Object from, Object to) {
        return Map.of(STATUS, "changed", "from", from, "to", to);
    }

    private static Map<String, Object> createUnchangedMap(Object value) {
        return Map.of(STATUS, "unchanged", VALUE, value);
    }

    private static String toJson(Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new JsonSerializationException("Ошибка при сериализации в JSON", e);
        }
    }
}
