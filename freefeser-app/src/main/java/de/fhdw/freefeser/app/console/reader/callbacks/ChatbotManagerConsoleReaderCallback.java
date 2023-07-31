package de.fhdw.freefeser.app.console.reader.callbacks;

import de.fhdw.freefeser.api.bot.ChatbotManager;
import de.fhdw.freefeser.api.console.reader.ConsoleReader;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.api.user.UserManager;

public class ChatbotManagerConsoleReaderCallback extends AppConsoleReaderCallback {

    private final ChatbotManager chatbotManager;
    private final UserManager userManager;

    public ChatbotManagerConsoleReaderCallback(ConsoleReader reader, ChatbotManager chatbotManager, UserManager userManager) {
        super(reader);
        this.chatbotManager = chatbotManager;
        this.userManager = userManager;
    }

    @Override
    public void onInputReceived(String input) {
        this.chatbotManager.executeCommand(this.userManager.getLoggedInUser(), input);
    }
}
