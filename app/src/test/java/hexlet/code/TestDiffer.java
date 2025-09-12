package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Files;
import java.nio.file.Paths;

class TestDiffer {
    private String tempDir;

    @BeforeEach
    void setUp() throws Exception {
        tempDir = Files.createTempDirectory("differ-test").toString();
    }

    @Test
    void testGenerateSuccess() throws Exception {
        String filePath1 = createTestFile("file1.json", "{\"key\": \"value\"}");
        String filePath2 = createTestFile("file2.json", "{\"key\": \"new_value\"}");

        String result = Differ.generate(filePath1, filePath2, "plain");

        assertNotNull(result);
        assertTrue(result.contains("key"));
    }

    @Test
    void testGenerateEmptyFiles() throws Exception {
        String filePath1 = createTestFile("empty1.json", "{}");
        String filePath2 = createTestFile("empty2.json", "{}");

        String result = Differ.generate(filePath1, filePath2, "plain");

        assertEquals("", result.trim());
    }

    @Test
    void testGenerateDifferentFormats() throws Exception {
        String filePath1 = createTestFile("file1.json", "{\"key\": \"value\"}");
        String filePath2 = createTestFile("file2.json", "{\"key\": \"new_value\"}");

        String[] formats = {"plain", "stylish"};

        for (String format : formats) {
            String result = Differ.generate(filePath1, filePath2, format);
            assertNotNull(result);
        }
    }

    @Test
    void testGenerateInvalidFormat() throws Exception {
        String filePath1 = createTestFile("file1.json", "{\"key\": \"value\"}");
        String filePath2 = createTestFile("file2.json", "{\"key\": \"new_value\"}");

        assertThrows(IllegalArgumentException.class,
                () -> Differ.generate(filePath1, filePath2, "invalid_format")
        );
    }

    @Test
    void testGenerateFileNotFound() {
        assertThrows(FileProcessingException.class,
                () -> Differ.generate("non_existent_file.json", "another_non_existent_file.json", "plain")
        );
    }

    private String createTestFile(String fileName, String content) throws Exception {
        String filePath = tempDir + "/" + fileName;
        Files.writeString(Paths.get(filePath), content);
        return filePath;
    }
}
