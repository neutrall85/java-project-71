package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.JsonSerializationException;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class Json {
    private static final String STATUS = "status";
    private static final String VALUE = "value";

    private Json() { }

    public static String createJson(List<Map<String, Object>> diff) {
        Map<String, Object> result = new TreeMap<>();
        for (Map<String, Object> entry : diff) {
            String key = (String) entry.get("key");
            String type = (String) entry.get("type");
            Object value1 = entry.get("value1");
            Object value2 = entry.get("value2");
            Object value = entry.get(VALUE);

            switch (type) {
                case "added":
                    result.put(key, Map.of(STATUS, "added", VALUE, value));
                    break;
                case "deleted":
                    result.put(key, Map.of(STATUS, "removed", VALUE, value));
                    break;
                case "unchanged":
                    result.put(key, Map.of(STATUS, "unchanged", VALUE, value));
                    break;
                case "changed":
                    result.put(key, Map.of(STATUS, "changed", "from", value1, "to", value2));
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный тип изменения: " + type);
            }
        }
        return toJson(result);
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
