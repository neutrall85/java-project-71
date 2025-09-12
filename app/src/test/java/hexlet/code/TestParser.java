package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestParser {
    @TempDir
    private Path tempDir;

    private Path jsonFile;
    private Path yamlFile;
    private Path invalidFile;
    private Map<String, Object> testData;

    @BeforeEach
    void setUp() throws IOException {
        jsonFile = tempDir.resolve("test.json");
        yamlFile = tempDir.resolve("test.yaml");
        invalidFile = tempDir.resolve("test.txt");

        testData = new HashMap<>();
        testData.put("key", "value");
        testData.put("number", 123);

        ObjectMapper objectMapper = new ObjectMapper();
        Files.writeString(jsonFile, objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(testData));

        Yaml yaml = new Yaml();
        Writer writer = new StringWriter();
        yaml.dump(testData, writer);
        Files.writeString(yamlFile, writer.toString());

        Files.createFile(invalidFile);
    }

    @Test
    void testReadJsonFile() throws FileProcessingException {
        Map<String, Object> result = Parser.getData(jsonFile.toString());
        assertEquals(testData, result);
    }

    @Test
    void testReadYamlFile() throws FileProcessingException {
        Map<String, Object> result = Parser.getData(yamlFile.toString());
        assertEquals(testData, result);
    }

    @Test
    void testInvalidFileExtension() {
        Exception exception = assertThrows(FileProcessingException.class, this::processInvalidFile);
        assertTrue(exception.getMessage().contains("Неподдерживаемый формат файла"));
    }

    private void processInvalidFile() {
        Parser.getData(invalidFile.toString());
    }

    @Test
    void testNonExistentFile() {
        Exception exception = assertThrows(FileProcessingException.class, () ->
            Parser.getData("non_existent_file.json")
        );
        assertTrue(exception.getMessage().contains("Файл не существует"));
    }

    @Test
    void testRelativePath() throws FileProcessingException {
        Path relativePath = tempDir.resolve("test.json");
        Map<String, Object> result = Parser.getData(relativePath.toString());
        assertEquals(testData, result);
    }

    @Test
    void testPathToFullPath() {
        Path result = Parser.pathToFullPath(jsonFile.toString());
        assertTrue(Files.exists(result));
    }
}
