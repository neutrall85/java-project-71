package hexlet.code;

public class FileProcessingException extends RuntimeException {

    public FileProcessingException(String message) {
        super(message);
    }

    public FileProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        String originalMessage = super.getMessage();
        if (getCause() != null) {
            return String.format("Ошибка обработки файла: %s. Причина: %s",
                    originalMessage, getCause().getMessage());
        }
        return String.format("Ошибка обработки файла: %s", originalMessage);
    }
}
