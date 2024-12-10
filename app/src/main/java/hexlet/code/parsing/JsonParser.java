package hexlet.code.parsing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonParser implements Parser {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Map<String, Object> parse(String content) throws Exception {
        return OBJECT_MAPPER.readValue(content, new TypeReference<>() {});
    }
}
