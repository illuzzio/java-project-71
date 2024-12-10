package hexlet.code.formatters;

import java.util.Map;

public interface Format {
    String format(Map<String, Map<String, Object>> differences) throws Exception;
}
