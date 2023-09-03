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
import java.util.concurrent.CompletableFuture;

public class AppChatbotManager implements ChatbotManager {

    private final Collection<Chatbot> bots;
    private final ChatbotEntityDatabaseManager databaseManager;
    private final ConsolePrinter printer;

    public AppChatbotManager(ChatbotEntityDatabaseManager databaseManager, ConsolePrinter printer) {
        this.databaseManager = databaseManager;
        this.bots = new ArrayList<>();
        this.printer = printer;
    }

    @Override
    public Collection<Chatbot> getBots() {
        return this.bots;
    }

    @Override
    public CompletableFuture<Void> registerBot(Chatbot chatbot) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        this.databaseManager.getByName(chatbot.getName()).thenAccept(bot -> {
            if(bot == null) {
                ChatbotEntity entity = new AppChatbotEntity(chatbot.getName(), true);
                this.databaseManager.create(entity).thenAccept(created -> {
                    this.bots.add(chatbot);
                    future.complete(null);
                });
            } else {
                this.bots.add(chatbot);
                future.complete(null);
            }
        });
        return future;
    }

    @Override
    public void unregisterBot(Chatbot chatbot) {
        this.bots.remove(chatbot);
    }

    @Override
    public Chatbot executeCommand(User sender, String text) {
        if(text.equalsIgnoreCase("!help") || text.equalsIgnoreCase("help")) {
            sendHelpMessage();
            return null;
        }

        if(text.equalsIgnoreCase("!quit") || text.equalsIgnoreCase("quit")) {
            this.printer.println("Das Chatbot-System wird heruntergefahren.", false);
            System.exit(0);
        }

        String[] commandCheckTextRaw = text.split(" ", 2);
        if(commandCheckTextRaw.length != 2) {
            sendShortHelpMessage();
            return null;
        }

        Chatbot extractedBot = extractBot(commandCheckTextRaw[0]);
        if(extractedBot == null) {
            sendShortHelpMessage();
            return null;
        }

        extractedBot.onExecute(sender, text.substring(1));

        return extractedBot;
    }

    private Chatbot extractBot(String firstPart) {
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

    private void sendShortHelpMessage() {
        String helpMessage = "[system] Ungültige Eingabe. Verwende !help, um Hilfe zu erhalten.";
        this.printer.println(helpMessage);
    }

    private void sendHelpMessage() {
        String helpMessage = "[system] Chatbot-System Hilfe\n" +
                "[system] ====================\n" +
                "[system] Verwendung der Bots:\n" +
                "[system] @wikibot              Was/Wer ist <Name/Ort/etc.>?\n" +
                "[system] @translationbot       Übersetze ins/auf <Ländercode (z.B. DE)>: <Text>\n" +
                "[system] @weatherbot           Wie wird/ist das Wetter in <Ort> heute/morgen/nächste Woche/etc. ?\n\n" +
                "[system] Befehle:\n" +
                "[system] !help                 Zeigt diese Hilfe-Nachricht an\n" +
                "[system] !activate <botname>   Aktiviert einen Bot\n" +
                "[system] !deactivate <botname> Deaktiviert einen Bot\n" +
                "[system] !list                 Listet alle Bots und deren Zustand auf\n" +
                "[system] !quit                 Beendet das Programm\n";
        this.printer.println(helpMessage);
    }
}
