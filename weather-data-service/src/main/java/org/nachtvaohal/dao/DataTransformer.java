package org.nachtvaohal.dao;

import org.nachtvaohal.view.WeatherDataView;

public interface DataTransformer {
    void transformToEntity (WeatherDataView weatherData);
}
