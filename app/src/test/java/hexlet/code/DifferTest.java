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
    String expectedStylish;
    String expectedPlain;

    @BeforeEach
    public void beforeEach() throws Exception {
        String expectedStylishFile = Objects.requireNonNull(classLoader
                .getResource("expectedStylishFile.txt"))
                .getFile();
        Path pathExpectedStylishFile = Paths.get(expectedStylishFile).toAbsolutePath().normalize();
        expectedStylish = Files.readString(pathExpectedStylishFile);
        String expectedPlainFile = Objects.requireNonNull(classLoader
                .getResource("expectedPlainFile.txt"))
                .getFile();
        Path pathExpectedPlainFile = Paths.get(expectedPlainFile).toAbsolutePath().normalize();
        expectedPlain = Files.readString(pathExpectedPlainFile);
    }

    @Test
    public void jsonToStylishDifferTest() throws Exception {
        String filepath1 = Objects.requireNonNull(classLoader.getResource("testFile1.json")).getFile();
        String filepath2 = Objects.requireNonNull(classLoader.getResource("testFile2.json")).getFile();
        String actual = Differ.generate(filepath1, filepath2, "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void yamlToStylishDifferTest() throws Exception {
        String filepath1 = Objects.requireNonNull(classLoader.getResource("testFile1.yml")).getFile();
        String filepath2 = Objects.requireNonNull(classLoader.getResource("testFile2.yml")).getFile();
        String actual = Differ.generate(filepath1, filepath2, "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void jsonToPlainDifferTest() throws Exception {
        String filepath1 = Objects.requireNonNull(classLoader.getResource("testFile1.json")).getFile();
        String filepath2 = Objects.requireNonNull(classLoader.getResource("testFile2.json")).getFile();
        String actual = Differ.generate(filepath1, filepath2, "plain");
        assertEquals(expectedPlain, actual);
    }

    @Test
    public void yamlToPlainDifferTest() throws Exception {
        String filepath1 = Objects.requireNonNull(classLoader.getResource("testFile1.yml")).getFile();
        String filepath2 = Objects.requireNonNull(classLoader.getResource("testFile2.yml")).getFile();
        String actual = Differ.generate(filepath1, filepath2, "plain");
        assertEquals(expectedPlain, actual);
    }
}
