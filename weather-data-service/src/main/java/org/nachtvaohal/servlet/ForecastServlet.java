package org.nachtvaohal.servlet;

import com.caucho.hessian.server.HessianServlet;
import org.nachtvaohal.WeatherData;
import org.nachtvaohal.WeatherDataRemoteReceivingService;
import org.nachtvaohal.dao.StoreData;
import org.nachtvaohal.model.WeatherDataModel;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import java.time.LocalDate;
import java.util.logging.Logger;

@WebServlet(name = "forecast", value = "/forecast")
public class ForecastServlet extends HessianServlet implements WeatherDataRemoteReceivingService {

    private final Logger LOG = Logger.getLogger(ForecastServlet.class.getName());

    @Inject
    private StoreData storeData;

    @Override
    public WeatherData receiveWeather(String city, String stringDate) {
        WeatherDataModel weatherDataModel = null;
        LocalDate date = LocalDate.parse(stringDate);
        if (storeData != null) {
            weatherDataModel = storeData.get(city, date);
        }
        WeatherData weatherData = new WeatherData(weatherDataModel.getCity(),
                weatherDataModel.getDate().toString(),
                weatherDataModel.getLow(),
                weatherDataModel.getHigh(),
                weatherDataModel.getText());
        LOG.info("weather information received");
        return weatherData;
    }
}
