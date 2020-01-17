package org.nachtvaohal.dao;

import org.nachtvaohal.view.WeatherData;

public interface DataTransformer {
    void transformToEntity (WeatherData weatherData);
}
