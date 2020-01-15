package org.nachtvaohal.service;

/**
 * Интерфейс получения данных с удаленного сервиса
 */
public interface DataRequest {
    // TODO: Эти исключения надо будет убрать отсюда наверно.
    String getWeatherData(String cityName) throws Exception;
}
