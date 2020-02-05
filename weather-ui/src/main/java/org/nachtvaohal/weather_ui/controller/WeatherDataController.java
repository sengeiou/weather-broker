package org.nachtvaohal.weather_ui.controller;

import org.nachtvaohal.WeatherDataRemoteReceivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class WeatherDataController {

    @Autowired
    WeatherDataRemoteReceivingService service;

    @GetMapping("/get")
    public String get() {
        if (service != null) {
            service.receiveWeather("penza", LocalDate.now());
        }
        return "weather";
    }
}
