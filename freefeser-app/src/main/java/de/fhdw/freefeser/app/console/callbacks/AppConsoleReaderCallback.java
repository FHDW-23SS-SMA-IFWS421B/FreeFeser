package de.fhdw.freefeser.app.console.callbacks;

import de.fhdw.freefeser.api.console.ConsoleReader;
import de.fhdw.freefeser.api.console.ConsoleReaderCallback;

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
