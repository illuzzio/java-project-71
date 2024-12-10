package hexlet.code.parsing;

import java.util.Map;

public interface Parser {
    Map<String, Object> parse(String content) throws Exception;
}
