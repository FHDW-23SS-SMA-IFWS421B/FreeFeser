package de.fhdw.freefeser.api.console;

public interface ConsoleReaderCallback {

    void onInputReceived(String input);

    boolean unregister();
}
