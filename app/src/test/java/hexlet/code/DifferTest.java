package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DifferTest {
    private static final ClassLoader CLASS_LOADER = DifferTest.class.getClassLoader();
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

    @BeforeAll
    static void beforeAll() throws IOException {
        expectedStylish = readFile("expectedStylishFile.txt");
        expectedPlain = readFile("expectedPlainFile.txt");
        expectedJson = readFile("expectedJsonFile.txt");
    }

    static String readFile(String nameFile) throws IOException {
        File expectedFile = new File(CLASS_LOADER.getResource(nameFile).getFile());
        Path pathExpectedFile = Paths.get(expectedFile.getAbsolutePath()).normalize();
        return Files.readString(pathExpectedFile);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void differStylishTest(String inputFormat) throws IOException {
        String filepath1 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile1" + inputFormat)).getFile();
        String filepath2 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile2" + inputFormat)).getFile();
        String actual = Differ.generate(filepath1, filepath2, "stylish");
        assertEquals(expectedStylish, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void differPlainTest(String inputFormat) throws IOException {
        String filepath1 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile1" + inputFormat)).getFile();
        String filepath2 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile2" + inputFormat)).getFile();
        String actual = Differ.generate(filepath1, filepath2, "plain");
        assertEquals(expectedPlain, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void differJsonTest(String inputFormat) throws IOException {
        String filepath1 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile1" + inputFormat)).getFile();
        String filepath2 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile2" + inputFormat)).getFile();
        String actual = Differ.generate(filepath1, filepath2, "json");
        assertEquals(expectedJson, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void differDefaultTest(String inputFormat) throws IOException {
        String filepath1 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile1" + inputFormat)).getFile();
        String filepath2 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile2" + inputFormat)).getFile();
        String actual = Differ.generate(filepath1, filepath2);
        assertEquals(expectedStylish, actual);
    }
}
