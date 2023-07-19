package de.fhdw.freefeser.api.console.exceptions;

public class ConsoleReaderAlreadyRunningException extends ConsoleReaderException {

    public ConsoleReaderAlreadyRunningException() {
        super("Console reader is already running.");
    }

    public ConsoleReaderAlreadyRunningException(Throwable cause) {
        super("Console reader is already running.", cause);
    }
}
