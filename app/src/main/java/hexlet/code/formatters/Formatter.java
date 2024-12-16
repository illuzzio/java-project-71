package hexlet.code.formatters;

public class Formatter {
    public static Format getFormatter(String formatName) {
        return switch (formatName) {
            case "json" -> new Json();
            case "plain" -> new Plain();
            default -> new Stylish();
        };
    }
}
