package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestDiffer {

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
    public void testDifferJSON() throws Exception {
        String actual = Differ.generate("file1.json", "file2.json", "stylish");
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void testDifferYML() throws Exception {
        String actual = Differ.generate("file1.yml", "file2.yml", "stylish");
        assertThat(actual).isEqualTo(EXPECTED);
    }

//    @Test
//    public void testDifferYAML() throws Exception {
//        String actual = Differ.generate("file1.yaml", "file2.yaml");
//        assertThat(actual).isEqualTo(EXPECTED);
//    }

    @Test
    public void testDifferHardYML() throws Exception {
        var expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        String actual = Differ.generate("file_hard1.yml", "file_hard2.yml", "stylish");
        assertThat(actual).isEqualTo(expected);
    }
}
