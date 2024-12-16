package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private final String pathToJson1 = "src/test/resources/file1.json";
    private final String pathToJson2 = "src/test/resources/file2.json";
    private final String pathToYaml1 = "src/test/resources/file1.yml";
    private final String pathToYaml2 = "src/test/resources/file2.yml";
    private final String pathToExpectedStylish = "src/test/resources/stylish";
    private final String pathToExpectedPlain = "src/test/resources/plain";
    private final String pathToExpectedJson = "src/test/resources/json";

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
        assertEquals(expectedStylish, Differ.generate(pathToYaml1, pathToYaml2));
        assertEquals(expectedStylish, Differ.generate(pathToJson1, pathToYaml2));
        assertEquals(expectedStylish, Differ.generate(pathToYaml1, pathToJson2));
    }

    @Test
    public void testWithStylish() throws Exception {
        var fullPath = Path.of(pathToExpectedStylish).toAbsolutePath().normalize();
        var expectedStylish = "";
        try {
            expectedStylish = Files.readString(fullPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(expectedStylish, Differ.generate(pathToJson1, pathToJson2, "stylish"));
        assertEquals(expectedStylish, Differ.generate(pathToYaml1, pathToYaml2, "stylish"));
        assertEquals(expectedStylish, Differ.generate(pathToJson1, pathToYaml2, "stylish"));
        assertEquals(expectedStylish, Differ.generate(pathToYaml1, pathToJson2, "stylish"));
    }

    @Test
    public void testWithPlain() throws Exception {
        var fullPath = Path.of(pathToExpectedPlain).toAbsolutePath().normalize();
        var expectedPlain = "";
        try {
            expectedPlain = Files.readString(fullPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(expectedPlain, Differ.generate(pathToJson1, pathToJson2, "plain"));
        assertEquals(expectedPlain, Differ.generate(pathToYaml1, pathToYaml2, "plain"));
        assertEquals(expectedPlain, Differ.generate(pathToJson1, pathToYaml2, "plain"));
        assertEquals(expectedPlain, Differ.generate(pathToYaml1, pathToJson2, "plain"));
    }

    @Test
    public void testWithJson() throws Exception {
        var fullPath = Path.of(pathToExpectedJson).toAbsolutePath().normalize();
        var expectedJson = "";
        try {
            expectedJson = Files.readString(fullPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(expectedJson, Differ.generate(pathToJson1, pathToJson2, "json"));
        assertEquals(expectedJson, Differ.generate(pathToYaml1, pathToYaml2, "json"));
        assertEquals(expectedJson, Differ.generate(pathToJson1, pathToYaml2, "json"));
        assertEquals(expectedJson, Differ.generate(pathToYaml1, pathToJson2, "json"));
    }
}
