package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestJson {

    @Test
    void testCreateJsonAdded() throws JsonProcessingException {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "added");
        entry.put("value", "newValue");

        diff.add(entry);

        String result = Json.createJson(diff);
        assertTrue(result.contains("\"testKey\""));
        assertTrue(result.contains("\"type\" : \"added\""));
        assertTrue(result.contains("\"value\" : \"newValue\""));
    }

    @Test
    void testCreateJsonDeleted() throws JsonProcessingException {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "deleted");
        entry.put("value", "oldValue");

        diff.add(entry);

        String result = Json.createJson(diff);
        assertTrue(result.contains("\"testKey\""));
        assertTrue(result.contains("\"type\" : \"deleted\""));
        assertTrue(result.contains("\"value\" : \"oldValue\""));
    }

    @Test
    void testCreateJsonUnchanged() throws JsonProcessingException {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "unchanged");
        entry.put("value", "sameValue");

        diff.add(entry);

        String result = Json.createJson(diff);
        assertTrue(result.contains("\"testKey\""));
        assertTrue(result.contains("\"type\" : \"unchanged\""));
        assertTrue(result.contains("\"value\" : \"sameValue\""));
    }

    @Test
    void testCreateJsonChanged() throws JsonProcessingException {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "changed");
        entry.put("value1", "oldValue");
        entry.put("value2", "newValue");

        diff.add(entry);

        String result = Json.createJson(diff);
        assertTrue(result.contains("\"testKey\""));
        assertTrue(result.contains("\"type\" : \"changed\""));
        assertTrue(result.contains("\"value1\" : \"oldValue\""));
        assertTrue(result.contains("\"value2\" : \"newValue\""));
    }

    @Test
    void testCreateJsonMultipleEntries() throws JsonProcessingException {
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
        assertTrue(result.contains("\"type\" : \"added\""));
        assertTrue(result.contains("\"value\" : \"value1\""));
        assertTrue(result.contains("\"testKey2\""));
        assertTrue(result.contains("\"type\" : \"deleted\""));
        assertTrue(result.contains("\"value\" : \"value2\""));
    }

    @Test
    void testCreateJsonEmptyList() throws JsonProcessingException {
        List<Map<String, Object>> diff = Collections.emptyList();
        String result = Json.createJson(diff);
        assertEquals("[ ]", result.trim());
    }

    @Test
    void testCreateJsonWithNullValues() throws JsonProcessingException {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "added");
        entry.put("value", null);

        diff.add(entry);

        String json = Json.createJson(diff);

        assertTrue(json.contains("\"testKey\""));
        assertTrue(json.contains("\"type\" : \"added\""));
        assertTrue(json.contains("\"value\" : null"));
    }
}
