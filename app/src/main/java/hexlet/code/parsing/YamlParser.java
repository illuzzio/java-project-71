package hexlet.code.parsing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class YamlParser implements Parser {
    private static final YAMLMapper YAML_MAPPER = new YAMLMapper();

    @Override
    public Map<String, Object> parse(String content) throws Exception {
        return YAML_MAPPER.readValue(content, new TypeReference<>() {});
    }
}
