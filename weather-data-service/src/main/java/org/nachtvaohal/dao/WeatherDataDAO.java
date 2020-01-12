package org.nachtvaohal.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.nachtvaohal.model.WeatherData;
import org.nachtvaohal.util.HibernateUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


public class WeatherDataDAO {

    @PersistenceContext
    Session session;


    public void save(WeatherData weatherData) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(weatherData);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

//    public List<WeatherData> getWeatherData() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("from WeatherData", WeatherData.class).list();
//        }
//    }
}