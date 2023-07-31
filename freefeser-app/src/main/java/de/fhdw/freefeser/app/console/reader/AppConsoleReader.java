package de.fhdw.freefeser.app.console.reader;

import de.fhdw.freefeser.api.console.reader.ConsoleReader;
import de.fhdw.freefeser.api.console.reader.ConsoleReaderCallback;
import de.fhdw.freefeser.api.console.reader.exceptions.ConsoleReaderAlreadyNotRunningException;
import de.fhdw.freefeser.api.console.reader.exceptions.ConsoleReaderAlreadyRunningException;

import java.io.Console;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class AppConsoleReader implements ConsoleReader {

    private final InputStream input;
    private boolean isRunning;
    private final Scanner scanner;
    private final Collection<ConsoleReaderCallback> callbacks;

    public AppConsoleReader(InputStream input, Collection<ConsoleReaderCallback> callbacks) {
        this.input = input;
        this.scanner = new Scanner(input);
        this.isRunning = false;
        this.callbacks = callbacks;
    }

    public AppConsoleReader(InputStream input) {
        this(input, new ArrayList<>());
    }


    @Override
    public InputStream getInput() {
        return this.input;
    }

    @Override
    public void start() throws ConsoleReaderAlreadyRunningException {
        if(this.isRunning) {
            throw new ConsoleReaderAlreadyRunningException();
        }

        this.isRunning = true;
        while (this.isRunning) {
            String input = scanner.nextLine();
            for(ConsoleReaderCallback callback : getCallbacks()) {
                callback.onInputReceived(input);
            }
        }
    }

    @Override
    public void stop() throws ConsoleReaderAlreadyNotRunningException {
        if(!this.isRunning) {
            throw new ConsoleReaderAlreadyNotRunningException();
        }

        this.isRunning = false;
        this.scanner.close();
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    @Override
    public Collection<ConsoleReaderCallback> getCallbacks() {
        return this.callbacks;
    }

    @Override
    public void addCallback(ConsoleReaderCallback callback) {
        this.callbacks.add(callback);
    }

    @Override
    public boolean removeCallback(ConsoleReaderCallback callback) {
        return this.callbacks.remove(callback);
    }
}
