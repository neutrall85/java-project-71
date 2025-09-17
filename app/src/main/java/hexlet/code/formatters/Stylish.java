package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Stylish {

    private Stylish() { }

    public static String createStylish(List<Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (Map<String, Object> entry : diff) {
            String key = (String) entry.get("key");
            String type = (String) entry.get("type");
            Object value = entry.get("value");
            Object value1 = entry.getOrDefault("value1", null);
            Object value2 = entry.getOrDefault("value2", null);

            switch (type) {
                case "added":
                    result.append(appendLine("  + ", key, value));
                    break;
                case "deleted":
                    result.append(appendLine("  - ", key, value));
                    break;
                case "unchanged":
                    result.append(appendLine("    ", key, value));
                    break;
                case "changed":
                    result.append(appendLine("  - ", key, value1))
                            .append(appendLine("  + ", key, value2));
                    break;
                default:
                    result.append("!!Ошибка: неизвестный тип изменения '")
                            .append(type)
                            .append("'!!\n");
            }
        }

        result.append("}");
        return result.toString();
    }

    public static String appendLine(String prefix, String key, Object value) {
        return prefix + key + ": " + value + "\n";
    }
}
