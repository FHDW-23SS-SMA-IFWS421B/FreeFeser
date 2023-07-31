package de.fhdw.freefeser.app.databases.managers;

import de.fhdw.freefeser.api.database.UserEntity;
import de.fhdw.freefeser.api.database.UserEntityDatabaseManager;
import de.fhdw.freefeser.app.databases.entities.AppUser;
import de.fhdw.freefeser.app.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AppUserDatabaseManager implements UserEntityDatabaseManager {

    @Override
    public CompletableFuture<List<UserEntity>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            List<UserEntity> userList = new ArrayList<>();

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // Define the HQL query to select all users
                String hql = "FROM AppUser";

                // Create the query object
                Query<AppUser> query = session.createQuery(hql, AppUser.class);

                // Execute the query and get the result list
                List<AppUser> resultList = query.getResultList();

                // Process the result list and add to the final userList
                userList.addAll(resultList);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return userList;
        });
    }

    @Override
    public CompletableFuture<UserEntity> get(UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            AppUser user = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // Use Hibernate's get() method to retrieve the AppUser by ID
                user = session.get(AppUser.class, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return user;
        });
    }

    @Override
    public CompletableFuture<Void> update(UserEntity user) {
        return CompletableFuture.runAsync(() -> {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // Begin a new database transaction
                Transaction transaction = session.beginTransaction();

                // Merge the updated user entity into the session
                session.merge(user);

                // Commit the transaction to persist the changes
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public CompletableFuture<UserEntity> create(UserEntity entityWithoutId) {
        return CompletableFuture.supplyAsync(() -> {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // Begin a new database transaction
                Transaction transaction = session.beginTransaction();

                // Persist the new user entity into the session
                session.persist(entityWithoutId);

                // Commit the transaction to persist the changes
                transaction.commit();

                // Return the newly created user entity
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

                // Retrieve the user entity from the database by its ID
                AppUser appUser = session.get(AppUser.class, id);
                if (appUser != null) {
                    // Remove the user entity from the session and database
                    session.remove(appUser);
                    // Commit the transaction to persist the changes
                    transaction.commit();
                } else {
                    // Throw a runtime exception if the user with the specified ID is not found
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public CompletableFuture<UserEntity> getByUsername(String username) {
        return CompletableFuture.supplyAsync(() -> {
            AppUser user = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // Use Hibernate's createQuery() method to retrieve the AppUser by username
                user = session.createQuery("FROM AppUser u WHERE u.username = :username", AppUser.class).setParameter("username", username).uniqueResult();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return user;
        });
    }
}
