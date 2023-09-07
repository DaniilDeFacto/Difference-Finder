package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    private static final String REMOVED = "  - %s: %s\n";
    private static final String ADDED = "  + %s: %s\n";
    private static final String SAME = "    %s: %s\n";
    private static final String UPDATED = REMOVED + ADDED;

    public static String diffToStylish(List<Map<String, Object>> diffList) throws Exception {
        StringBuilder result = new StringBuilder(("{\n"));
        for (var element : diffList) {
            switch (element.get("STATUS").toString()) {
                case "REMOVED" -> result.append(String.format(REMOVED,
                        element.get("FIELD"),
                        element.get("OLD_VALUE")));
                case "ADDED" -> result.append(String.format(ADDED,
                        element.get("FIELD"),
                        element.get("NEW_VALUE")));
                case "SAME" -> result.append(String.format(SAME,
                        element.get("FIELD"),
                        element.get("OLD_VALUE")));
                case "UPDATED" -> result.append(String.format(UPDATED,
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
