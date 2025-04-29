package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> getData(String filePath) throws IOException {
        var mapper = new ObjectMapper();
        File file = Paths.get(filePath).toFile();
        return mapper.readValue(file, Map.class);
    }
}

