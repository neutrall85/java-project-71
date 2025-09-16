package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Stylish {

    private Stylish() { }

    public static String createStylish(List<Map<String, Object>> diff, int count) {
        StringBuilder result = new StringBuilder();
        String space = " ";
        result.append(space.repeat(count)).append("{\n");

        for (Map<String, Object> entry : diff) {
            String key = (String) entry.get("key");
            String type = (String) entry.get("type");
            Object value = entry.get("value");
            Object value1 = entry.getOrDefault("value1", null);
            Object value2 = entry.getOrDefault("value2", null);

            switch (type) {
                case "added":
                    result.append(appendLine(count, "  + ", key, value));
                    break;
                case "deleted":
                    result.append(appendLine(count, "  - ", key, value));
                    break;
                case "unchanged":
                    result.append(appendLine(count, "    ", key, value));
                    break;
                case "changed":
                    result.append(appendLine(count, "  - ", key, value1))
                            .append(appendLine(count, "  + ", key, value2));
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный тип изменения: " + type);

            }
        }

        result.append(space.repeat(count)).append("}");
        return result.toString();
    }

    public static String appendLine(int count, String prefix, String key, Object value) {
        String space = " ";
        return space.repeat(count) + prefix + key + ": " + value + "\n";
    }
}
