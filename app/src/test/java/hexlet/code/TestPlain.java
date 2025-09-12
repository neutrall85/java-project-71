package hexlet.code;

import hexlet.code.formatters.Plain;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestPlain {

    @Test
    void testEmptyMaps() {
        Map<String, Object> first = new HashMap<>();
        Map<String, Object> second = new HashMap<>();

        String result = Plain.createPlain(first, second);
        assertEquals("", result);
    }

    @Test
    void testAddedProperty() {
        Map<String, Object> first = new HashMap<>();
        Map<String, Object> second = new HashMap<>();
        second.put("key1", "value1");

        String result = Plain.createPlain(first, second);
        assertEquals("Property 'key1' was added with value: 'value1'", result);
    }

    @Test
    void testRemovedProperty() {
        Map<String, Object> first = new HashMap<>();
        first.put("key1", "value1");
        Map<String, Object> second = new HashMap<>();

        String result = Plain.createPlain(first, second);
        assertEquals("Property 'key1' was removed", result);
    }

    @Test
    void testUpdatedProperty() {
        Map<String, Object> first = new HashMap<>();
        first.put("key1", "oldValue");
        Map<String, Object> second = new HashMap<>();
        second.put("key1", "newValue");

        String result = Plain.createPlain(first, second);
        assertEquals("Property 'key1' was updated. From 'oldValue' to 'newValue'", result);
    }

    @Test
    void testMultipleChanges() {
        Map<String, Object> first = new HashMap<>();
        first.put("key1", "oldValue");
        first.put("key2", "value2");

        Map<String, Object> second = new HashMap<>();
        second.put("key1", "newValue");
        second.put("key3", "value3");

        String result = Plain.createPlain(first, second);
        String expected = """
                Property 'key1' was updated. From 'oldValue' to 'newValue'
                Property 'key2' was removed
                Property 'key3' was added with value: 'value3'""";
        assertEquals(expected, result);
    }

    @Test
    void testComplexValues() {
        Map<String, Object> first = new HashMap<>();
        first.put("key1", Map.of("nested", "value"));

        Map<String, Object> second = new HashMap<>();
        second.put("key1", Map.of("nested", "new value"));

        String result = Plain.createPlain(first, second);
        String expected = "Property 'key1' was updated. From [complex value] to [complex value]";
        assertEquals(expected, result);
    }

    @Test
    void testBooleanValues() {
        Map<String, Object> first = new HashMap<>();
        first.put("key1", true);

        Map<String, Object> second = new HashMap<>();
        second.put("key1", false);

        String result = Plain.createPlain(first, second);
        String expected = "Property 'key1' was updated. From true to false";
        assertEquals(expected, result);
    }

    @Test
    void testNullValues() {
        Map<String, Object> first = new HashMap<>();
        first.put("key1", null);

        Map<String, Object> second = new HashMap<>();
        second.put("key1", "value");

        String result = Plain.createPlain(first, second);
        String expected = "Property 'key1' was updated. From null to 'value'";
        assertEquals(expected, result);
    }

    @Test
    void testNumberValues() {
        Map<String, Object> first = new HashMap<>();
        first.put("key1", 123);

        Map<String, Object> second = new HashMap<>();
        second.put("key1", 456);

        String result = Plain.createPlain(first, second);
        String expected = "Property 'key1' was updated. From 123 to 456";
        assertEquals(expected, result);
    }

    @Test
    void testMixedTypes() {
        Map<String, Object> first = new HashMap<>();
        first.put("key1", "string");
        first.put("key2", 123);
        first.put("key3", true);
        first.put("key4", null);

        Map<String, Object> second = new HashMap<>();
        second.put("key1", 456);
        second.put("key2", "new string");
        second.put("key3", false);
        second.put("key4", "not null anymore");

        String result = Plain.createPlain(first, second);
        String expected = """
                Property 'key1' was updated. From 'string' to 456
                Property 'key2' was updated. From 123 to 'new string'
                Property 'key3' was updated. From true to false
                Property 'key4' was updated. From null to 'not null anymore'""";
        assertEquals(expected, result);
    }

    @Test
    void testComplexNestedValues() {
        Map<String, Object> first = new HashMap<>();
        first.put("key1", Map.of("nested1", "value1", "nested2", "value2"));

        Map<String, Object> second = new HashMap<>();
        second.put("key1", Map.of("nested1", "new value1", "nested3", "value3"));

        String result = Plain.createPlain(first, second);
        String expected = "Property 'key1' was updated. From [complex value] to [complex value]";
        assertEquals(expected, result);
    }

    @Test
    void testArrayValues() {
        Map<String, Object> first = new HashMap<>();
        first.put("key1", new String[]{"a", "b", "c"});

        Map<String, Object> second = new HashMap<>();
        second.put("key1", new String[]{"x", "y"});

        String result = Plain.createPlain(first, second);
        String expected = "Property 'key1' was updated. From [complex value] to [complex value]";
        assertEquals(expected, result);
    }

    @Test
    void testCollectionValues() {
        Map<String, Object> first = new HashMap<>();
        first.put("key1", List.of("a", "b", "c"));

        Map<String, Object> second = new HashMap<>();
        second.put("key1", List.of("x", "y"));

        String result = Plain.createPlain(first, second);
        String expected = "Property 'key1' was updated. From [complex value] to [complex value]";
        assertEquals(expected, result);
    }

    @Test
    void testEmptyValue() {
        Map<String, Object> first = new HashMap<>();
        first.put("key1", "");

        Map<String, Object> second = new HashMap<>();
        second.put("key1", "new value");

        String result = Plain.createPlain(first, second);
        String expected = "Property 'key1' was updated. From '' to 'new value'";
        assertEquals(expected, result);
    }
}
