package de.fhdw.freefeser.api.console.printer;

public interface ConsolePrinter {

    void println(String value);

    void println(String value, boolean askForInput);

    void print(String value);
}
