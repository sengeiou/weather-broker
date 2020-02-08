package org.nachtvaohal.dao;

import org.nachtvaohal.model.WeatherDataModel;
import org.springframework.stereotype.Repository;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.logging.Logger;

@RequestScoped
public class StoreWeatherData implements StoreData {
    private static final Logger LOG = Logger.getLogger(StoreWeatherData.class.getName());

    @PersistenceContext(unitName = "persistence-unit")
    private EntityManager em;

    @Override
    public void save(WeatherDataModel weatherDataModel) {
        em.persist(weatherDataModel);
        LOG.info("successfully stored");
    }

    @Override
    public WeatherDataModel get(String cityName, LocalDate localDate) {
        Query query = em.createQuery("SELECT wdm FROM WeatherDataModel wdm WHERE wdm.city=:city AND wdm.date=:date");
        query.setParameter("date", localDate);
        query.setParameter("city", cityName);
        WeatherDataModel weatherDataModel = (WeatherDataModel) query.getSingleResult();
        LOG.info("successfully sent through proxy");
        return weatherDataModel;
    }


}
