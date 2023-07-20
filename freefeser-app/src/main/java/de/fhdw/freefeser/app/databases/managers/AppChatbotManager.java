package de.fhdw.freefeser.app.databases.managers;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.ChatbotManager;
import de.fhdw.freefeser.app.databases.entities.AppChatbot;
import de.fhdw.freefeser.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppChatbotManager implements ChatbotManager {

    @Override
    public boolean registerBot(Chatbot chatbot) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a new database transaction
            Transaction transaction = session.beginTransaction();

            // Cast the chatbot to AppChatbot
            AppChatbot appChatbot = (AppChatbot) chatbot;

            // Retrieve the chatbot entity from the database by its ID
            appChatbot = session.get(AppChatbot.class, appChatbot.getId());

            // Update the active status to true
            appChatbot.setStatus(true);

            // Save the updated entity back to the database
            session.merge(appChatbot);

            // Commit the transaction to persist the changes
            transaction.commit();

            // Return true to indicate a successful registration
            return true;
        } catch (Exception e) {
            // Handle any exceptions that might occur during the process
            e.printStackTrace();
        }
        // Return false in case there is an exception or the chatbot is not an instance of AppChatbot
        return false;
    }

    @Override
    public boolean unregisterBot(Chatbot chatbot) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Cast the chatbot to AppChatbot
            AppChatbot appChatbot = (AppChatbot) chatbot;

            // Retrieve the chatbot entity from the database by its ID
            appChatbot = session.get(AppChatbot.class, appChatbot.getId());

            // Update the active status to false
            appChatbot.setStatus(false);

            // Save the updated entity
            session.merge(appChatbot);

            transaction.commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
