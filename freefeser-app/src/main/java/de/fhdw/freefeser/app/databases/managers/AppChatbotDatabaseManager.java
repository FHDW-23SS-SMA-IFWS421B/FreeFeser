package de.fhdw.freefeser.app.databases.managers;

import de.fhdw.freefeser.api.database.ChatbotEntity;
import de.fhdw.freefeser.api.database.ChatbotEntityDatabaseManager;
import de.fhdw.freefeser.app.databases.entities.AppChatbotEntity;
import de.fhdw.freefeser.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AppChatbotDatabaseManager implements ChatbotEntityDatabaseManager {

    @Override
    public CompletableFuture<List<ChatbotEntity>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            List<ChatbotEntity> chatbotList = new ArrayList<>();

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // Define the HQL query to select all chatbots
                String hql = "FROM AppChatbotEntity";

                // Create the query object
                Query<AppChatbotEntity> query = session.createQuery(hql, AppChatbotEntity.class);

                // Execute the query and get the result list
                List<AppChatbotEntity> resultList = query.getResultList();

                // Process the result list and add to the final chatbotList
                chatbotList.addAll(resultList);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return chatbotList;
        });
    }

    @Override
    public CompletableFuture<ChatbotEntity> get(UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            AppChatbotEntity appChatbot = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // Use Hibernate's get() method to retrieve the AppChatbot by ID
                appChatbot = session.get(AppChatbotEntity.class, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return appChatbot;
        });
    }

    @Override
    public CompletableFuture<Void> update(ChatbotEntity chatbot) {
        return CompletableFuture.runAsync(() -> {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // Begin a new database transaction
                Transaction transaction = session.beginTransaction();

                // Merge the updated chatbot entity into the session
                session.merge(chatbot);

                // Commit the transaction to persist the changes
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public CompletableFuture<ChatbotEntity> create(ChatbotEntity entityWithoutId) {
        return CompletableFuture.supplyAsync(() -> {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // Begin a new database transaction
                Transaction transaction = session.beginTransaction();

                // Persist the new chatbot entity into the session
                session.persist(entityWithoutId);

                // Commit the transaction to persist the changes
                transaction.commit();

                // Return the newly created chatbot entity
                return entityWithoutId;
            } catch (Exception e) {
                e.printStackTrace();

                return null;
            }
        });
    }

    @Override
    public CompletableFuture<Void> delete(UUID id) {
        return CompletableFuture.runAsync(() -> {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // Begin a new database transaction
                Transaction transaction = session.beginTransaction();

                // Retrieve the chatbot entity from the database by its ID
                AppChatbotEntity appChatbotEntity = session.get(AppChatbotEntity.class, id);
                if (appChatbotEntity != null) {
                    // Remove the chatbot entity from the session and database
                    session.remove(appChatbotEntity);
                    // Commit the transaction to persist the changes
                    transaction.commit();
                } else {
                    // Throw a runtime exception if the chatbot with the specified ID is not found
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
