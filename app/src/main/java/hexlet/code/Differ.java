package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Map;

public class Differ {
//    private final String format; // todo
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Differ(String format) {
//        this.format = format;
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        Map<String, Object> data1 = parseFile(filepath1);
        Map<String, Object> data2 = parseFile(filepath2);

        return buildDiff(data1, data2);
    }

    private static Map<String, Object> parseFile(String filepath) throws IOException {
        var path = Paths.get(filepath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new IOException("File not found on the path - " + filepath);
        }

        // reading file
        var content = Files.readString(path);
        // parsing JSON in Map
        return objectMapper.readValue(content, new TypeReference<>() {});
    }

    private static String buildDiff(Map<String, Object> data1, Map<String, Object> data2) {
        var result = new StringBuilder("{\n");

        var allKeys = new ArrayList<>(data1.keySet());
        for (var key : data2.keySet()) {
            if (!allKeys.contains(key)) {
                allKeys.add(key);
            }
        }

        Collections.sort(allKeys);

        for (var key : allKeys) {
            var value1 = data1.get(key);
            var value2 = data2.get(key);

            if (data1.containsKey(key) && data2.containsKey(key)) {
                if (value1.equals(value2)) {
                    result.append("    ").append(key).append(": ").append(value1).append("\n");
                } else {
                    result.append("  - ").append(key).append(": ").append(value1).append("\n");
                    result.append("  + ").append(key).append(": ").append(value2).append("\n");
                }
            } else if (data1.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(value1).append("\n");
            } else {
                result.append("  + ").append(key).append(": ").append(value2).append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}
