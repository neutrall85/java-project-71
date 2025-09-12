package hexlet.code;

import hexlet.code.formatters.Formatter;

import static hexlet.code.Parser.getData;

import java.util.Map;

public final class Differ {

    private Differ() { }

    public static String generate(String filePath1,
                                  String filePath2,
                                  String formatName) throws FileProcessingException {
        Map<String, Object> firstFileData = getData(filePath1);
        Map<String, Object> secondFileData = getData(filePath2);

        return Formatter.format(formatName, firstFileData, secondFileData);
    }
}
