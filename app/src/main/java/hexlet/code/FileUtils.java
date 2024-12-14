package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static hexlet.code.parsing.ParserFactory.getParser;

public class FileUtils {
    public static Map<String, Object> parseFile(String filepath) throws Exception {
        // reading file
        var fileExtension = getFileExtension(filepath);
        var content = readFileContent(filepath);
        // parsing JSON/YAML in Map
        return getParser(fileExtension).parse(content);
    }

    private static String readFileContent(String filepath) throws Exception {
        var path = Path.of(filepath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File not found: " + filepath);
        }

        return Files.readString(path);
    }

    private static String getFileExtension(String filepath) {
        var lastDotIndex = filepath.trim().toLowerCase().lastIndexOf('.');

        if (lastDotIndex == -1 || lastDotIndex == filepath.length() - 1) {
            throw new IllegalArgumentException("File extension is missing of invalid: " + filepath);
        }

        return filepath.substring(lastDotIndex);
    }
}
