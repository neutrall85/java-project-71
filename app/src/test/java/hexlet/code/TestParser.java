package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestParser {

    @Test
    void testParseJsonSuccess() {
        String json = "{\"name\": \"John\", \"age\": 30, \"city\": \"New York\"}";
        Map<String, Object> result = Parser.parse(json, "json");

        assertEquals("John", result.get("name"));
        assertEquals(30, result.get("age"));
        assertEquals("New York", result.get("city"));
    }

    @Test
    void testParseYamlSuccess() {
        String yaml = """
                name: John
                age: 30
                city: New York
                """;
        Map<String, Object> result = Parser.parse(yaml, "yaml");

        assertEquals("John", result.get("name"));
        assertEquals(30, result.get("age"));
        assertEquals("New York", result.get("city"));
    }

    @Test
    void testParseYmlSuccess() {
        String yml = """
                name: John
                age: 30
                city: New York
                """;
        Map<String, Object> result = Parser.parse(yml, "yml");

        assertEquals("John", result.get("name"));
        assertEquals(30, result.get("age"));
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

    @Test
    void testInvalidJson() {
        assertThrows(IllegalArgumentException.class, () ->
            Parser.parse("{invalid json", "json")
        );
    }

    @Test
    void testEmptyContent() {
        assertThrows(IllegalArgumentException.class, () ->
            Parser.parse("", "json")
        );
    }
}
