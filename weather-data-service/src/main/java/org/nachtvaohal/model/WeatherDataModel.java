package org.nachtvaohal.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "weather_data")
public class WeatherDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "forecast_date")
    private LocalDate date;

    @Column(name = "low")
    private int low;

    @Column(name = "high")
    private int high;

    @Column(name = "text")
    private String text;

    public WeatherDataModel() {
    }

    public WeatherDataModel(String city, LocalDate date, int low, int high, String text) {
        this.city = city;
        this.date = date;
        this.low = low;
        this.high = high;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
