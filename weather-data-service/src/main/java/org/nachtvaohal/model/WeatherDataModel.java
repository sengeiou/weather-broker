package org.nachtvaohal.model;

import javax.persistence.*;

@Entity
@Table(name = "weather_data")
public class WeatherDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;

    public WeatherDataModel() {
    }

    public WeatherDataModel(String city) {
        this.city = city;
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
}
