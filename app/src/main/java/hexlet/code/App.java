package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true, // activate --version --help
        version = "gendiff 1.0.0" // Version of the application
)
public class App implements Callable<Integer> {
    @Option(
            names = {"-f", "--format"},
            description = "output format [default: ${DEFAULT-VALUE}]",
            defaultValue = "stylish", paramLabel = "format"
    )
    private static String format; // todo

    @Parameters(index = "0", description = "path to the first file", paramLabel = "filepath1")
    private String filepath1;

    @Parameters(index = "1", description = "path to the second file", paramLabel = "filepath2")
    private String filepath2;

    public static void main(String[] args) {
        var commandLine = new CommandLine(new App());
        commandLine.parseArgs(args);

        if (commandLine.isVersionHelpRequested()) {
            commandLine.printVersionHelp(System.out);
            return;
        }

        if (commandLine.isUsageHelpRequested()) {
            commandLine.usage(System.out);
            return;
        }

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            String diff = Differ.generate(filepath1, filepath2);
            System.out.println(diff);
            return 0;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return 1;
        }
    }
}
