package hexlet.code;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String buildFormattedString(List<Map<String, Object>> diffMap, String format) throws Exception {
        return switch (format) {
            case "stylish" -> diffToStylish(diffMap);
            default -> throw new Exception("Unexpected format: " + format);
        };
    }
    public static String diffToStylish(List<Map<String, Object>> diffMap) throws Exception {
        StringBuilder result = new StringBuilder(("{\n"));
        String removed = "  - %s: %s\n";
        String added = "  + %s: %s\n";
        String same = "    %s: %s\n";
        String updated = removed + added;
        for (var element : diffMap) {
            switch (element.get("STATUS").toString()) {
                case "REMOVED" -> result.append(String.format(removed,
                        element.get("FIELD"),
                        element.get("OLD_VALUE")));
                case "ADDED" -> result.append(String.format(added,
                        element.get("FIELD"),
                        element.get("NEW_VALUE")));
                case "SAME" -> result.append(String.format(same,
                        element.get("FIELD"),
                        element.get("OLD_VALUE")));
                case "UPDATED" -> result.append(String.format(updated,
                        element.get("FIELD"),
                        element.get("OLD_VALUE"),
                        element.get("FIELD"),
                        element.get("NEW_VALUE")));
                default -> throw new Exception("Unexpected status: " + element.get("STATUS"));
            }
        }
        result.append("}");
        return result.toString();
    }
}
