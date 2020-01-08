package org.nachtvaohal.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Forecast {
    @JsonProperty("day")
    private String day;
    @JsonProperty("date")
    private String date;
    @JsonProperty("low")
    private int low;
    @JsonProperty("high")
    private int high;
    @JsonProperty("text")
    private String text;
    @JsonProperty("code")
    private int code;

    public Forecast() {
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "day='" + day + '\'' +
                ", date='" + date + '\'' +
                ", low=" + low +
                ", high=" + high +
                ", text='" + text + '\'' +
                ", code=" + code +
                '}';
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
