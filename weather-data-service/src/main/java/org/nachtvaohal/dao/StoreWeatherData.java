package org.nachtvaohal.dao;

import org.nachtvaohal.model.WeatherDataModel;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
