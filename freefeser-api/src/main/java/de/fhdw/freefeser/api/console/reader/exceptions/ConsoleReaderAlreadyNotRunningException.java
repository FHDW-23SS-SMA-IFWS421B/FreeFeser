package de.fhdw.freefeser.api.console.reader.exceptions;

public class ConsoleReaderAlreadyNotRunningException extends ConsoleReaderException {

    public ConsoleReaderAlreadyNotRunningException() {
        super("Console reader is not running.");
    }

    public ConsoleReaderAlreadyNotRunningException(Throwable cause) {
        super("Console reader is not running.", cause);
    }
}
