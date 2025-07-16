package hexlet.code;

import hexlet.code.formatter.Stylish;

import static hexlet.code.Parser.getData;

import java.util.Map;

public class Differ {

    public static String generate(String filePath1,
                                  String filePath2,
                                  String format) throws Exception {
        Map<String, Object> firstFileData = getData(filePath1);
        Map<String, Object> secondFileData = getData(filePath2);
        int count = 0;

        return switch(format) {
            case "stylish" -> Stylish.createStylish(firstFileData, secondFileData, count);
            default -> format + " is not correct! Try again";
        };

    }


}
