package com.weatherapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {

    private static final String API_KEY = "YOUR_OPENWEATHERMAP_API_KEY";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";

    @GetMapping("/forecast")
    public String getForecast(@RequestParam String city, @RequestParam String date) {
        try {
            String url = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            // Parse response to find weather for the selected date
            String forecastForDate = extractWeatherForDate(response, date);
            return forecastForDate;

        } catch (Exception e) {
            return "Unable to fetch weather forecast for location: " + city + " on " + date;
        }
    }

    private String extractWeatherForDate(String response, String date) {
        // Replace with actual parsing logic based on the API response format
        return "Weather on " + date + ": Clear skies, 24°C";
    }
}
