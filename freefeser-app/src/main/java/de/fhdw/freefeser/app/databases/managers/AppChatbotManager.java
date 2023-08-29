package de.fhdw.freefeser.app.databases.managers;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.ChatbotManager;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.app.databases.entities.AppChatbotEntity;
import de.fhdw.freefeser.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;

public class AppChatbotManager implements ChatbotManager {

    @Override
    public Collection<Chatbot> getBots() {
        return null;
    }

    @Override
    public boolean registerBot(Chatbot chatbot) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a new database transaction
            Transaction transaction = session.beginTransaction();

            // Cast the chatbot to AppChatbot
            AppChatbotEntity appChatbotEntity = (AppChatbotEntity) chatbot;

            // Retrieve the chatbot entity from the database by its ID
            appChatbotEntity = session.get(AppChatbotEntity.class, appChatbotEntity.getId());

            // Update the active status to true
            appChatbotEntity.setStatus(true);

            // Save the updated entity back to the database
            session.merge(appChatbotEntity);

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
            AppChatbotEntity appChatbotEntity = (AppChatbotEntity) chatbot;

            // Retrieve the chatbot entity from the database by its ID
            appChatbotEntity = session.get(AppChatbotEntity.class, appChatbotEntity.getId());

            // Update the active status to false
            appChatbotEntity.setStatus(false);

            // Save the updated entity
            session.merge(appChatbotEntity);

            transaction.commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Chatbot executeCommand(User sender, String text) {
        return null;
    }
}
