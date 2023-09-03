package de.fhdw.freefeser.app.console.reader.callbacks;

import de.fhdw.freefeser.api.bot.ChatbotManager;
import de.fhdw.freefeser.api.console.reader.ConsoleReader;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.api.user.UserManager;
import de.fhdw.freefeser.app.databases.entities.AppChatMessageEntity;
import de.fhdw.freefeser.app.databases.managers.AppChatMessageDatabaseManager;

import java.time.LocalDateTime;

public class ChatbotManagerConsoleReaderCallback extends AppConsoleReaderCallback {

    private final ChatbotManager chatbotManager;
    private final UserManager userManager;
    private final AppChatMessageDatabaseManager chatMessageManager;

    public ChatbotManagerConsoleReaderCallback(ConsoleReader reader, ChatbotManager chatbotManager, UserManager userManager, AppChatMessageDatabaseManager chatMessageManager) {
        super(reader);
        this.chatbotManager = chatbotManager;
        this.userManager = userManager;
        this.chatMessageManager = chatMessageManager;
    }

    @Override
    public void onInputReceived(String input) {

        if(this.userManager.getLoggedInUser() != null) {
            User user = this.userManager.getLoggedInUser();
            AppChatMessageEntity messageEntity = new AppChatMessageEntity(input, LocalDateTime.now(), user.getEntity());
            this.chatMessageManager.create(messageEntity);
        }

        this.chatbotManager.executeCommand(this.userManager.getLoggedInUser(), input);
    }
}
