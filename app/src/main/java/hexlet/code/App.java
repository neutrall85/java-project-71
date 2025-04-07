package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;


@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
class App implements Runnable {

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this @|fg(81) help|@ message and exit.")
    private boolean help;

    @Option(names = {"-V", "--version"}, description = "Print version information and exit.")
    private boolean version;

    @Override
    public void run() {
        if (version) {
            System.out.println("gendiff version 1.0");
        }
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}