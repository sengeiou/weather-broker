package org.nachtvaohal.dao;

import org.nachtvaohal.model.WeatherDataModel;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.logging.Logger;

public class StoreWeatherData implements StoreData {
    private static final Logger LOG = Logger.getLogger(StoreWeatherData.class.getName());

    private EntityManager em;

    public StoreWeatherData(EntityManager em) {
        this.em = em;
    }

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
