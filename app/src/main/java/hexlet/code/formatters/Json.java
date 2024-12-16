package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

public final class Json implements Format {
    @Override
    public String format(Map<String, Map<String, Object>> differences) {
        var objectMapper = new ObjectMapper();
        var result = new ArrayList<>();
        differences.forEach((key, value) -> {
            var type = (String) value.get("type");
            var changedValue = new TreeMap<>();

            switch (type) {
                case "unchanged" -> {
                    changedValue.put("name", key);
                    changedValue.put("value", value.get("value1"));
                    changedValue.put("type", value.get("type"));
                }
                case "changed", "added", "removed" -> {
                    changedValue.put("name", key);
                    changedValue.put("value1", value.get("value1"));
                    changedValue.put("value2", value.get("value2"));
                    changedValue.put("type", value.get("type"));
                }
                default -> throw new RuntimeException("Unexpected change type: " + type);
            }
            result.add(changedValue);
        });

        try {
            return objectMapper.writeValueAsString(result);
        } catch (Exception e) {
            throw new RuntimeException("Error while serializing to JSON", e);
        }
    }
}
