package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Differ {

    private Differ() { }

    public static String generate(String path1, String path2, String formatName) throws IOException {
        Objects.requireNonNull(path1, "Путь к первому файлу не может быть null");
        Objects.requireNonNull(path2, "Путь ко второму файлу не может быть null");
        Objects.requireNonNull(formatName, "Формат вывода не может быть null");

        String content1 = readContent(path1);
        String content2 = readContent(path2);
        String format = detectFormat(path1);

        Map<String, Object> data1 = Parser.parse(content1, format);
        Map<String, Object> data2 = Parser.parse(content2, format);
        List<Map<String, Object>> diff = DifferenceCalc.calculateDifferences(data1, data2);
        return Formatter.format(formatName, diff);
    }

    public static String generate(String path1, String path2) throws IOException {
        return generate(path1, path2, "stylish");
    }

    private static String readContent(String path) throws IOException {
        Path fullPath = getFullPath(path);
        return Files.readString(fullPath);
    }

    private static String detectFormat(String path) {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Путь к файлу не может быть пустым");
        }

        int dotIndex = path.lastIndexOf('.');

        if (dotIndex <= 0 || dotIndex == path.length() - 1) {
            throw new IllegalArgumentException("Некорректное имя файла: " + path);
        }

        return path.substring(dotIndex + 1).toLowerCase();
    }

    private static Path getFullPath(String path) {
        Path basePath = Paths.get(path);
        if (!basePath.isAbsolute()) {
            return Paths.get("src/test/resources", path).toAbsolutePath();
        }
        return basePath.toAbsolutePath();
    }
}
