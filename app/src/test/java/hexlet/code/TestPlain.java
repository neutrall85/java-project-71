package hexlet.code;

import hexlet.code.formatters.Plain;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestPlain {
    private static final int TEST_NUM1 = 123;
    private static final double TEST_NUM2 = 45.67;

    @Test
    void testCreatePlainWithAddedProperty() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "added");
        entry.put("value", "testValue");
        diff.add(entry);

        String expected = "Property 'testKey' was added with value: 'testValue'";
        String result = Plain.createPlain(diff);

        assertEquals(expected, result);
    }

    @Test
    void testCreatePlainWithRemovedProperty() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "deleted");
        diff.add(entry);

        String expected = "Property 'testKey' was removed";
        String result = Plain.createPlain(diff);
        assertEquals(expected, result);
    }

    @Test
    void testCreatePlainWithUpdatedProperty() {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Object> entry = new HashMap<>();
        entry.put("key", "testKey");
        entry.put("type", "changed");
        entry.put("value1", "oldValue");
        entry.put("value2", "newValue");
        diff.add(entry);

        String expected = "Property 'testKey' was updated. From 'oldValue' to 'newValue'";
        String result = Plain.createPlain(diff);
        assertEquals(expected, result);
    }

    @Test
    void testCreatePlainWithMultipleChanges() {
        List<Map<String, Object>> diff = new ArrayList<>();

        Map<String, Object> entry1 = new HashMap<>();
        entry1.put("key", "key1");
        entry1.put("type", "added");
        entry1.put("value", "value1");

        Map<String, Object> entry2 = new HashMap<>();
        entry2.put("key", "key2");
        entry2.put("type", "deleted");

        Map<String, Object> entry3 = new HashMap<>();
        entry3.put("key", "key3");
        entry3.put("type", "changed");
        entry3.put("value1", "old");
        entry3.put("value2", "new");

        diff.add(entry1);
        diff.add(entry2);
        diff.add(entry3);

        String expected = """
                        Property 'key1' was added with value: 'value1'
                        Property 'key2' was removed
                        Property 'key3' was updated. From 'old' to 'new'""";
        String result = Plain.createPlain(diff);
        assertEquals(expected, result);
    }

    @Test
    void testFormatValueWithString() {
        assertEquals("'test'", Plain.formatValue("test"));
    }

    @Test
    void testFormatValueWithNumber() {
        assertEquals("123", Plain.formatValue(TEST_NUM1));
        assertEquals("45.67", Plain.formatValue(TEST_NUM2));
    }

    @Test
    void testFormatValueWithBoolean() {
        assertEquals("true", Plain.formatValue(true));
        assertEquals("false", Plain.formatValue(false));
    }

    @Test
    void testFormatValueWithNull() {
        assertEquals("null", Plain.formatValue(null));
    }

    @Test
    void testIsComplexValue() {
        assertTrue(Plain.isComplexValue(new HashMap<>()), "Map должна быть сложной структурой");
        assertTrue(Plain.isComplexValue(new ArrayList<>()), "List должна быть сложной структурой");
        assertTrue(Plain.isComplexValue(new Object[]{}), "Массив должен быть сложной структурой");
        assertFalse(Plain.isComplexValue("string"), "Строка не должна быть сложной структурой");
        assertFalse(Plain.isComplexValue(TEST_NUM1), "Число не должно быть сложной структурой");
        assertFalse(Plain.isComplexValue(true), "Булево значение не должно быть сложной структурой");
        assertFalse(false, "Null не должен быть сложной структурой");
    }

    @Test
    void testFormatValueWithComplexValue() {
        assertEquals("[complex value]", Plain.formatValue(new HashMap<>()),
                "Для Map должен возвращаться [complex value]");
        assertEquals("[complex value]", Plain.formatValue(new ArrayList<>()),
                "Для List должен возвращаться [complex value]");
        assertEquals("[complex value]", Plain.formatValue(new Object[]{}),
                "Для массива должен возвращаться [complex value]");
    }

    @Test
    void testEmptyDiff() {
        List<Map<String, Object>> diff = Collections.emptyList();
        String result = Plain.createPlain(diff);
        assertTrue(result.isEmpty(), "Для пустого diff должен возвращаться пустой результат");
    }

    @Test
    void testMixedValues() {
        List<Map<String, Object>> diff = new ArrayList<>();

        Map<String, Object> entry1 = new HashMap<>();
        entry1.put("key", "key1");
        entry1.put("type", "added");
        entry1.put("value", true);

        Map<String, Object> entry2 = new HashMap<>();
        entry2.put("key", "key2");
        entry2.put("type", "added");
        entry2.put("value", TEST_NUM2);

        Map<String, Object> entry3 = new HashMap<>();
        entry3.put("key", "key3");
        entry3.put("type", "added");
        entry3.put("value", "string");

        Map<String, Object> entry4 = new HashMap<>();
        entry4.put("key", "key4");
        entry4.put("type", "added");
        entry4.put("value", new HashMap<>());

        diff.add(entry1);
        diff.add(entry2);
        diff.add(entry3);
        diff.add(entry4);

        String expected = """
                Property 'key1' was added with value: true
                Property 'key2' was added with value: 45.67
                Property 'key3' was added with value: 'string'
                Property 'key4' was added with value: [complex value]""";

        assertEquals(expected, Plain.createPlain(diff), "Неверный результат форматирования смешанных значений");
    }
}
