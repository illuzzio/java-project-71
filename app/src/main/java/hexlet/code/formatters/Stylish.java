package hexlet.code.formatters;

import java.util.Map;

public class Stylish implements Format {
    @Override
    public String format(Map<String, Map<String, Object>> differences) {
        var result = new StringBuilder("{\n");
        differences.forEach((key, value) -> {
            String type = (String) value.get("type");
            Object value1 = value.get("value1");
            Object value2 = value.get("value2");

            switch (type) {
                case "unchanged" -> result.append("    ").append(key).append(": ").append(toString(value1)).append("\n");
                case "added" -> result.append("  + ").append(key).append(": ").append(toString(value2)).append("\n");
                case "removed" -> result.append("  - ").append(key).append(": ").append(toString(value1)).append("\n");
                case "changed" -> {
                    result.append("  - ").append(key).append(": ").append(toString(value1)).append("\n");
                    result.append("  + ").append(key).append(": ").append(toString(value2)).append("\n");
                }
                default -> throw new RuntimeException("Unexpected change type: " + type);
            }
        });
        result.append("}");
        return result.toString();
    }

    private String toString(Object value) {
        if (value == null) {
            return "null";
        }
        return value.toString();
    }
}
