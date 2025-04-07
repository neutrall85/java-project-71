package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.IOException;


@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
class App implements Runnable {

    @Parameters(
            index = "0",
            description = "path to first file",
            paramLabel = "filepath1",
            arity = "1"
    )
    private String filepath1;

    @Parameters(
            index = "1",
            description = "path to second file",
            paramLabel = "filepath2",
            arity = "1"
    )
    private String filepath2;

    @Option(names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format [default: stylish]",
            defaultValue = "stylish")
    private String format;

    @Option(names = {"-h", "--help"},
            usageHelp = true,
            description = "Show this @|fg(81) help|@ message and exit.")
    private boolean help;

    @Option(names = {"-V", "--version"},
            description = "Print version information and exit.")
    private boolean version;

    @Override
    public void run() {
        if (version) {
            System.out.println("gendiff version 1.0");
        }
    }
    public Integer call() throws IOException {
        System.out.printf("Comparing %s with %s using format %s%n", filepath1, filepath2, format);
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}