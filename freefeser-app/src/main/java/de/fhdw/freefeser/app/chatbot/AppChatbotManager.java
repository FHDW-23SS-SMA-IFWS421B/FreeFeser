package de.fhdw.freefeser.app.chatbot;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.ChatbotManager;
import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.database.ChatbotEntity;
import de.fhdw.freefeser.api.database.ChatbotEntityDatabaseManager;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.app.console.printer.AppConsolePrinter;
import de.fhdw.freefeser.app.databases.entities.AppChatbotEntity;
import de.fhdw.freefeser.app.textanalyzer.AppTranslationTextAnalyzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class AppChatbotManager implements ChatbotManager {

    private final Collection<Chatbot> bots;
    private final ChatbotEntityDatabaseManager databaseManager;
    private final ConsolePrinter printer;

    public AppChatbotManager(ChatbotEntityDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.bots = new ArrayList<>();
        this.printer = new AppConsolePrinter();
    }

    @Override
    public Collection<Chatbot> getBots() {
        return this.bots;
    }

    @Override
    public void registerBot(Chatbot chatbot) {
        this.databaseManager.getByName(chatbot.getName()).thenAccept(bot -> {
            if(bot == null) {
                ChatbotEntity entity = new AppChatbotEntity(chatbot.getName(), true);
                this.databaseManager.create(entity);
            }
            this.bots.add(chatbot);
        });
    }

    @Override
    public void unregisterBot(Chatbot chatbot) {
        this.bots.remove(chatbot);
    }

    @Override
    public Chatbot executeCommand(User sender, String text) {
        if(text.equalsIgnoreCase("!help") || text.equalsIgnoreCase("help")) {
            String helpMessage = "Chatbot-System Hilfe\n" +
                    "====================\n" +
                    "Verwendung der Bots:\n" +
                    "@wikibot              Was/Wer ist <Name/Ort/etc.>?\n" +
                    "@translationbot       Übersetze ins/auf <Ländercode (z.B. DE)>: <Text>\n" +
                    "@weatherbot           Wie wird/ist das Wetter in <Ort> heute/morgen/nächste Woche/etc. ?\n\n" +
                    "Befehle:\n" +
                    "!help                 Zeigt diese Hilfe-Nachricht an\n" +
                    "!activate <botname>   Aktiviert einen Bot\n" +
                    "!deactivate <botname> Deaktiviert einen Bot\n" +
                    "!list                 Listet alle Bots und deren Zustand auf\n" +
                    "!quit                 Beendet das Programm\n";
            this.printer.println(helpMessage);
        }

        if(text.equalsIgnoreCase("!quit") || text.equalsIgnoreCase("quit")) {
            this.printer.println("Das Chatbot-System wird heruntergefahren.");
            System.exit(0);
        }

        String[] commandCheckTextRaw = text.split(" ", 2);
        if(commandCheckTextRaw.length != 2) {
            return null;
        }

        Chatbot extractedBot = extractBot(commandCheckTextRaw[0]);
        if(extractedBot == null) {
            return null;
        }

        extractedBot.onExecute(sender, text.substring(1));

        return extractedBot;
    }

    public Chatbot extractBot(String firstPart) {
        if (!firstPart.startsWith("@")) {
            return null;
        }

        String botName = firstPart.substring(1);

        // take one textanalyzer instance that inherits the abstract class to call the extractBot()
        AppTranslationTextAnalyzer textAnalyzer = new AppTranslationTextAnalyzer();
        HashMap<String, String> extractedBot = textAnalyzer.analyze(botName);

        String selectedBot = extractedBot.get("Bot");
        if (selectedBot == null) {
            return null;
        }

        for (Chatbot bot : bots) {
            if (bot.getName().equalsIgnoreCase(selectedBot)) {
                return bot;
            }
        }

        return null;
    }
}
