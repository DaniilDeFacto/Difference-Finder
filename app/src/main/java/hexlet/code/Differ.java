package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String content1 = readFile(filePath1);
        String content2 = readFile(filePath2);
        String inputFormat1 = defineFormat(filePath1);
        String inputFormat2 = defineFormat(filePath2);
        Map<String, Object> map1 = Parser.toMap(content1, inputFormat1);
        Map<String, Object> map2 = Parser.toMap(content2, inputFormat2);
        List<Map<String, Object>> diffMap = DiffList.build(map1, map2);
        return Formatter.toString(diffMap, format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return Files.readString(path);
    }

    public static String defineFormat(String filePath) throws Exception {
        if (filePath.endsWith("json")) {
            return "json";
        } else if (filePath.endsWith("yml")) {
            return "yml";
        } else {
            throw new Exception("File '" + filePath + "' is in an unknown format");
        }
    }
}
