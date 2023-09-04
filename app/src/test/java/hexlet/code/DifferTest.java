package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    ClassLoader classLoader = getClass().getClassLoader();
    String expected;

    @BeforeEach
    public void beforeEach() throws Exception {
        String expectedFile = Objects.requireNonNull(classLoader.getResource("expectedFile.txt")).getFile();
        Path pathExpectedFile = Paths.get(expectedFile).toAbsolutePath().normalize();
        expected = Files.readString(pathExpectedFile);
    }

    @Test
    public void jsonDifferTest() throws Exception {
        String filepath1 = Objects.requireNonNull(classLoader.getResource("testFile1.json")).getFile();
        String filepath2 = Objects.requireNonNull(classLoader.getResource("testFile2.json")).getFile();
        String actual = Differ.generate(filepath1, filepath2, "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void yamlDifferTest() throws Exception {
        String filepath1 = Objects.requireNonNull(classLoader.getResource("testFile1.yml")).getFile();
        String filepath2 = Objects.requireNonNull(classLoader.getResource("testFile2.yml")).getFile();
        String actual = Differ.generate(filepath1, filepath2, "stylish");
        assertEquals(expected, actual);
    }
}
