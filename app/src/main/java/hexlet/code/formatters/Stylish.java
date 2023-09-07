package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    static String removed = "  - %s: %s\n";
    static String added = "  + %s: %s\n";
    static String same = "    %s: %s\n";
    static String updated = removed + added;
    public static String diffToStylish(List<Map<String, Object>> diffMap) throws Exception {
        StringBuilder result = new StringBuilder(("{\n"));
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
