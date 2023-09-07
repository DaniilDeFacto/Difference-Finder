package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> toMap(String content, String inputFormat) throws Exception {
        switch (inputFormat) {
            case "json" -> {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(content, new TypeReference<>() { });
            }
            case "yml" -> {
                ObjectMapper mapper = new YAMLMapper();
                return mapper.readValue(content, new TypeReference<>() { });
            }
            default -> throw new Exception("Unexpected format: " + inputFormat);
        }
    }
}
