package hexlet.code;

//import hexlet.code.formatter.Stylish;

import static hexlet.code.Parser.getData;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> firstFileData = getData(filePath1);
        Map<String, Object> secondFileData = getData(filePath2);

        Set<String> keys = new TreeSet<>(firstFileData.keySet());
        keys.addAll(secondFileData.keySet());

        StringBuilder result = new StringBuilder("{\n");

        for (String key : keys) {
            boolean inFirst = firstFileData.containsKey(key);
            boolean inSecond = secondFileData.containsKey(key);

            if (inFirst && inSecond) {
                Object firstValue = firstFileData.get(key);
                Object secondValue = secondFileData.get(key);

                if (Objects.equals(firstValue, secondValue)) {
                    result.append("    ").append(key).append(": ").append(firstValue).append("\n");
                } else {
                    result.append("  - ").append(key).append(": ").append(firstValue).append("\n");
                    result.append("  + ").append(key).append(": ").append(secondValue).append("\n");
                }
            } else if (inFirst) {
                result.append("  - ").append(key).append(": ").append(firstFileData.get(key)).append("\n");
            } else {
                result.append("  + ").append(key).append(": ").append(secondFileData.get(key)).append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}
