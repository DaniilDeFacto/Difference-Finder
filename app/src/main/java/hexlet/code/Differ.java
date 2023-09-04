package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.parse(filePath1);
        Map<String, Object> map2 = Parser.parse(filePath2);
        List<Map<String, Object>> diffMap = createDiffMap(map1, map2);
        return Formatter.buildFormattedString(diffMap, format);
    }

    public static List<Map<String, Object>> createDiffMap(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> diffList = new ArrayList<>();
        TreeSet<String> keySet = Stream.of(map1, map2)
                .flatMap(m -> m.keySet().stream())
                .collect(Collectors.toCollection(TreeSet::new));
        for (var key : keySet) {
            Map<String, Object> diffMap = new HashMap<>();
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                diffMap.put("FIELD", key);
                diffMap.put("STATUS", "REMOVED");
                diffMap.put("OLD_VALUE", map1.get(key));
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                diffMap.put("FIELD", key);
                diffMap.put("STATUS", "ADDED");
                diffMap.put("NEW_VALUE", map2.get(key));
            } else if (map1.containsKey(key) && map2.containsKey(key) && Objects.equals(map1.get(key), map2.get(key))) {
                diffMap.put("FIELD", key);
                diffMap.put("STATUS", "SAME");
                diffMap.put("OLD_VALUE", map1.get(key));
            } else {
                diffMap.put("FIELD", key);
                diffMap.put("STATUS", "UPDATED");
                diffMap.put("OLD_VALUE", map1.get(key));
                diffMap.put("NEW_VALUE", map2.get(key));
            }
            diffList.add(diffMap);
        }
        return diffList;
    }
}
