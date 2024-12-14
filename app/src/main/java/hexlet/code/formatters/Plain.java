package hexlet.code.formatters;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public final class Plain implements Format {
    @Override
    public String format(Map<String, Map<String, Object>> differences) {
        var result = new StringBuilder();
        differences.forEach((key, value) -> {
            var type = (String) value.get("type");
            var value1 = getValueByType(value.get("value1"));
            var value2 = getValueByType(value.get("value2"));
            var changed = "Property '%s' was updated. From %s to %s\n";
            var added = "Property '%s' was added with value: %s\n";
            var removed = "Property '%s' was removed\n";

            switch (type) {
                case "unchanged" -> result.append("");
                case "changed" -> result.append(String.format(changed, key, value1, value2));
                case "added" -> result.append(String.format(added, key, value2));
                case "removed" -> result.append(String.format(removed, key));
                default -> throw new RuntimeException("Unexpected change type: " + type);
            }
        });
        return StringUtils.removeEnd(result.toString(), "\n");
    }

    private static String getValueByType(Object value) {
        if (value == null) {
            return "null";
        }
        if (ClassUtils.isPrimitiveOrWrapper(value.getClass())) {
            return value.toString();
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return "[complex value]";
    }
}
