package hexlet.code;

import hexlet.code.formatters.Json;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestJson {
    private static final int TEST_NUM1 = 42;

    @Test
    void testCreateJsonAdded() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "added");
        entry.put("value", "newValue");

        diff.add(entry);

        String result = Json.createJson(diff);
        assertTrue(result.contains("\"testKey\""));
        assertTrue(result.contains("\"status\" : \"added\""));
        assertTrue(result.contains("\"value\" : \"newValue\""));
    }

    @Test
    void testCreateJsonDeleted() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "deleted");
        entry.put("value", "oldValue");

        diff.add(entry);

        String result = Json.createJson(diff);
        assertTrue(result.contains("\"testKey\""));
        assertTrue(result.contains("\"status\" : \"removed\""));
        assertTrue(result.contains("\"value\" : \"oldValue\""));
    }

    @Test
    void testCreateJsonUnchanged() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "unchanged");
        entry.put("value", "sameValue");

        diff.add(entry);

        String result = Json.createJson(diff);
        assertTrue(result.contains("\"testKey\""));
        assertTrue(result.contains("\"status\" : \"unchanged\""));
        assertTrue(result.contains("\"value\" : \"sameValue\""));
    }

    @Test
    void testCreateJsonChanged() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "changed");
        entry.put("value1", "oldValue");
        entry.put("value2", "newValue");

        diff.add(entry);

        String result = Json.createJson(diff);
        assertTrue(result.contains("\"testKey\""));
        assertTrue(result.contains("\"status\" : \"changed\""));
        assertTrue(result.contains("\"from\" : \"oldValue\""));
        assertTrue(result.contains("\"to\" : \"newValue\""));
    }

    @Test
    void testCreateJsonMultipleEntries() {
        List<Map<String, Object>> diff = new ArrayList<>();

        Map<String, Object> entry1 = new HashMap<>();
        entry1.put("key", "testKey1");
        entry1.put("type", "added");
        entry1.put("value", "value1");

        Map<String, Object> entry2 = new HashMap<>();
        entry2.put("key", "testKey2");
        entry2.put("type", "deleted");
        entry2.put("value", "value2");

        diff.add(entry1);
        diff.add(entry2);

        String result = Json.createJson(diff);

        assertTrue(result.contains("\"testKey1\""));
        assertTrue(result.contains("\"status\" : \"added\""));
        assertTrue(result.contains("\"value\" : \"value1\""));
        assertTrue(result.contains("\"testKey2\""));
        assertTrue(result.contains("\"status\" : \"removed\""));
        assertTrue(result.contains("\"value\" : \"value2\""));
    }

    @Test
    void testCreateJsonUnknownType() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "unknown");
        entry.put("value", "someValue");

        diff.add(entry);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                Json.createJson(diff)
        );

        assertEquals("Неизвестный тип изменения: unknown", exception.getMessage());
    }

    @Test
    void testCreateJsonEmptyList() {
        List<Map<String, Object>> diff = Collections.emptyList();
        String result = Json.createJson(diff);
        assertEquals("{ }", result.trim());
    }

    @Test
    void testToJsonSimpleMap() {
        Map<String, Object> map = Map.of("key", "value");
        String result = Json.toJson(map);
        assertEquals("{\n  \"key\" : \"value\"\n}", result);
    }

    @Test
    void testToJsonComplexMap() {
        Map<String, Object> map = new TreeMap<>();
        map.put("number", TEST_NUM1);
        map.put("boolean", true);
        map.put("string", "test");
        map.put("array", Arrays.asList("a", "b", "c"));
        map.put("nested", Map.of("inner", "value"));

        String result = Json.toJson(map);
        assertTrue(result.contains("\"number\" : 42"));
        assertTrue(result.contains("\"boolean\" : true"));
        assertTrue(result.contains("\"string\" : \"test\""));
        assertTrue(result.contains("\"array\" : [ \"a\", \"b\", \"c\" ]"));
        assertTrue(result.contains("""
                "nested" : {
                    "inner" : "value"
                  }"""));
    }

    @Test
    void testToJsonEmptyMap() {
        Map<String, Object> map = new TreeMap<>();
        String result = Json.toJson(map);
        assertEquals("{ }", result);
    }

    @Test
    void testCreateJsonWithNullValues() {
        // Подготовка тестовых данных
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "added");
        entry.put("value", null);

        diff.add(entry);

        String json = Json.createJson(diff);

        assertTrue(json.contains("\"testKey\""));
        assertTrue(json.contains("\"status\" : \"added\""));
        assertTrue(json.contains("\"value\" : null"));
    }
}
