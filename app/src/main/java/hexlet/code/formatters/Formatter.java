package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public final class Formatter {

    private Formatter() { }

    private static final Set<String> SUPPORTED_FORMATS = new HashSet<>();

    static {
        SUPPORTED_FORMATS.add("plain");
        SUPPORTED_FORMATS.add("stylish");
        SUPPORTED_FORMATS.add("json");
    }

    public static String format(String formatName, List<Map<String, Object>> diff) throws JsonProcessingException {
        if (!SUPPORTED_FORMATS.contains(formatName)) {
            return "Ошибка: формат '" + formatName + "' не поддерживается";
        }

        return switch (formatName) {
            case "plain" -> Plain.createPlain(diff);
            case "stylish" -> Stylish.createStylish(diff);
            case "json" -> Json.createJson(diff);
            default -> "Непредвиденная ошибка обработки формата";
        };
    }
}
