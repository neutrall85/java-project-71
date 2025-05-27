package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> getData(String filePath) throws Exception {
        var mapper = new ObjectMapper();
        File file = Paths.get(filePath).toFile();
        if (filePath.endsWith("json")) {
            mapper = new ObjectMapper();
        } else if (filePath.endsWith("yml")) {
            mapper = new YAMLMapper();
        }
        return mapper.readValue(file, new TypeReference<>() { });
    }
}

