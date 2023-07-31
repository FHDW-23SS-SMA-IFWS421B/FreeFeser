package de.fhdw.freefeser.api.console.reader;

public interface ConsoleReaderCallback {

    void onInputReceived(String input);

    boolean unregister();
}
