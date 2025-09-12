package hexlet.code.formatters;

import java.util.Map;
import java.util.Objects;

public final class Plain {

    private Plain() { }

    public static String createPlain(Map<String, Object> first, Map<String, Object> second) {
        StringBuilder result = new StringBuilder();

        var allKeys = new java.util.TreeSet<>(first.keySet());
        allKeys.addAll(second.keySet());

        for (String key : allKeys) {
            Object oldValue = first.get(key);
            Object newValue = second.get(key);

            boolean existsInFirst = first.containsKey(key);
            boolean existsInSecond = second.containsKey(key);

            if (!existsInSecond) {
                result.append(removedProperty(key));
            } else if (!existsInFirst) {
                result.append(addedProperty(key, newValue));
            } else if (!Objects.equals(oldValue, newValue)) {
                result.append(updatedProperty(key, oldValue, newValue));
            }
        }

        return result.toString().stripTrailing();
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

    private static String formatValue(Object value) {
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



    private static boolean isComplexValue(Object value) {
        return value instanceof Map || value instanceof Object[] || value instanceof java.util.Collection;
    }
}
