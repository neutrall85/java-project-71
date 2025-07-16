package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

import static java.nio.file.Files.readString;

public class Parser {
    public static Map<String, Object> getData(String filePath) throws Exception {
        Path fullPath = pathToFullPath(filePath);
        Map<String, Object> file = null;
        if (filePath.endsWith(".json")) {
            file = jsonFileToMap(fullPath);
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            file = yamlFileToMap(fullPath);
        }
        return file;
    }

    public static Map<String, Object> jsonFileToMap(Path path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(readString(path), new TypeReference<>() { });
    }
    public static Map<String, Object> yamlFileToMap(Path path) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(readString(path), new TypeReference<>() { });
    }
    public static Path pathToFullPath(String path) {
        String path1 = "src/test/resources";
        File file = new File(path1);
        String absolutePath = file.getAbsolutePath();
        Path resultPath = Path.of(path);
        if (!path.startsWith("/")) {
            resultPath = Path.of(absolutePath + "/" + path);
        }
        if (new File(resultPath.toString()).exists()) {
            return resultPath;
        }
        throw new RuntimeException("File: " + resultPath + " doesn't exist");
    }
}

