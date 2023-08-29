package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        if (!Files.exists(path1)) {
            throw new Exception("File '" + path1 + "' does not exist");
        } else if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' does not exist");
        }
        String json1 = Files.readString(path1);
        String json2 = Files.readString(path2);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map1 = objectMapper.readValue(json1, new TypeReference<>(){});
        Map<String, Object> map2 = objectMapper.readValue(json2, new TypeReference<>(){});
        List<Map<String, Object>> diffMap = createDiffMap(map1,map2);
        return "\n{\n" + translateToString(diffMap) + "}";
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
                diffMap.add(Map.of("FIELD", key, "STATUS", "UPDATED","OLD_VALUE", map1.get(key),
                        "NEW_VALUE", map2.get(key)));
            }
        }
        return diffMap;
    }

    public static String translateToString(List<Map<String, Object>> diffMap) {
        String result = "";
        for (var element : diffMap) {
            switch (element.get("STATUS").toString()) {
                case "REMOVED" -> result += "  - " + element.get("FIELD") + ": " + element.get("OLD_VALUE") + "\n";
                case "ADDED" -> result += "  + " + element.get("FIELD") + ": " + element.get("NEW_VALUE") + "\n";
                case "SAME" -> result += "    " + element.get("FIELD") + ": " + element.get("OLD_VALUE") + "\n";
                default -> result += "  - " + element.get("FIELD") + ": " + element.get("OLD_VALUE") + "\n"
                        + "  + " + element.get("FIELD") + ": " + element.get("NEW_VALUE") + "\n";
            }
        }
        return result;
    }
}
