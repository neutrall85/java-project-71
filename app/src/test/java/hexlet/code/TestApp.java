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
        app.filepath1 = "file1.json";
        app.filepath2 = "file2.json";
        app.format = "stylish";

        String result = app.call();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testDifferentFormats() {
        App app = new App();
        app.filepath1 = "file1.json";
        app.filepath2 = "file2.json";

        app.format = "stylish";
        String stylishResult = app.call();
        assertNotNull(stylishResult);

        app.format = "plain";
        String plainResult = app.call();
        assertNotNull(plainResult);

        app.format = "json";
        String jsonResult = app.call();
        assertNotNull(jsonResult);

        assertNotEquals(stylishResult, plainResult);
        assertNotEquals(plainResult, jsonResult);
        assertNotEquals(stylishResult, jsonResult);
    }

    @Test
    void testInvalidFormat() {
        App app = new App();
        app.filepath1 = "file1.json";
        app.filepath2 = "file2.json";
        app.format = "invalid-format";

        String result = app.call();
        assertTrue(result.isEmpty());
    }

    @Test
    void testMissingFile() {
        App app = new App();
        app.filepath1 = "nonexistent-file.json";
        app.filepath2 = "file2.json";
        app.format = "stylish";

        String result = app.call();
        assertTrue(result.isEmpty());
    }
}
