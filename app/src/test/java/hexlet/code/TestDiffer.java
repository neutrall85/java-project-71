package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestDiffer {

    private static final String JSON_FILE1 = "file1.json";
    private static final String JSON_FILE2 = "file2.json";
    private static final String YML_FILE1 = "file1.yml";
    private static final String YML_FILE2 = "file2.yml";

    // JSON input → JSON output
    @Test
    void testJsonInputJsonOutput() throws Exception {
        String result = Differ.generate(JSON_FILE1, JSON_FILE2, "json");
        assertNotNull(result);
        assertTrue(result.contains("{"));
        assertTrue(result.contains("\"added\""));
        assertTrue(result.contains("\"removed\""));
    }

    // JSON input → Stylish output
    @Test
    void testJsonInputStylishOutput() throws Exception {
        String result = Differ.generate(JSON_FILE1, JSON_FILE2, "stylish");
        assertNotNull(result);
        assertTrue(result.contains("- follow: false"));
        assertTrue(result.contains("- timeout: 50"));
    }

    // JSON input → Plain output
    @Test
    void testJsonInputPlainOutput() throws Exception {
        String result = Differ.generate(JSON_FILE1, JSON_FILE2, "plain");
        assertNotNull(result);
        assertTrue(result.contains("Property 'follow' was removed"));
        assertTrue(result.contains("Property 'timeout' was updated. From 50 to 20"));
    }

    // JSON input → Default output
    @Test
    void testJsonInputDefaultOutput() throws Exception {
        String result = Differ.generate(JSON_FILE1, JSON_FILE2);
        assertNotNull(result);
        assertTrue(result.contains("- follow: false"));
        assertTrue(result.contains("- timeout: 50"));
    }

    // YAML input → JSON output
    @Test
    void testYmlInputJsonOutput() throws Exception {
        String result = Differ.generate(YML_FILE1, YML_FILE2, "json");
        assertNotNull(result);
        assertTrue(result.contains("{"));
        assertTrue(result.contains("\"added\""));
        assertTrue(result.contains("\"removed\""));
    }

    // YAML input → Stylish output
    @Test
    void testYmlInputStylishOutput() throws Exception {
        String result = Differ.generate(YML_FILE1, YML_FILE2, "stylish");
        assertNotNull(result);
        assertTrue(result.contains("- follow: false"));
        assertTrue(result.contains("- timeout: 50"));
    }

    // YAML input → Plain output
    @Test
    void testYmlInputPlainOutput() throws Exception {
        String result = Differ.generate(YML_FILE1, YML_FILE2, "plain");
        assertNotNull(result);
        assertTrue(result.contains("Property 'follow' was removed"));
        assertTrue(result.contains("Property 'timeout' was updated. From 50 to 20"));
    }

    // YAML input → Default output
    @Test
    void testYmlInputDefaultOutput() throws Exception {
        String result = Differ.generate(YML_FILE1, YML_FILE2);
        assertNotNull(result);
        assertTrue(result.contains("- follow: false"));
        assertTrue(result.contains("- timeout: 50"));
    }
}
