package org.nachtvaohal.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class StoreDataFactory {

    @PersistenceContext(unitName = "persistence-unit")
    private EntityManager em;

    @Produces
    public StoreData createStoreData() {
        return new StoreWeatherData(em);
    }

}
