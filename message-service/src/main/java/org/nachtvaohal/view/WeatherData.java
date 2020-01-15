package org.nachtvaohal.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherData implements MessageModel {
    @JsonProperty("location")
    private Location location;
    @JsonProperty("forecasts")
    private List<Forecast> forecasts;

    public WeatherData() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

}
