package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestParser {
    private static final int TEST_NUM = 30;

    @Test
    void testParseJsonSuccess() throws JsonProcessingException {
        String json = "{\"name\": \"John\", \"age\": 30, \"city\": \"New York\"}";
        Map<String, Object> result = Parser.parse(json, "json");

        assertEquals("John", result.get("name"));
        assertEquals(TEST_NUM, result.get("age"));
        assertEquals("New York", result.get("city"));
    }

    @Test
    void testParseYamlSuccess() throws JsonProcessingException {
        String yaml = """
                name: John
                age: 30
                city: New York
                """;
        Map<String, Object> result = Parser.parse(yaml, "yaml");

        assertEquals("John", result.get("name"));
        assertEquals(TEST_NUM, result.get("age"));
        assertEquals("New York", result.get("city"));
    }

    @Test
    void testParseYmlSuccess() throws JsonProcessingException {
        String yml = """
                name: John
                age: 30
                city: New York
                """;
        Map<String, Object> result = Parser.parse(yml, "yml");

        assertEquals("John", result.get("name"));
        assertEquals(TEST_NUM, result.get("age"));
        assertEquals("New York", result.get("city"));
    }

    @Test
    void testInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () ->
            Parser.parse("{}", "xml")
        );
    }

    @Test
    void testNullFormat() {
        assertThrows(NullPointerException.class, () ->
            Parser.parse("{}", null)
        );
    }
}
