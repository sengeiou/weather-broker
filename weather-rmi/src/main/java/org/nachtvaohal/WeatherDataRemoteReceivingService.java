package org.nachtvaohal;

import java.time.LocalDate;

public interface WeatherDataRemoteReceivingService {

    WeatherData receiveWeather(String city, LocalDate date);
}
