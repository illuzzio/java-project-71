package hexlet.code.formatters;

import java.util.Map;

public final class Stylish implements Format {
    @Override
    public String format(Map<String, Map<String, Object>> differences) {
        var result = new StringBuilder("{\n");
        differences.forEach((key, value) -> {
            var type = (String) value.get("type");
            var value1 = value.get("value1");
            var value2 = value.get("value2");

            switch (type) {
                case "unchanged" -> result.append("    ").append(key).append(": ").append(toString(value1));
                case "added" -> result.append("  + ").append(key).append(": ").append(toString(value2));
                case "removed" -> result.append("  - ").append(key).append(": ").append(toString(value1));
                case "changed" -> {
                    result.append("  - ").append(key).append(": ").append(toString(value1));
                    result.append("\n");
                    result.append("  + ").append(key).append(": ").append(toString(value2));
                }
                default -> throw new RuntimeException("Unexpected change type: " + type);
            }
            result.append("\n");
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
