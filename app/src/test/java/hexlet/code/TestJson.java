package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.Json;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestJson {

    @Test
    void testEmptyMaps() {
        Map<String, Object> first = new HashMap<>();
        Map<String, Object> second = new HashMap<>();

        String result = Json.createJson(first, second);
        assertTrue(result.contains("{ }"));
    }

    @Test
    void testAddedKey() {
        Map<String, Object> first = new HashMap<>();
        Map<String, Object> second = new HashMap<>();
        second.put("key", "value");

        String result = Json.createJson(first, second);
        assertTrue(result.contains("\"status\" : \"added\""));
        assertTrue(result.contains("\"value\" : \"value\""));
    }

    @Test
    void testRemovedKey() {
        Map<String, Object> first = new HashMap<>();
        first.put("key", "value");
        Map<String, Object> second = new HashMap<>();

        String result = Json.createJson(first, second);
        assertTrue(result.contains("\"status\" : \"removed\""));
        assertTrue(result.contains("\"value\" : \"value\""));
    }

    @Test
    void testChangedKey() {
        Map<String, Object> first = new HashMap<>();
        first.put("key", "old");
        Map<String, Object> second = new HashMap<>();
        second.put("key", "new");

        String result = Json.createJson(first, second);
        assertTrue(result.contains("\"status\" : \"changed\""));
        assertTrue(result.contains("\"from\" : \"old\""));
        assertTrue(result.contains("\"to\" : \"new\""));
    }

    @Test
    void testUnchangedKey() {
        Map<String, Object> first = new HashMap<>();
        first.put("key", "value");
        Map<String, Object> second = new HashMap<>();
        second.put("key", "value");

        String result = Json.createJson(first, second);
        assertTrue(result.contains("\"status\" : \"unchanged\""));
        assertTrue(result.contains("\"value\" : \"value\""));
    }

    @Test
    void testNullValues() {
        Map<String, Object> first = new HashMap<>();
        first.put("key1", null);
        first.put("key2", "value");
        Map<String, Object> second = new HashMap<>();
        second.put("key1", "value");
        second.put("key2", null);

        String result = Json.createJson(first, second);
        assertTrue(result.contains("\"status\" : \"added\"")); // для key1
        assertTrue(result.contains("\"status\" : \"removed\"")); // для key2
    }

    @Test
    void testMultipleKeys() {
        Map<String, Object> first = new HashMap<>();
        first.put("key1", "value1");
        first.put("key2", "value2");

        Map<String, Object> second = new HashMap<>();
        second.put("key1", "value1");
        second.put("key3", "value3");

        String result = Json.createJson(first, second);

        Map<String, Object> expected = new TreeMap<>();
        expected.put("key1", Map.of(
                "status", "unchanged",
                "value", "value1"
        ));
        expected.put("key2", Map.of(
                "status", "removed",
                "value", "value2"
        ));
        expected.put("key3", Map.of(
                "status", "added",
                "value", "value3"
        ));

        assertEquals(toJson(expected), result);
    }

    private String toJson(Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
