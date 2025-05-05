package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff version 1.0",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<String> {

    @Parameters(
            index = "0",
            description = "path to first file",
            paramLabel = "filepath1")
    private String filepath1;

    @Parameters(
            index = "1",
            description = "path to second file",
            paramLabel = "filepath2")
    private String filepath2;

    @Option(names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format [default: stylish]",
            defaultValue = "stylish")
    private String format;

    @Override
    public String call() throws Exception {
        System.out.println(Differ.generate(filepath1, filepath2));
        return "";
    }

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
