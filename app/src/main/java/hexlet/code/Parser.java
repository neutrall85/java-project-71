package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

import static java.util.Objects.requireNonNull;

public final class Parser {

    private Parser() { }

    public static Map<String, Object> parse(String content, String format) throws JsonProcessingException {
        ObjectMapper mapper = getMapper(format);
        return mapper.readValue(content, new TypeReference<>() { });
    }

    private static ObjectMapper getMapper(String format) {
        requireNonNull(format, "Формат данных не может быть null");

        return switch (format) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new YAMLMapper();
            default -> throw new IllegalArgumentException("Неподдерживаемый формат данных: " + format);
        };
    }
}
