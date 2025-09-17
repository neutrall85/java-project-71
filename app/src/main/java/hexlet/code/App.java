package hexlet.code;

import lombok.Getter;
import lombok.Setter;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;


import static hexlet.code.Differ.generate;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff version 1.0",
        description = "Compares two configuration files and shows a difference")
@Getter
@Setter
public final class App implements Callable<Integer> {
    static final int SUCCESS_EXIT_CODE = 0;
    static final int ERROR_EXIT_CODE = 1;

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
    public Integer call() {
        try {
            String result = generate(filepath1, filepath2, format);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ERROR_EXIT_CODE;
        }
        return SUCCESS_EXIT_CODE;
    }

    public static void main(String[] args) {
        try {
            int exit = new CommandLine(new App()).execute(args);
            System.exit(exit);
        } catch (Exception e) {
            System.err.println("Ошибка" + e.getMessage());
        }
    }
}
