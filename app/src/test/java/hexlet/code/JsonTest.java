package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    @Test
    public void jsonTest() throws Exception {
        String filepath1 = "./src/test/resources/testFile1.json";
        String filepath2 = "./src/test/resources/testFile2.json";
        Path pathExpectedFile = Paths.get("./src/test/resources/expectedFile.txt").toAbsolutePath().normalize();
        String actual = Differ.generate(filepath1, filepath2);
        String expected = Files.readString(pathExpectedFile);
        assertEquals(expected, actual);
    }
}
