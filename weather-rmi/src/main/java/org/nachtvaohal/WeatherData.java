package org.nachtvaohal;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Класс для передачи данных через remote-proxy
 */
public class WeatherData implements Serializable {
    private String city;
    private LocalDate date;
    private int low;
    private int high;
    private String text;

    public WeatherData(String city, LocalDate date, int low, int high, String text) {
        this.city = city;
        this.date = date;
        this.low = low;
        this.high = high;
        this.text = text;
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
