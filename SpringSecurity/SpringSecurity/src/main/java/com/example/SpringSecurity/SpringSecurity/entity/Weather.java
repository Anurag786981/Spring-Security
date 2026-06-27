package com.example.SpringSecurity.SpringSecurity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="weather")
public class Weather {

    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String forecast;

}
