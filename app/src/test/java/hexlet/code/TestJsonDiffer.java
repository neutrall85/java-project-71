package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestJsonDiffer {

    private static final String PATH_FIRST_FILE = "file1.json";
    private static final String PATH_SECOND_FILE = "file2.json";

    @Test
    public void testDifferGenerateJSON() throws Exception {
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        String actual = Differ.generate(PATH_FIRST_FILE, PATH_SECOND_FILE);
        System.out.println("Actual output:\n" + actual);

        assertThat(actual).isEqualTo(expected);
    }
}
