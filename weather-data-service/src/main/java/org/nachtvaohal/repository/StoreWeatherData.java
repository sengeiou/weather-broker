package org.nachtvaohal.repository;

import javax.enterprise.context.RequestScoped;
import java.util.logging.Logger;

@RequestScoped
public class StoreWeatherData implements StoreData {
    private static final Logger LOG = Logger.getLogger(StoreWeatherData.class.getName());

    @Override
    public void save() {
        LOG.info("successfully stored");
    }
}
