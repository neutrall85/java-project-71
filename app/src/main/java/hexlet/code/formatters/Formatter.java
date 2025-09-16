package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Formatter {

    private Formatter() { }

    private static final int INITIAL_INDENT = 0;

    public static String format(String formatName, List<Map<String, Object>> diff) {
        return switch (formatName) {
            case "plain" -> Plain.createPlain(diff);
            case "stylish" -> Stylish.createStylish(diff, INITIAL_INDENT);
            case "json" -> Json.createJson(diff);
            default -> throw new IllegalArgumentException("Неподдерживаемый формат: " + formatName);
        };
    }
}
