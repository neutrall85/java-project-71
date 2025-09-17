package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestApp {
    private App app;
    private static final String DEFAULT_FORMAT = "stylish";
    private static final String FILE1_PATH = "file1.json";
    private static final String FILE2_PATH = "file2.json";

    @BeforeEach
    void setUp() {
        app = new App();
    }

    @Test
    void testSuccessfulExecutionWithExistingFiles() {
        app.setFilepath1(FILE1_PATH);
        app.setFilepath2(FILE2_PATH);
        app.setFormat(DEFAULT_FORMAT);

        assertEquals(App.SUCCESS_EXIT_CODE, app.call());
    }

    @Test
    void testDifferentFormats() {
        app.setFilepath1(FILE1_PATH);
        app.setFilepath2(FILE2_PATH);

        app.setFormat("stylish");
        assertEquals(App.SUCCESS_EXIT_CODE, app.call());

        app.setFormat("plain");
        assertEquals(App.SUCCESS_EXIT_CODE, app.call());

        app.setFormat("json");
        assertEquals(App.SUCCESS_EXIT_CODE, app.call());
    }

    @Test
    void testMissingFirstFile() {
        app.setFilepath2(FILE2_PATH);
        assertEquals(App.ERROR_EXIT_CODE, app.call());
    }

    @Test
    void testMissingSecondFile() {
        app.setFilepath1(FILE1_PATH);
        assertEquals(App.ERROR_EXIT_CODE, app.call());
    }

    @Test
    void testInvalidFilePath() {
        app.setFilepath1("invalid/path/file.json");
        app.setFilepath2(FILE2_PATH);
        assertEquals(App.ERROR_EXIT_CODE, app.call());
    }

    @Test
    void testEmptyFilePath() {
        app.setFilepath1("");
        app.setFilepath2(FILE2_PATH);
        assertEquals(App.ERROR_EXIT_CODE, app.call());
    }

    @Test
    void testNullFilePath() {
        app.setFilepath1(null);
        app.setFilepath2(FILE2_PATH);
        assertEquals(App.ERROR_EXIT_CODE, app.call());
    }
}
