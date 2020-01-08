package org.nachtvaohal.repository;

import java.util.logging.Logger;

public class StoreWeatherData implements StoreData {
    private static final Logger LOG = Logger.getLogger(StoreWeatherData.class.getName());

    @Override
    public void saveForecastInDatabase() {
        LOG.info("successfully stored");
    }
}
