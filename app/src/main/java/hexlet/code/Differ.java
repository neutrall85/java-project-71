package hexlet.code;

import hexlet.code.formatters.Formatter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public final class Differ {

    private Differ() { }

    public static String generate(String path1, String path2, String formatName) throws IOException {
        String content1 = readContent(path1);
        String content2 = readContent(path2);

        String format = detectFormat(path1);
        try {
            Map<String, Object> data1 = Parser.parse(content1, format);
            Map<String, Object> data2 = Parser.parse(content2, format);
            return Formatter.format(formatName, data1, data2);
        } catch (IllegalArgumentException e) {
            throw new IOException("Ошибка при парсинге данных: " + e.getMessage(), e);
        }
    }

    public static String generate(String path1, String path2) throws IOException {
        return generate(path1, path2, "stylish");
    }

    private static String readContent(String path) throws IOException {
        Path fullPath = getFullPath(path);

        try {
            return Files.readString(fullPath);
        } catch (Exception e) {
            throw new IOException("Ошибка при чтении данных: " + path, e);
        }
    }

    private static String detectFormat(String path) throws IOException {
        if (path.endsWith(".json")) {
            return "json";
        } else if (path.endsWith(".yml") || path.endsWith(".yaml")) {
            return "yaml";
        }
        throw new IOException("Неподдерживаемый формат данных: " + path);
    }

    private static Path getFullPath(String path) throws IOException {
        try {
            Path basePath = Paths.get(path);
            if (!basePath.isAbsolute()) {
                return Paths.get("src/test/resources", path).toAbsolutePath();
            }
            return basePath.toAbsolutePath();
        } catch (Exception e) {
            throw new IOException("Ошибка при обработке пути: " + path, e);
        }
    }
}
