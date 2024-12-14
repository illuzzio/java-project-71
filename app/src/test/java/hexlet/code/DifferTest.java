package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private final String pathToJson1 = "src/test/java/resources/file1.json";
    private final String pathToJson2 = "src/test/java/resources/file2.json";
    private final String pathToYml1 = "src/test/java/resources/file1.yml";
    private final String pathToYml2 = "src/test/java/resources/file2.yml";
    private final String pathToExpectedStylish = "src/test/java/resources/stylish";

    @Test
    public void testWithoutFormatter() throws Exception {
        var fullPath = Path.of(pathToExpectedStylish).toAbsolutePath().normalize();
        var expectedStylish = "";
        try {
            expectedStylish = Files.readString(fullPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(expectedStylish, Differ.generate(pathToJson1, pathToJson2));
    }

    @Test
    public void testJsonToStylish() throws Exception {
        var fullPath = Path.of(pathToExpectedStylish).toAbsolutePath().normalize();
        var expectedStylish = "";
        try {
            expectedStylish = Files.readString(fullPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(expectedStylish, Differ.generate(pathToJson1, pathToJson2, "stylish"));
    }

    @Test
    public void testYmlToStylish() throws Exception {
        var fullPath = Path.of(pathToExpectedStylish).toAbsolutePath().normalize();
        var expectedStylish = "";
        try {
            expectedStylish = Files.readString(fullPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(expectedStylish, Differ.generate(pathToYml1, pathToYml2, "stylish"));
    }
}
