package org.nachtvaohal.weather_ui.controller;

import org.nachtvaohal.WeatherData;
import org.nachtvaohal.WeatherDataRemoteReceivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.logging.Logger;

@Controller
public class WeatherDataController {

    private static final Logger LOG = Logger.getLogger(WeatherDataController.class.getName());
    private WeatherDataRemoteReceivingService service;

    @Autowired
    public WeatherDataController(WeatherDataRemoteReceivingService service) {
        this.service = service;
    }

    @GetMapping("/receiveWeather")
    public String receiveWeather(@RequestParam("city") String city,
                                 @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                 Model model) {
        // Потому что hessian бросает SOE при сериализации/десериализации LocalDate
        String stringDate = date.toString();
        WeatherData weatherData = service.receiveWeather(city, stringDate);
        model.addAttribute("city", weatherData.getCity());
        model.addAttribute("date", weatherData.getDate());
        model.addAttribute("low", weatherData.getLow());
        model.addAttribute("high", weatherData.getHigh());
        model.addAttribute("text", weatherData.getText());
        return "weather";
    }


}
