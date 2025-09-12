package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestFileProcessingException {

    @Test
    void testConstructorWithMessage() {
        String message = "Не удалось прочитать файл";
        FileProcessingException exception = new FileProcessingException(message);

        String expectedMessage = String.format("Ошибка обработки файла: %s", message);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String message = "Ошибка при записи файла";
        String causeMessage = "Файл не найден";
        Throwable cause = new NullPointerException(causeMessage);
        FileProcessingException exception = new FileProcessingException(message, cause);

        String expectedMessage = String.format("Ошибка обработки файла: %s. Причина: %s",
                message, causeMessage);

        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testGetMessageWithoutCause() {
        String message = "Файл поврежден";
        FileProcessingException exception = new FileProcessingException(message);

        String expected = String.format("Ошибка обработки файла: %s", message);
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void testGetMessageWithCause() {
        String message = "Ошибка доступа к файлу";
        String causeMessage = "Файл заблокирован другим процессом";
        Throwable cause = new Exception(causeMessage);
        FileProcessingException exception = new FileProcessingException(message, cause);

        String expected = String.format("Ошибка обработки файла: %s. Причина: %s",
                message, causeMessage);
        assertEquals(expected, exception.getMessage());
    }
}
