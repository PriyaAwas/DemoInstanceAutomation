package sew.ai.helpers.exceptions;

@SuppressWarnings("serial")
public class InvalidPathExcelException extends FrameworkExceptions {

    public InvalidPathExcelException(String message) {
        super(message);
    }

    public InvalidPathExcelException(String message, Throwable cause) {
        super(message, cause);
    }
}
