package sew.ai.helpers.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sew.ai.utils.StringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.AccessDeniedException;

public class FileException extends RuntimeException {
    private static final Logger log = LogManager.getLogger(FileException.class);
    private static final long serialVersionUID = 7430222710522100336L;
    private static final String CONFIGURATION_FILE = "sentinel.yml";
    private final File file;

    /**
     * Creates a custom exception message and logs it to the error log.
     *
     * @param file java.io.File the File that caused the exception
     */
    public FileException(File file) {
        super();
        this.file = file;
        logMessage();
    }

    /**
     * Creates a custom exception message and logs it to the error log.
     *
     * @param cause Throwable the exception that was thrown that caused this exception to be thrown
     * @param file  java.io.File the File that caused the exception
     */
    public FileException(Throwable cause, File file) {
        super(cause);
        this.file = file;
        logMessage();
    }

    /**
     * Creates a custom exception message and logs it to the error log.
     *
     * @param message String the custom text to add to the custom exception message
     * @param file    java.io.File the File that caused the exception
     */
    public FileException(String message, File file) {
        super(message);
        this.file = file;
        logMessage();
    }

    /**
     * Creates a custom exception message and logs it to the error log.
     *
     * @param message String the custom text to add to the custom exception message
     * @param cause   Throwable the exception that was thrown that caused this exception to be thrown
     * @param file    java.io.File the File that caused the exception
     */
    public FileException(String message, Throwable cause, File file) {
        super(message, cause);
        this.file = file;
        logMessage();
    }

    /**
     * Returns a custom error message based on the Exception that initially caused this to be thrown.
     *
     * @return String the custom error message
     */
    @Override
    public String getMessage() {
        Object cause = this.getCause();
        if (cause == null)
            return nullSafeMessage();
        if (cause instanceof NoSuchMethodException)
            return StringUtil.format("{} could not find suitable method (constructor, most likely) " +
                    "for element in this file. {}", filePath(), super.getMessage());
        if (cause instanceof InvocationTargetException)
            return StringUtil.format("{} target invocation failure in this file. {}", filePath(),
                    super.getMessage());
        if (cause instanceof IllegalAccessException)
            return StringUtil.format("{} illegal access failure in this file. {}", filePath(),
                    super.getMessage());
        if (cause instanceof InstantiationException)
            return StringUtil.format("{} could not instantiate element in this file. {}", filePath(),
                    super.getMessage());
        if (cause instanceof ClassNotFoundException)
            return StringUtil.format("{} could not find suitable class for element in this file. {}",
                    filePath(), super.getMessage());
        if (cause instanceof FileNotFoundException)
            return StringUtil.format("{} cannot be found in the specified location. {}", file.getName(),
                    super.getMessage());
        if (cause instanceof AccessDeniedException)
            return StringUtil.format("{} could not be accessed. Please ensure the file can be read " +
                            "by the current user and is not password protected. {}",
                    filePath(), super.getMessage());
        if (cause instanceof JsonParseException)
            return StringUtil.format("{} is not a valid YAML file. {}", filePath(), super.getMessage());
        if (cause instanceof JsonMappingException)
            return StringUtil.format("{} has incorrect formatting and cannot be read. {}", filePath(),
                    super.getMessage());
        if (cause instanceof IOException)
            return StringUtil.format("{} cannot be opened. {}", filePath(), super.getMessage());
        return nullSafeMessage();
    }

    /**
     * Returns a message without "null" showing up in it.
     *
     * @return String the message to send
     */
    private String nullSafeMessage() {
        String message = super.getMessage();
        if (message == null) {
            return filePath();
        }
        return filePath() + " " + message;
    }

    /**
     * Returns the File set when this exception was created.
     *
     * @return java.io.File the File that caused the exception
     */
    public File getFile() {
        return file;
    }

    /**
     * Returns the absolute path to the file or directory that threw the error.
     *
     * @return String the absolute path of the File that caused the exception
     */
    public String filePath() {
        return file.getAbsoluteFile().toString();
    }

    /**
     * Logs the error if it is not a missing sentinel.yml configuration file.
     * Logs missing sentinel.yml file if DEBUG mode is on.
     * Logs the stack trace for all but a missing sentinel.yml file if TRACE mode is on.
     */
    public void logMessage() {
        if (filePath().contains(CONFIGURATION_FILE) && this.getCause() instanceof FileNotFoundException) {
            log.debug(this.getMessage());
            return;
        }
        if (log.isTraceEnabled())
            log.trace(this.getMessage(), this);
        else
            log.error(this.getMessage());
    }
}
