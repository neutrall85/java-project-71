package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestApp {

    @Test
    void testSuccessfulExecution() {
        App app = new App();
        app.setFilepath1("file1.json");
        app.setFilepath2("file2.json");
        app.setFormat("stylish");

        String result = app.call();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testDifferentFormats() {
        App app = new App();
        app.setFilepath1("file1.json");
        app.setFilepath2("file2.json");
        app.setFormat("stylish");
        String stylishResult = app.call();
        assertNotNull(stylishResult);

        app.setFormat("plain");
        String plainResult = app.call();
        assertNotNull(plainResult);

        app.setFormat("json");
        String jsonResult = app.call();
        assertNotNull(jsonResult);

        assertNotEquals(stylishResult, plainResult);
        assertNotEquals(plainResult, jsonResult);
        assertNotEquals(stylishResult, jsonResult);
    }

    @Test
    void testInvalidFormat() {
        App app = new App();
        app.setFilepath1("file1.json");
        app.setFilepath2("file2.json");
        app.setFormat("invalid-format");

        String result = app.call();
        assertTrue(result.isEmpty());
    }

    @Test
    void testMissingFile() {
        App app = new App();
        app.setFilepath1("nonexistent-file.json");
        app.setFilepath2("file2.json");
        app.setFormat("stylish");

        String result = app.call();
        assertTrue(result.isEmpty());
    }
}
