package de.fhdw.freefeser.api.console.reader.exceptions;

public class ConsoleReaderException extends Exception {
    public ConsoleReaderException(String message) {
        super(message);
    }

    public ConsoleReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
