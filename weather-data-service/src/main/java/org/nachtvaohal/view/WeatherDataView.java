package org.nachtvaohal.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherDataView {
    @JsonProperty("location")
    private LocationView locationView;
    @JsonProperty("forecasts")
    private List<ForecastView> forecastViews;

    public WeatherDataView() {
    }

    public LocationView getLocationView() {
        return locationView;
    }

    public void setLocationView(LocationView locationView) {
        this.locationView = locationView;
    }

    public List<ForecastView> getForecastViews() {
        return forecastViews;
    }

    public void setForecastViews(List<ForecastView> forecastViews) {
        this.forecastViews = forecastViews;
    }
}
