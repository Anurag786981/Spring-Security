package com.example.SpringSecurity.SpringSecurity.service;

import com.example.SpringSecurity.SpringSecurity.entity.Weather;
import com.example.SpringSecurity.SpringSecurity.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository){this.weatherRepository=weatherRepository;}

    public String getWeatherByCity(String city){
        System.out.println("Fetching data from DB for city:"+ city);

        Optional<Weather> weather = weatherRepository.findByCity(city);
        return weather.map(Weather::getForecast).orElse("Weather data not available");
    }

}
