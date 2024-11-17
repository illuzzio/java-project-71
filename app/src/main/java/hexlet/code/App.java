package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true
)
public class App implements Callable {
    @Option(names = {"-h", "--help"}, description = "Show this help message and exit.", usageHelp = true)
    private boolean help;

    @Option(names = {"-V", "--version"}, description = "Print version information and exit.", versionHelp = true)
    private boolean version;

    @Option(names = {"-f", "--format"}, description = "output format [default: ${DEFAULT-VALUE}]", defaultValue = "stylish", paramLabel = "format")
    private String format;

    @Parameters(index = "0", description = "path to the first file", paramLabel = "filepath1")
    private String filepath1;

    @Parameters(index = "1", description = "path to the second file", paramLabel = "filepath2")
    private String filepath2;

    private static final String VERSION = "1.0.0"; // Define the version of the application

    public static void main(String[] args) {
            int exitCode = new CommandLine(new App()).execute(args);
            System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            System.out.println("format is - " + format);
            Differ differ = new Differ(format);
            String diff = differ.generate(filepath1, filepath2);
            System.out.println(diff);
            return 0;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return 1;
        }
    }

    public static void printVersion() { // todo
        System.out.println("gendiff version " + VERSION);
    }
}
