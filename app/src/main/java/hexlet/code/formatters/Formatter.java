package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public final class Formatter {

    private Formatter() { }

    public static String format(String formatName, List<Map<String, Object>> diff) throws JsonProcessingException {

        return switch (formatName) {
            case "plain" -> Plain.createPlain(diff);
            case "stylish" -> Stylish.createStylish(diff);
            case "json" -> Json.createJson(diff);
            default -> throw new IllegalArgumentException("Unknown format: " + formatName);
        };
    }
}
