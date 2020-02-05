package org.nachtvaohal;

import java.time.LocalDate;

public class WeatherData {
    private String city;
    private LocalDate date;

    public WeatherData(String city, LocalDate date) {
        this.city = city;
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
