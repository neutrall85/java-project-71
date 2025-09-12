package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static java.nio.file.Files.readString;

public final class Parser {

    private Parser() { }

    public static Map<String, Object> getData(String filePath) throws FileProcessingException {
        Path fullPath = pathToFullPath(filePath);

        if (!Files.exists(fullPath)) {
            throw new FileProcessingException("Файл не существует: " + fullPath);
        }

        try {
            return readFile(fullPath);
        } catch (Exception e) {
            throw new FileProcessingException("Ошибка обработки файла: " + filePath, e);
        }
    }

    private static Map<String, Object> readFile(Path path) throws FileProcessingException {
        ObjectMapper mapper = getMapper(path);

        try {
            return mapper.readValue(readString(path), new TypeReference<>() { });
        } catch (Exception e) {
            throw new FileProcessingException("Ошибка чтения файла: " + path, e);
        }
    }

    private static ObjectMapper getMapper(Path path) {
        String filePath = path.toString();

        if (filePath.endsWith(".json")) {
            return new ObjectMapper();
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            return new YAMLMapper();
        }

        throw new FileProcessingException("Неподдерживаемый формат файла: " + path);
    }

    public static Path pathToFullPath(String filePath) throws FileProcessingException {
        Path absolutePath = Paths.get(filePath).toAbsolutePath();

        if (!absolutePath.isAbsolute()) {
            try {
                Path currentDir = Paths.get("").toAbsolutePath();
                return currentDir.resolve(filePath);
            } catch (Exception e) {
                throw new FileProcessingException("Ошибка при обработке пути: " + filePath, e);
            }
        }

        return absolutePath;
    }
}
