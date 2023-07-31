package de.fhdw.freefeser.app.chatbot;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.console.printer.ConsolePrinter;

import java.util.concurrent.CompletableFuture;

public abstract class AppChatbot implements Chatbot {

    private final ConsolePrinter printer;
    private final String name;

    public AppChatbot(ConsolePrinter printer, String name) {
        this.printer = printer;
        this.name = name;
    }


    @Override
    public String getName() {
        return this.name;//@Todo change name from db entity
    }

    @Override
    public CompletableFuture<Boolean> isEnabled() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setEnabled(boolean enabled) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendMessageOnBehalf(String message) {
        //@Todo formatter
        printer.println("["+getName()+"] "+message);
    }
}
