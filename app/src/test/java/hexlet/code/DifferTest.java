package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

public class DifferTest {
    private Path createTempJsonFile(String content) throws IOException {
        var newFile = Files.createTempFile("testfile", ".json");
        Files.writeString(newFile, content);
        newFile.toFile().deleteOnExit();
        return newFile;
    }

    @Test
    public void testIdenticalFiles() throws IOException {
        String content = "{ \"host\": \"hexlet.io\", \"timeout\": 50 }";
        Path file1 = createTempJsonFile(content);
        Path file2 = createTempJsonFile(content);

        String result = Differ.generate(file1.toString(), file2.toString()); // todo add "stylish"
        String expected = "{\n    host: hexlet.io\n    timeout: 50\n}";
        assertEquals(expected, result);
    }

    @Test
    void testFileWithAddedKey() throws IOException {
        String content1 = "{ \"host\": \"hexlet.io\" }";
        String content2 = "{ \"host\": \"hexlet.io\", \"timeout\": 50 }";
        Path file1 = createTempJsonFile(content1);
        Path file2 = createTempJsonFile(content2);

        String result = Differ.generate(file1.toString(), file2.toString()); // todo add "stylish"
        String expected = "{\n    host: hexlet.io\n  + timeout: 50\n}";
        assertEquals(expected, result);
    }

    @Test
    void testFileWithRemovedKey() throws IOException {
        String content1 = "{ \"host\": \"hexlet.io\", \"timeout\": 50 }";
        String content2 = "{ \"host\": \"hexlet.io\" }";
        Path file1 = createTempJsonFile(content1);
        Path file2 = createTempJsonFile(content2);

        String result = Differ.generate(file1.toString(), file2.toString()); // todo add "stylish"
        String expected = "{\n    host: hexlet.io\n  - timeout: 50\n}";
        assertEquals(expected, result);
    }

    @Test
    void testFileWithModifiedValue() throws IOException {
        String content1 = "{ \"host\": \"hexlet.io\", \"timeout\": 50 }";
        String content2 = "{ \"host\": \"hexlet.io\", \"timeout\": 20 }";
        Path file1 = createTempJsonFile(content1);
        Path file2 = createTempJsonFile(content2);

        String result = Differ.generate(file1.toString(), file2.toString()); // todo add "stylish"
        String expected = "{\n    host: hexlet.io\n  - timeout: 50\n  + timeout: 20\n}";
        assertEquals(expected, result);
    }


}
