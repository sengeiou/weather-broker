package org.nachtvaohal.remote;

import org.nachtvaohal.WeatherData;
import org.nachtvaohal.WeatherDataRemoteReceivingService;
import org.nachtvaohal.dao.StoreData;
import org.nachtvaohal.dao.StoreWeatherData;
import org.nachtvaohal.model.WeatherDataModel;
import org.springframework.remoting.caucho.HessianServiceExporter;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.logging.Logger;

@RequestScoped
public class WeatherDataRemoteReceivingServiceImpl implements WeatherDataRemoteReceivingService {

    @Inject
    private StoreData storeData;

    @Override
    public WeatherData receiveWeather(String city, LocalDate date) {
        WeatherDataModel weatherDataModel = null;
        if (storeData != null) {
            weatherDataModel = storeData.get(city, date);
        }
        WeatherData weatherData = new WeatherData(weatherDataModel.getCity(),
                weatherDataModel.getDate(),
                weatherDataModel.getLow(),
                weatherDataModel.getHigh(),
                weatherDataModel.getText());
        return weatherData;
    }
}
