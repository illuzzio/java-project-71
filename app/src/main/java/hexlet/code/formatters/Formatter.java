package hexlet.code.formatters;

public class Formatter {
    public static Format getFormatter(String formatName) {
        return switch (formatName) {
            // case "..." -> new OtherFormat...();
            case "stylish" -> new Stylish();
            default -> new Stylish();
        };
    }
}
