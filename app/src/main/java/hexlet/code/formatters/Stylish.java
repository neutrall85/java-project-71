package hexlet.code.formatters;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class Stylish {

    private Stylish() { }

    public static String createStylish(Map<String, Object> firstFileData,
                                       Map<String, Object> secondFileData,
                                       int count) {
        Set<String> keys = new TreeSet<>(firstFileData.keySet());
        keys.addAll(secondFileData.keySet());
        String space = " ";
        StringBuilder result = new StringBuilder(space.repeat(count) + "{\n");

        for (String key : keys) {
            result.append(processKey(count, key, firstFileData, secondFileData));
        }

        result.append(space.repeat(count)).append("}");
        return result.toString();
    }

    public static String processKey(int count, String key,
                                     Map<String, Object> firstFileData,
                                     Map<String, Object> secondFileData) {

        if (!firstFileData.containsKey(key)) {
            return appendLine(count, "  + ", key, secondFileData.get(key));
        }

        if (!secondFileData.containsKey(key)) {
            return appendLine(count, "  - ", key, firstFileData.get(key));
        }

        return stylishEasy(count, key, firstFileData, secondFileData);
    }

    public static String stylishEasy(int count, String key,
                                      Map<String, Object> firstFileData,
                                      Map<String, Object> secondFileData) {
        Object objOf1 = firstFileData.get(key);
        Object objOf2 = secondFileData.get(key);

        if (areEqual(objOf1, objOf2)) {
            return appendLine(count, "    ", key, objOf1);
        } else {
            return appendLine(count, "  - ", key, objOf1)
                    + appendLine(count, "  + ", key, objOf2);
        }
    }

    public static boolean areEqual(Object obj1, Object obj2) {
        return (obj1 == null || obj2 == null) ? obj1 == obj2 : obj1.equals(obj2);
    }

    public static String appendLine(int count, String prefix, String key, Object value) {
        String space = " ";
        return space.repeat(count) + prefix + key + ": " + value + "\n";
    }
}
