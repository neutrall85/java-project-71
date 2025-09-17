package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;
import java.util.Map;

public final class Json {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private Json() { }

    public static String createJson(List<Map<String, Object>> diff) throws JsonProcessingException {
        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        return OBJECT_MAPPER.writeValueAsString(diff);
    }
}
