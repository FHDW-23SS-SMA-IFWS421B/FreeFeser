package de.fhdw.freefeser.api.console;

import de.fhdw.freefeser.api.console.exceptions.ConsoleReaderAlreadyNotRunningException;
import de.fhdw.freefeser.api.console.exceptions.ConsoleReaderAlreadyRunningException;

import java.io.InputStream;
import java.util.Collection;

public interface ConsoleReader {

    InputStream getInput();

    void start() throws ConsoleReaderAlreadyRunningException;

    void stop() throws ConsoleReaderAlreadyNotRunningException;

    boolean isRunning();

    Collection<ConsoleReaderCallback> getCallbacks();

    void addCallback(ConsoleReaderCallback callback);

    boolean removeCallback(ConsoleReaderCallback callback);

}
