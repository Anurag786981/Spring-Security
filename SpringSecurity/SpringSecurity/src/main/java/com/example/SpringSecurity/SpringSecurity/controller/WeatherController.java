package com.example.SpringSecurity.SpringSecurity.controller;

import com.example.SpringSecurity.SpringSecurity.entity.Weather;
import com.example.SpringSecurity.SpringSecurity.repository.WeatherRepository;
import com.example.SpringSecurity.SpringSecurity.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherRepository weatherRepository;

    @GetMapping
    public String getWeather(@RequestParam String city){
        return weatherService.getWeatherByCity(city);
    }
    @PostMapping
    public Weather addWeather(@RequestBody Weather weather){
        return weatherRepository.save(weather);
    }
    @GetMapping("/all")
    public List<Weather> getAllWeather(){
        return weatherRepository.findAll();
    }

    @GetMapping("/health")
    public  String healthCheck(){
        return ("Healthy");
    }
}
