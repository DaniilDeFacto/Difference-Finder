package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> map1 = Parser.parse(filePath1);
        Map<String, Object> map2 = Parser.parse(filePath2);
        List<Map<String, Object>> diffMap = createDiffMap(map1, map2);
        return translateToString(diffMap);
    }

    public static List<Map<String, Object>> createDiffMap(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> diffMap = new ArrayList<>();
        TreeSet<String> keySet = Stream.of(map1, map2)
                .flatMap(m -> m.keySet().stream())
                .collect(Collectors.toCollection(TreeSet::new));
        for (var key : keySet) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                diffMap.add(Map.of("FIELD", key, "STATUS", "REMOVED", "OLD_VALUE", map1.get(key)));
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                diffMap.add(Map.of("FIELD", key, "STATUS", "ADDED", "NEW_VALUE", map2.get(key)));
            } else if (map1.containsKey(key) && map2.containsKey(key) && map1.get(key).equals(map2.get(key))) {
                diffMap.add(Map.of("FIELD", key, "STATUS", "SAME", "OLD_VALUE", map1.get(key)));
            } else {
                diffMap.add(Map.of("FIELD", key, "STATUS", "UPDATED", "OLD_VALUE", map1.get(key),
                        "NEW_VALUE", map2.get(key)));
            }
        }
        return diffMap;
    }

    public static String translateToString(List<Map<String, Object>> diffMap) {
        StringBuilder result = new StringBuilder();
        for (var element : diffMap) {
            switch (element.get("STATUS").toString()) {
                case "REMOVED" -> result.append("  - ").append(element.get("FIELD"))
                        .append(": ").append(element.get("OLD_VALUE")).append("\n");
                case "ADDED" -> result.append("  + ").append(element.get("FIELD"))
                        .append(": ").append(element.get("NEW_VALUE")).append("\n");
                case "SAME" -> result.append("    ").append(element.get("FIELD"))
                        .append(": ").append(element.get("OLD_VALUE")).append("\n");
                default -> result.append("  - ").append(element.get("FIELD"))
                        .append(": ").append(element.get("OLD_VALUE")).append("\n")
                        .append("  + ").append(element.get("FIELD"))
                        .append(": ").append(element.get("NEW_VALUE")).append("\n");
            }
        }
        return "{\n" + result + "}";
    }
}
