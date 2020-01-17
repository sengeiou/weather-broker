package org.nachtvaohal.dao;

import org.nachtvaohal.model.WeatherDataModel;
import org.nachtvaohal.view.WeatherData;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class DataTransformerImpl implements DataTransformer {
    private static final Logger LOG = Logger.getLogger(DataTransformerImpl.class.getName());

    private StoreData storeData;

    public DataTransformerImpl() {
    }

    @Inject
    DataTransformerImpl (StoreData storeData){
        this.storeData = storeData;
    }
    @Override
    public void transformToEntity(WeatherData weatherData) {
        int forecastDaysAmount = weatherData.getForecasts().size();
        List<WeatherDataModel> weatherDataModelList = new ArrayList<>();
        for (int i = 0; i < forecastDaysAmount; i ++) {
            WeatherDataModel tempWeathDataModel = new WeatherDataModel(
                    weatherData.getLocation().getCity(),
                    transformStringToLocalDate(weatherData.getForecasts().get(i).getDate()),
                    convertFahrenheitToCelsius(weatherData.getForecasts().get(i).getLow()),
                    convertFahrenheitToCelsius(weatherData.getForecasts().get(i).getHigh()),
                    weatherData.getForecasts().get(i).getText());
            tempWeathDataModel.setDate(transformStringToLocalDate(weatherData.getForecasts().get(i).getDate()));
            weatherDataModelList.add(tempWeathDataModel);
        }
        for (WeatherDataModel wdm : weatherDataModelList) {
            storeData.save(wdm);
        }
    }

    private static LocalDate transformStringToLocalDate(String stringDate) {
        LOG.info("transforming :" + stringDate);
        long epochSeconds = Long.valueOf(stringDate);
        LocalDate date = Instant.ofEpochSecond(epochSeconds)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
        return date;
    }

    private static int convertFahrenheitToCelsius(int fahrenheitDegrees) {
        return ((fahrenheitDegrees - 32) * 5) / 9;

    }
}
