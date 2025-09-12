package hexlet.code;

import hexlet.code.formatters.Stylish;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestStylish {

    @Test
    void testCreateStylishEmptyMaps() {
        Map<String, Object> first = new HashMap<>();
        Map<String, Object> second = new HashMap<>();

        String result = Stylish.createStylish(first, second, 0);
        assertEquals("{\n}", result);
    }

    @Test
    void testCreateStylishOnlyFirstMap() {
        Map<String, Object> first = Map.of("key1", "value1");
        Map<String, Object> second = new HashMap<>();

        String result = Stylish.createStylish(first, second, 0);
        assertEquals("{\n  - key1: value1\n}", result);
    }

    @Test
    void testCreateStylishOnlySecondMap() {
        Map<String, Object> first = new HashMap<>();
        Map<String, Object> second = Map.of("key1", "value1");

        String result = Stylish.createStylish(first, second, 0);
        assertEquals("{\n  + key1: value1\n}", result);
    }

    @Test
    void testCreateStylishSameKeys() {
        Map<String, Object> first = Map.of("key1", "value1");
        Map<String, Object> second = Map.of("key1", "value1");

        String result = Stylish.createStylish(first, second, 0);
        assertEquals("{\n    key1: value1\n}", result);
    }

    @Test
    void testCreateStylishDifferentValues() {
        Map<String, Object> first = Map.of("key1", "value1");
        Map<String, Object> second = Map.of("key1", "value2");

        String result = Stylish.createStylish(first, second, 0);
        assertEquals("{\n  - key1: value1\n  + key1: value2\n}", result);
    }

    @Test
    void testCreateStylishMixedKeys() {
        Map<String, Object> first = Map.of("key1", "value1", "key2", "value2");
        Map<String, Object> second = Map.of("key2", "value2", "key3", "value4");

        String result = Stylish.createStylish(first, second, 0);
        assertEquals("{\n  - key1: value1\n    key2: value2\n  + key3: value4\n}", result);
    }

    @Test
    void testCreateStylishWithIndent() {
        Map<String, Object> first = Map.of("key1", "value1");
        Map<String, Object> second = Map.of("key1", "value1");

        String result = Stylish.createStylish(first, second, 2);
        assertEquals("  {\n      key1: value1\n  }", result);
    }

    @Test
    void testAreEqualWithNulls() {
        assertTrue(Stylish.areEqual(null, null));
        assertFalse(Stylish.areEqual(null, "value"));
        assertFalse(Stylish.areEqual("value", null));
    }

    @Test
    void testAreEqualWithValues() {
        assertTrue(Stylish.areEqual("value", "value"));
        assertFalse(Stylish.areEqual("value1", "value2"));
    }

    @Test
    void testAppendLine() {
        String result = Stylish.appendLine(0, "  + ", "key", "value");
        assertEquals("  + key: value\n", result);
    }
}
