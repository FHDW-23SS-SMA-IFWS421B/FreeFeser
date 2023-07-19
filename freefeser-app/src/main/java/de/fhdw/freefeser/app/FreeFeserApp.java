package de.fhdw.freefeser.app;

import de.fhdw.freefeser.api.bot.command.CommandManager;
import de.fhdw.freefeser.api.console.ConsoleReader;
import de.fhdw.freefeser.api.console.ConsoleReaderCallback;
import de.fhdw.freefeser.app.bot.command.AppCommandManager;
import de.fhdw.freefeser.app.chatbot.translation.commands.TranslationCommand;
import de.fhdw.freefeser.app.console.AppConsoleReader;
import de.fhdw.freefeser.app.console.callbacks.CommandManagerConsoleReaderCallback;
import de.fhdw.freefeser.app.console.callbacks.LoginConsoleReaderCallback;

public class FreeFeserApp {
    public static void main(String[] args) throws Exception {
        /*String text = "Wie ist das Wetter in Berlin?";
        String text2 = "Wie ist das Wetter in deiner Mutter?";
        String text3 = "wikiBot";
        String text4 = "WeatherBot";
        String text5 = "Wie ist das Wetter in Berlin?";
        String text6 = "Wie wird das Wetter in Bielefeld morgen?";

        String location = TextAnalyzer.extractLocation(text);
        String location2 = TextAnalyzer.extractLocation(text2);
        System.out.println(location + "\n" + location2 + "\n");

        String bot = TextAnalyzer.extractBot(text3);
        String bot2 = TextAnalyzer.extractBot(text4);
        System.out.println(bot + "\n" + bot2 + "\n");

        String weather = TextAnalyzer.extractWeatherCurrentOrForecast(text5);
        String weather2 = TextAnalyzer.extractWeatherCurrentOrForecast(text6);
        System.out.println(weather + "\n" + weather2);

        AppUser appUser = new AppUser("testuser", "test123");
        AppUser appUser1 = new AppUser("testuser2", "admin");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the appUser object
            session.persist(appUser);
            session.persist(appUser1);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<AppUser> appUsers = session.createQuery("from AppUser", AppUser.class).list();
            appUsers.forEach(a -> System.out.println(a.getId()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }*/

        CommandManager commandManager = new AppCommandManager();
        commandManager.registerCommand(new TranslationCommand(null, "translate", "Translate something"));

        ConsoleReader reader = new AppConsoleReader(System.in);

        ConsoleReaderCallback loginCallback = new LoginConsoleReaderCallback(reader, user -> {
            reader.addCallback(new CommandManagerConsoleReaderCallback(reader, commandManager));
        });

        reader.addCallback(loginCallback);

        reader.start();
    }
}