package de.fhdw.freefeser.app.console.printer;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;

public class AppConsolePrinter implements ConsolePrinter {

    @Override
    public void println(String value) {
        System.out.println(value);
    }
}
