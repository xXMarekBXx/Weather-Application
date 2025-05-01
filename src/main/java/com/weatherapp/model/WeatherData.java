package com.weatherapp.model;

public class WeatherData {
    private String countryName;
    private String cityName;
    private String description;
    private double temperature;
    private int humidity;
    private double windSpeed;
    private int precipitationChance;

    public WeatherData(String countryName, String cityName, String description,
                       double temperature, int humidity, double windSpeed, int precipitationChance) {
        this.countryName = countryName;
        this.cityName = cityName;
        this.description = description;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.precipitationChance = precipitationChance;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDescription() {
        return description;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getPrecipitationChance() {
        return precipitationChance;
    }

    @Override
    public String toString() {
        return "Weather in " + countryName + ", " + cityName +
                ": \ndescription: " + description +
                ", \ntemperature: " + temperature + "°C" +
                ", \nair humidity: " + humidity + "%" +
                ", \nwind: " + String.format("%.1f", windSpeed) + " km/h" +
                ", \nrain chances: " + precipitationChance + "%";
    }
}
