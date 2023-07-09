package de.fhdw.freefeser.app;

import de.fhdw.freefeser.app.prototype.textanalyzer.TextAnalyzer;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.fhdw.freefeser.app.prototype.databases.AppUser;
import de.fhdw.freefeser.app.util.HibernateUtil;

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
        System.out.println(weather + "\n" + weather2);*/

        AppUser appUser = new AppUser();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(appUser);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        /*try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List <AppUser> appUsers = session.createQuery("from AppUser", AppUser.class).list();
            appUsers.forEach(a -> System.out.println(a.getUserName()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }*/
    }
}