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

    public static String createJson(List<Map<String, Object>> diff) {
        if (diff == null) {
            throw new IllegalArgumentException("Данные не могут быть null");
        }
        Map<String, Object> result = new LinkedHashMap<>();
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

    public static String toJson(Map<String, Object> map) {
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new JsonSerializationException("Ошибка при сериализации в JSON", e);
        }
    }
}
