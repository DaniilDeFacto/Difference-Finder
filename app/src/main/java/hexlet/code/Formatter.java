package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String buildFormattedString(List<Map<String, Object>> diffMap, String format) throws Exception {
        return switch (format) {
            case "stylish" -> Stylish.diffToStylish(diffMap);
            case "plain" -> Plain.diffToPlain(diffMap);
            case "json" -> Json.diffToJson(diffMap);
            default -> throw new Exception("Unexpected format: " + format);
        };
    }
}
