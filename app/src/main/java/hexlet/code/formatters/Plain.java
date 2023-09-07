package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    static String removed = "Property '%s' was removed\n";
    static String added = "Property '%s' was added with value: %s\n";
    static String updated = "Property '%s' was updated. From %s to %s\n";

    public static String diffToPlain(List<Map<String, Object>> diffMap) throws Exception {
        StringBuilder result = new StringBuilder();
        for (var element : diffMap) {
            switch (element.get("STATUS").toString()) {
                case "REMOVED" -> result.append(String.format(removed,
                        element.get("FIELD")));
                case "ADDED" -> result.append(String.format(added,
                        element.get("FIELD"),
                        processingComplexValue(element.get("NEW_VALUE"))));
                case "SAME" -> { }
                case "UPDATED" -> result.append(String.format(updated,
                        element.get("FIELD"),
                        processingComplexValue(element.get("OLD_VALUE")),
                        processingComplexValue(element.get("NEW_VALUE"))));
                default -> throw new Exception("Unexpected status: " + element.get("STATUS"));
            }
        }
        return result.substring(0, result.length() - 1);
    }

    public static String processingComplexValue(Object object) {
        boolean isComplexValue = object instanceof Map<?, ?> || object instanceof List<?>;
        boolean isStringObject = object instanceof String;
        if (isComplexValue) {
            return "[complex value]";
        } else if (isStringObject) {
            return String.format("'%s'", object);
        } else {
            return String.valueOf(object);
        }
    }
}
