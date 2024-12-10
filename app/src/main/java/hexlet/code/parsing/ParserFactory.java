package hexlet.code.parsing;

public class ParserFactory {
    public static Parser getParser(String fileExtension) throws Exception {
        return switch (fileExtension) {
            case ".json" -> new JsonParser();
            case ".yml", ".yaml" -> new YamlParser();
            default -> throw new Exception("Unsupported file format: " + fileExtension);
        };
    }
}
