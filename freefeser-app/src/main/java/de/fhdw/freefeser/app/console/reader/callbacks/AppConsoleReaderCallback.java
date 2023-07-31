package de.fhdw.freefeser.app.console.reader.callbacks;

import de.fhdw.freefeser.api.console.reader.ConsoleReader;
import de.fhdw.freefeser.api.console.reader.ConsoleReaderCallback;

public abstract class AppConsoleReaderCallback implements ConsoleReaderCallback {

    private final ConsoleReader reader;

    protected AppConsoleReaderCallback(ConsoleReader reader) {
        this.reader = reader;
    }

    @Override
    public boolean unregister() {
        return this.reader.removeCallback(this);
    }
}
