package de.fhdw.freefeser.app.databases.managers;

import de.fhdw.freefeser.api.database.ChatMessageEntity;
import de.fhdw.freefeser.api.database.ChatMessageEntityDatabaseManager;
import de.fhdw.freefeser.app.databases.entities.AppChatMessageEntity;
import de.fhdw.freefeser.app.databases.entities.AppChatbotEntity;
import de.fhdw.freefeser.app.databases.entities.AppUserEntity;
import de.fhdw.freefeser.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AppChatMessageDatabaseManager implements ChatMessageEntityDatabaseManager<AppUserEntity, AppChatbotEntity> {

    private final HibernateUtil hibernateUtil;

    public AppChatMessageDatabaseManager(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    @Override
    public CompletableFuture<List<ChatMessageEntity<AppUserEntity, AppChatbotEntity>>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            List<ChatMessageEntity<AppUserEntity, AppChatbotEntity>> chatMessageList = new ArrayList<>();

            try (Session session = this.hibernateUtil.getSessionFactory().openSession()) {
                // Define the HQL query to select the top 100 chat messages, fetch related user and chatbot entities, and order by timestamp
                String hql = "SELECT cm FROM AppChatMessageEntity cm ORDER BY cm.timestamp ASC";

                // Create the query object
                Query<AppChatMessageEntity> query = session.createQuery(hql, AppChatMessageEntity.class);

                // Set the maximum number of results to 100
                query.setMaxResults(100);

                // Execute the query and get the result list
                List<AppChatMessageEntity> resultList = query.getResultList();

                // Process the result list and add to the final chatMessageList
                chatMessageList.addAll(resultList);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return chatMessageList;
        });
    }

    @Override
    public CompletableFuture<ChatMessageEntity<AppUserEntity, AppChatbotEntity>> get(UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            AppChatMessageEntity chatMessage = null;
            try (Session session = this.hibernateUtil.getSessionFactory().openSession()) {
                // Use Hibernate's get() method to retrieve the AppChatMessage by ID
                chatMessage = session.get(AppChatMessageEntity.class, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return chatMessage;
        });
    }

    @Override
    public CompletableFuture<Void> update(ChatMessageEntity<AppUserEntity, AppChatbotEntity> chatMessage) {
        return CompletableFuture.runAsync(() -> {
            try (Session session = this.hibernateUtil.getSessionFactory().openSession()) {
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
    public CompletableFuture<ChatMessageEntity<AppUserEntity, AppChatbotEntity>> create(ChatMessageEntity<AppUserEntity, AppChatbotEntity> entityWithoutId) {
        return CompletableFuture.supplyAsync(() -> {
            try (Session session = this.hibernateUtil.getSessionFactory().openSession()) {
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
            try (Session session = this.hibernateUtil.getSessionFactory().openSession()) {
                // Begin a new database transaction
                Transaction transaction = session.beginTransaction();

                // Retrieve the chatMessage entity from the database by its ID
                AppChatMessageEntity appChatMessageEntity = session.get(AppChatMessageEntity.class, id);
                if (appChatMessageEntity != null) {
                    // Remove the chatMessage entity from the session and database
                    session.remove(appChatMessageEntity);
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

    @Override
    public CompletableFuture<List<ChatMessageEntity<AppUserEntity, AppChatbotEntity>>> getAll(String username) {
        return CompletableFuture.supplyAsync(() -> {
            List<ChatMessageEntity<AppUserEntity, AppChatbotEntity>> chatMessageList = new ArrayList<>();

            try (Session session = this.hibernateUtil.getSessionFactory().openSession()) {
                // Define the HQL query to select the top 100 chat messages, fetch related user and chatbot entities, and order by timestamp
                String hql = "SELECT cm FROM AppChatMessageEntity cm WHERE user.username = :username ORDER BY cm.timestamp ASC";

                // Create the query object
                Query<AppChatMessageEntity> query = session.createQuery(hql, AppChatMessageEntity.class);
                query.setParameter("username", username);

                // Set the maximum number of results to 100
                query.setMaxResults(100);

                // Execute the query and get the result list
                List<AppChatMessageEntity> resultList = query.getResultList();

                // Process the result list and add to the final chatMessageList
                chatMessageList.addAll(resultList);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return chatMessageList;
        });
    }
}
