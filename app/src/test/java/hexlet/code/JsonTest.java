package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    ClassLoader classLoader = getClass().getClassLoader();
    String expectedFile = Objects.requireNonNull(classLoader.getResource("expectedFile.txt")).getFile();
    String filepath1 = Objects.requireNonNull(classLoader.getResource("testFile1.json")).getFile();
    String filepath2 = Objects.requireNonNull(classLoader.getResource("testFile2.json")).getFile();

    @Test
    public void jsonTest() throws Exception {
        Path pathExpectedFile = Paths.get(expectedFile).toAbsolutePath().normalize();
        String expected = Files.readString(pathExpectedFile);
        String actual = Differ.generate(filepath1, filepath2);
        assertEquals(expected, actual);
    }
}
