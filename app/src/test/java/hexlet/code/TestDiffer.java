package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestDiffer {


    private static final String EXPECTED = "{\n"
            + "  - follow: false\n"
            + "    host: hexlet.io\n"
            + "  - proxy: 123.234.53.22\n"
            + "  - timeout: 50\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            + "}";

    @Test
    public void testDifferGenerateJSON() throws Exception {
        String pathFirstFile = "file1.json";
        String pathSecondFile = "file2.json";
        String actual = Differ.generate(pathFirstFile, pathSecondFile);
        assertThat(actual).isEqualTo(EXPECTED);
    }
    @Test
    public void testDifferGenerateYAML() throws Exception {
        String pathFirstFile = "file1.yml";
        String pathSecondFile = "file2.yml";
        String actual = Differ.generate(pathFirstFile, pathSecondFile);
        assertThat(actual).isEqualTo(EXPECTED);
    }
}
