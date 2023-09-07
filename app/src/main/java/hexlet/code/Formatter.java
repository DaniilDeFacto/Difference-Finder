package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String toString(List<Map<String, Object>> diffList, String format) throws JsonProcessingException {
        return switch (format) {
            case "stylish" -> Stylish.diffToStylish(diffList);
            case "plain" -> Plain.diffToPlain(diffList);
            case "json" -> Json.diffToJson(diffList);
            default -> throw new RuntimeException("Unexpected format: " + format);
        };
    }
}
