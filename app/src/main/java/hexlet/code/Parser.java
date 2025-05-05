package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> getData(String filePath) throws Exception {
        var mapper = new ObjectMapper();
        File file = Paths.get(filePath).toFile();
        return mapper.readValue(file, new TypeReference<>() { });
    }
}

