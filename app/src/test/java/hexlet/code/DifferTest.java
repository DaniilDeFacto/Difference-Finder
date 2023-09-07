package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
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
    static void beforeAll() throws Exception {
        File expectedStylishFile = new File(CLASS_LOADER.getResource("expectedStylishFile.txt").getFile());
        Path pathExpectedStylishFile = Paths.get(expectedStylishFile.getAbsolutePath()).normalize();
        expectedStylish = Files.readString(pathExpectedStylishFile);

        File expectedPlainFile = new File(CLASS_LOADER.getResource("expectedPlainFile.txt").getFile());
        Path pathExpectedPlainFile = Paths.get(expectedPlainFile.getAbsolutePath()).normalize();
        expectedPlain = Files.readString(pathExpectedPlainFile);

        File expectedJsonFile = new File(CLASS_LOADER.getResource("expectedJsonFile.txt").getFile());
        Path pathExpectedJsonFile = Paths.get(expectedJsonFile.getAbsolutePath()).normalize();
        expectedJson = Files.readString(pathExpectedJsonFile);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void differStylishTest(String inputFormat) throws Exception {
        String filepath1 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile1" + inputFormat)).getFile();
        String filepath2 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile2" + inputFormat)).getFile();
        String actual = Differ.generate(filepath1, filepath2, "stylish");
        assertEquals(expectedStylish, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void differPlainTest(String inputFormat) throws Exception {
        String filepath1 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile1" + inputFormat)).getFile();
        String filepath2 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile2" + inputFormat)).getFile();
        String actual = Differ.generate(filepath1, filepath2, "plain");
        assertEquals(expectedPlain, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void differJsonTest(String inputFormat) throws Exception {
        String filepath1 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile1" + inputFormat)).getFile();
        String filepath2 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile2" + inputFormat)).getFile();
        String actual = Differ.generate(filepath1, filepath2, "json");
        assertEquals(expectedJson, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void differDefaultTest(String inputFormat) throws Exception {
        String filepath1 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile1" + inputFormat)).getFile();
        String filepath2 = Objects.requireNonNull(CLASS_LOADER.getResource("testFile2" + inputFormat)).getFile();
        String actual = Differ.generate(filepath1, filepath2);
        assertEquals(expectedStylish, actual);
    }
}
