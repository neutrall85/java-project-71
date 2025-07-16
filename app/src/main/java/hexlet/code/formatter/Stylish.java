package hexlet.code.formatter;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Stylish {
    public static String createStylish(Map<String, Object> firstFileData,
                                       Map<String, Object> secondFileData,
                                       int count) {

        Set<String> keys = new TreeSet<>(firstFileData.keySet());
        keys.addAll(secondFileData.keySet());
        String space = " ";
        StringBuilder result = new StringBuilder(space.repeat(count) + "{\n");

        for (String key : keys) {
            if (firstFileData.isEmpty()) {
                result.append(space.repeat(count)).append("  + ").append(key).append(": ").append(secondFileData.get(key))
                        .append("\n");
            } else if (secondFileData.isEmpty()) {
                result.append(space.repeat(count)).append("  - ").append(key).append(": ").append(firstFileData.get(key))
                        .append("\n");
            } else if (firstFileData.containsKey(key) && !secondFileData.containsKey(key)) {
                result.append(space.repeat(count)).append("  - ").append(key).append(": ").append(firstFileData.get(key))
                        .append("\n");
            } else if (!firstFileData.containsKey(key) && secondFileData.containsKey(key)) {
                result.append(space.repeat(count)).append("  + ").append(key).append(": ").append(secondFileData.get(key))
                        .append("\n");
            } else {
                result.append(stylishEasy(count, key, firstFileData, secondFileData));
            }
        }
        result.append(space.repeat(count)).append("}");
        return result.toString();
    }

    public static String stylishEasy(int count,
                                     String key,
                                     Map<String, Object> firstFileData,
                                     Map<String, Object> secondFileData) {
        String space = " ";
        Object objOf1 = firstFileData.get(key);
        Object objOf2 = secondFileData.get(key);
        final StringBuilder result = new StringBuilder();
        if (objOf1 == null || objOf2 == null ? objOf1 == objOf2 : firstFileData.get(key).equals(secondFileData.get(key))) {
            result.append(space.repeat(count)).append("    ").append(key).append(": ").append(firstFileData.get(key))
                    .append("\n");
        } else {
            result.append(space.repeat(count)).append("  - ").append(key).append(": ").append(firstFileData.get(key))
                    .append("\n");
            result.append(space.repeat(count)).append("  + ").append(key).append(": ").append(secondFileData.get(key))
                    .append("\n");
        }

        return result.toString();
    }
}
