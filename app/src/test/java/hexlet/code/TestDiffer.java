package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDiffer {

    private static final String JSON_FILE1 = "file1.json";
    private static final String JSON_FILE2 = "file2.json";
    private static final String YML_FILE1 = "file1.yml";
    private static final String YML_FILE2 = "file2.yml";
    private static final String EXPECTED_JSON_RESULT = "src/test/resources/expected_json_result";
    private static final String EXPECTED_STYLISH_RESULT = "src/test/resources/expected_stylish_result";
    private static final String EXPECTED_PLAIN_RESULT = "src/test/resources/expected_plain_result";

    @Test
    void testJsonInputJsonOutput() throws Exception {
        String result = Differ.generate(JSON_FILE1, JSON_FILE2, "json");
        String expected = Files.readString(Path.of(EXPECTED_JSON_RESULT)).trim();
        assertEquals(expected, result);
    }

    @Test
    void testJsonInputStylishOutput() throws Exception {
        String result = Differ.generate(JSON_FILE1, JSON_FILE2, "stylish");
        String expected = Files.readString(Path.of(EXPECTED_STYLISH_RESULT)).trim();
        assertEquals(expected, result);
    }

    @Test
    void testJsonInputPlainOutput() throws Exception {
        String result = Differ.generate(JSON_FILE1, JSON_FILE2, "plain");
        String expected = Files.readString(Path.of(EXPECTED_PLAIN_RESULT)).trim();
        assertEquals(expected, result);
    }

    @Test
    void testJsonInputDefaultOutput() throws Exception {
        String result = Differ.generate(JSON_FILE1, JSON_FILE2);
        String expected = Files.readString(Path.of(EXPECTED_STYLISH_RESULT)).trim();
        assertEquals(expected, result);
    }

    @Test
    void testYmlInputJsonOutput() throws Exception {
        String result = Differ.generate(YML_FILE1, YML_FILE2, "json");
        String expected = Files.readString(Path.of(EXPECTED_JSON_RESULT)).trim();
        assertEquals(expected, result);
    }

    @Test
    void testYmlInputStylishOutput() throws Exception {
        String result = Differ.generate(YML_FILE1, YML_FILE2, "stylish");
        String expected = Files.readString(Path.of(EXPECTED_STYLISH_RESULT)).trim();
        assertEquals(expected, result);
    }

    @Test
    void testYmlInputPlainOutput() throws Exception {
        String result = Differ.generate(YML_FILE1, YML_FILE2, "plain");
        String expected = Files.readString(Path.of(EXPECTED_PLAIN_RESULT)).trim();
        assertEquals(expected, result);
    }

    @Test
    void testYmlInputDefaultOutput() throws Exception {
        String result = Differ.generate(YML_FILE1, YML_FILE2);
        String expected = Files.readString(Path.of(EXPECTED_STYLISH_RESULT)).trim();
        assertEquals(expected, result);
    }
}
