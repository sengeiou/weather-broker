package org.nachtvaohal.remote;

import org.nachtvaohal.WeatherData;
import org.nachtvaohal.WeatherDataRemoteReceivingService;
import org.nachtvaohal.dao.StoreWeatherData;

import java.time.LocalDate;
import java.util.logging.Logger;

public class WeatherDataRemoteReceivingServiceImpl implements WeatherDataRemoteReceivingService {
    private static final Logger LOG = Logger.getLogger(WeatherDataRemoteReceivingServiceImpl.class.getName());
    @Override
    public WeatherData receiveWeather(String city, LocalDate date) {
        LOG.info("weather information received");
        return new WeatherData(city, date);
    }
}
