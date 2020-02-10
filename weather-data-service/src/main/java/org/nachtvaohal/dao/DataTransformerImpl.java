package org.nachtvaohal.dao;

import org.nachtvaohal.model.WeatherDataModel;
import org.nachtvaohal.view.WeatherDataView;

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
    public void transformToEntity(WeatherDataView weatherDataView) {
        int forecastDaysAmount = weatherDataView.getForecasts().size();
        List<WeatherDataModel> weatherDataModelList = new ArrayList<>();
        for (int i = 0; i < forecastDaysAmount; i ++) {
            WeatherDataModel tempWeathDataModel = new WeatherDataModel(
                    weatherDataView.getLocation().getCity(),
                    transformStringToLocalDate(weatherDataView.getForecasts().get(i).getDate()),
                    convertFahrenheitToCelsius(weatherDataView.getForecasts().get(i).getLow()),
                    convertFahrenheitToCelsius(weatherDataView.getForecasts().get(i).getHigh()),
                    weatherDataView.getForecasts().get(i).getText());
            tempWeathDataModel.setDate(transformStringToLocalDate(weatherDataView.getForecasts().get(i).getDate()));
            weatherDataModelList.add(tempWeathDataModel);
        }
        for (WeatherDataModel wdm : weatherDataModelList) {
            storeData.save(wdm);
        }
    }

    private static LocalDate transformStringToLocalDate(String stringDate) {
        LOG.info("transforming :" + stringDate);
        long epochSeconds = Long.parseLong(stringDate);
        // todo дата трансформируется на сутки раньше
        LocalDate date = Instant.ofEpochSecond(epochSeconds)
                                .atZone(ZoneId.of("GMT+3"))
                                .toLocalDate();
        return date;
    }

    private static int convertFahrenheitToCelsius(int fahrenheitDegrees) {
        return ((fahrenheitDegrees - 32) * 5) / 9;

    }
}
