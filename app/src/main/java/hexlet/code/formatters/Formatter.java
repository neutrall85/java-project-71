package hexlet.code.formatters;

import java.util.Map;

public final class Formatter {

    private Formatter() { }

    private static final int INITIAL_INDENT = 0;

    public static String format(String formatName, Map<String, Object> first, Map<String, Object> second) {
        return switch (formatName) {
            case "plain" -> Plain.createPlain(first, second);
            case "stylish" -> Stylish.createStylish(first, second, INITIAL_INDENT);
            case "json" -> Json.createJson(first, second);
            default -> throw new IllegalArgumentException("Неподдерживаемый формат: " + formatName);
        };
    }
}
