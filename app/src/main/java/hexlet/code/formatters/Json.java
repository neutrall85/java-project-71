package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.JsonSerializationException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Json {
    private static final String STATUS = "status";
    private static final String VALUE = "value";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private Json() { }

    public static String createJson(List<Map<String, Object>> diff) throws JsonSerializationException {
        Map<String, Object> result = new LinkedHashMap<>();

        for (Map<String, Object> entry : diff) {
            String key = (String) entry.get("key");
            if (key == null) {
                throw new IllegalArgumentException("Ключ не может быть null");
            }

            String type = (String) entry.getOrDefault("type", null);
            Object value1 = entry.getOrDefault("value1", null);
            Object value2 = entry.getOrDefault("value2", null);
            Object value = entry.getOrDefault(VALUE, null);

            switch (type) {
                case "added":
                    Map<String, Object> addedMap = new LinkedHashMap<>();
                    addedMap.put(STATUS, "added");
                    addedMap.put(VALUE, value);
                    result.put(key, addedMap);
                    break;
                case "deleted":
                    Map<String, Object> deletedMap = new LinkedHashMap<>();
                    deletedMap.put(STATUS, "removed");
                    deletedMap.put(VALUE, value);
                    result.put(key, deletedMap);
                    break;
                case "unchanged":
                    Map<String, Object> unchangedMap = new LinkedHashMap<>();
                    unchangedMap.put(STATUS, "unchanged");
                    unchangedMap.put(VALUE, value);
                    result.put(key, unchangedMap);
                    break;
                case "changed":
                    Map<String, Object> changedMap = new LinkedHashMap<>();
                    changedMap.put(STATUS, "changed");
                    changedMap.put("from", value1);
                    changedMap.put("to", value2);
                    result.put(key, changedMap);
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный тип изменения: " + type);
            }
        }
        return toJson(result);
    }

    public static String toJson(Map<String, Object> map) {
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new JsonSerializationException("Ошибка при сериализации в JSON", e);
        }
    }
}
