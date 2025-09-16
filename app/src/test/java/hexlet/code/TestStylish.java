package hexlet.code;

import hexlet.code.formatters.Stylish;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TestStylish {

    @Test
    void testCreateStylishWithAdded() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "added");
        entry.put("value", "testValue");
        diff.add(entry);

        String expected = "    {\n      + testKey: testValue\n    }";
        Assertions.assertEquals(expected, Stylish.createStylish(diff, 4));
    }

    @Test
    void testCreateStylishWithDeleted() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "deleted");
        entry.put("value", "testValue");
        diff.add(entry);

        String expected = "    {\n      - testKey: testValue\n    }";
        Assertions.assertEquals(expected, Stylish.createStylish(diff, 4));
    }

    @Test
    void testCreateStylishWithUnchanged() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "unchanged");
        entry.put("value", "testValue");
        diff.add(entry);

        String expected = "    {\n        testKey: testValue\n    }";
        Assertions.assertEquals(expected, Stylish.createStylish(diff, 4));
    }

    @Test
    void testCreateStylishWithChanged() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "changed");
        entry.put("value1", "oldValue");
        entry.put("value2", "newValue");
        diff.add(entry);

        String expected = "    {\n      - testKey: oldValue\n      + testKey: newValue\n    }";
        Assertions.assertEquals(expected, Stylish.createStylish(diff, 4));
    }

    @Test
    void testCreateStylishWithMultipleEntries() {
        List<Map<String, Object>> diff = new ArrayList<>();

        Map<String, Object> entry1 = new HashMap<>();
        entry1.put("key", "key1");
        entry1.put("type", "added");
        entry1.put("value", "value1");

        Map<String, Object> entry2 = new HashMap<>();
        entry2.put("key", "key2");
        entry2.put("type", "deleted");
        entry2.put("value", "value2");

        diff.add(entry1);
        diff.add(entry2);

        String expected = "    {\n      + key1: value1\n      - key2: value2\n    }";
        Assertions.assertEquals(expected, Stylish.createStylish(diff, 4));
    }

    @Test
    void testCreateStylishWithEmptyList() {
        List<Map<String, Object>> diff = new ArrayList<>();
        String expected = "    {\n    }";
        Assertions.assertEquals(expected, Stylish.createStylish(diff, 4));
    }

    @Test
    void testAppendLine() {
        String result = Stylish.appendLine(4, "  + ", "testKey", "testValue");
        String expected = "      + testKey: testValue\n";
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testUnknownTypeThrowsException() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "unknownType");
        entry.put("value", "testValue");
        diff.add(entry);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
            Stylish.createStylish(diff, 4)
        );
    }

    @Test
    void testCreateStylishWithNullValues() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "changed");
        entry.put("value1", null);
        entry.put("value2", null);
        diff.add(entry);

        String expected = "    {\n      - testKey: null\n      + testKey: null\n    }";
        Assertions.assertEquals(expected, Stylish.createStylish(diff, 4));
    }

    @Test
    void testCreateStylishWithComplexValues() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "changed");
        entry.put("value1", new HashMap<String, Object>() {{
                    put("nestedKey", "nestedValue");
                }});
        entry.put("value2", new HashMap<String, Object>() {{
                    put("nestedKey", "newNestedValue");
                }});
        diff.add(entry);

        String expected =
                "    {\n      - testKey: {nestedKey=nestedValue}\n      + testKey: {nestedKey=newNestedValue}\n    }";
        Assertions.assertEquals(expected, Stylish.createStylish(diff, 4));
    }

    @Test
    void testCreateStylishWithEmptyValues() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "unchanged");
        entry.put("value", "");
        diff.add(entry);

        String expected = "    {\n        testKey: \n    }";
        Assertions.assertEquals(expected, Stylish.createStylish(diff, 4));
    }
}
