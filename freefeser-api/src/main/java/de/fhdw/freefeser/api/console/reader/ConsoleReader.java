package de.fhdw.freefeser.api.console.reader;

import de.fhdw.freefeser.api.console.reader.exceptions.ConsoleReaderAlreadyNotRunningException;
import de.fhdw.freefeser.api.console.reader.exceptions.ConsoleReaderAlreadyRunningException;

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
