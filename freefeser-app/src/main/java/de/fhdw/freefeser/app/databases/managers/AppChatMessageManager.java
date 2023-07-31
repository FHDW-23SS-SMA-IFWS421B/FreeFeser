package de.fhdw.freefeser.app.databases.managers;

import de.fhdw.freefeser.api.database.ChatMessageEntity;
import de.fhdw.freefeser.api.database.ChatMessageEntityDatabaseManager;
import de.fhdw.freefeser.app.databases.entities.AppChatMessage;
import de.fhdw.freefeser.app.databases.entities.AppChatbot;
import de.fhdw.freefeser.app.databases.entities.AppUser;
import de.fhdw.freefeser.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AppChatMessageManager implements ChatMessageEntityDatabaseManager<AppUser, AppChatbot> {

    @Override
    public CompletableFuture<List<ChatMessageEntity<AppUser, AppChatbot>>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            List<ChatMessageEntity<AppUser, AppChatbot>> chatMessageList = new ArrayList<>();

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // Define the HQL query to select all chat messages and fetch related user and chatbot entities
                String hql = "FROM AppChatMessage";

                // Create the query object
                Query<AppChatMessage> query = session.createQuery(hql, AppChatMessage.class);

                // Execute the query and get the result list
                List<AppChatMessage> resultList = query.getResultList();

                // Process the result list and add to the final chatMessageList
                chatMessageList.addAll(resultList);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return chatMessageList;
        });
    }

    @Override
    public CompletableFuture<ChatMessageEntity<AppUser, AppChatbot>> get(UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            AppChatMessage chatMessage = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // Use Hibernate's get() method to retrieve the AppChatMessage by ID
                chatMessage = session.get(AppChatMessage.class, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return chatMessage;
        });
    }

    @Override
    public CompletableFuture<Void> update(ChatMessageEntity<AppUser, AppChatbot> chatMessage) {
        return CompletableFuture.runAsync(() -> {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // Begin a new database transaction
                Transaction transaction = session.beginTransaction();

                // Merge the updated chatMessage entity into the session
                session.merge(chatMessage);

                // Commit the transaction to persist the changes
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public CompletableFuture<ChatMessageEntity<AppUser, AppChatbot>> create(ChatMessageEntity<AppUser, AppChatbot> entityWithoutId) {
        return CompletableFuture.supplyAsync(() -> {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // Begin a new database transaction
                Transaction transaction = session.beginTransaction();

                // Persist the new chatMessage entity into the session
                session.persist(entityWithoutId);

                // Commit the transaction to persist the changes
                transaction.commit();

                // Return the newly created chatMessage entity
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

                // Retrieve the chatMessage entity from the database by its ID
                AppChatMessage appChatMessage = session.get(AppChatMessage.class, id);
                if (appChatMessage != null) {
                    // Remove the chatMessage entity from the session and database
                    session.remove(appChatMessage);
                    // Commit the transaction to persist the changes
                    transaction.commit();
                } else {
                    // Throw a runtime exception if the chatMessage with the specified ID is not found
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
