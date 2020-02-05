package org.nachtvaohal.dao;

import org.nachtvaohal.model.WeatherDataModel;

import java.time.LocalDate;

public interface StoreData{
    void save(WeatherDataModel weatherDataModel);
    WeatherDataModel get(LocalDate localDate, String cityName);
}
