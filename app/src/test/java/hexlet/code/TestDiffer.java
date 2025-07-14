package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestDiffer {

    private static final String PATH_FIRST_JSON = "./src/test/resources/file1.json";
    private static final String PATH_SECOND_JSON = "./src/test/resources/file2.json";


    private static final String EXPECTED = """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";

    @Test
    public void testDifferGenerateJSON() throws Exception {
        String actual = Differ.generate(PATH_FIRST_JSON, PATH_SECOND_JSON);
        assertThat(actual).isEqualTo(EXPECTED);
    }
}
