package com.weatherapp.model;

public class WeatherData {
    private final String description;

    public WeatherData(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
