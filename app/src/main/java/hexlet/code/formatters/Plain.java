package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Plain {

    private Plain() { }

    public static String createPlain(List<Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> entry : diff) {
            String key = (String) entry.get("key");
            String type = (String) entry.get("type");

            switch (type) {
                case "added":
                    result.append(addedProperty(key, entry.get("value")));
                    break;
                case "deleted":
                    result.append(removedProperty(key));
                    break;
                case "changed":
                    result.append(updatedProperty(
                            key,
                            entry.get("value1"),
                            entry.get("value2")
                    ));
                    break;
                default:
                    break;
            }
        }

        return result.toString().trim();
    }

    private static String addedProperty(String key, Object value) {
        return String.format("Property '%s' was added with value: %s%n",
                key, formatValue(value));
    }

    private static String removedProperty(String key) {
        return String.format("Property '%s' was removed%n", key);
    }

    private static String updatedProperty(String key, Object oldValue, Object newValue) {
        return String.format("Property '%s' was updated. From %s to %s%n",
                key, formatValue(oldValue), formatValue(newValue));
    }

    public static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (isComplexValue(value)) {
            return "[complex value]";
        }
        if (value instanceof Boolean) {
            return value.toString();
        }
        if (value instanceof Number) {
            return value.toString();
        }
        return String.format("'%s'", value);
    }

    public static boolean isComplexValue(Object value) {
        return value instanceof Map
                || value instanceof Object[]
                || value instanceof java.util.Collection;
    }
}
