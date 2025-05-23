package com.weatherapp.model;

public class DailyWeatherData {
    private final int dayNumber;
    private final String date;
    private final String description;
    private final double temperature;
    private final int humidity;
    private final double windSpeed;
    private final int precipitationChance;

    public DailyWeatherData(int dayNumber, String date, String description, double temperature, int humidity, double windSpeed, int precipitationChance) {
        this.dayNumber = dayNumber;
        this.date = date;
        this.description = description;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.precipitationChance = precipitationChance;
    }

    @Override
    public String toString() {
        return String.format("Day %d - %s\nDescription: %s\nTemperature: %.1f°C\nHumidity: %d%%\nWind: %.1f km/h\nRain Chance: %d%%\n",
                dayNumber, date, description, temperature, humidity, windSpeed, precipitationChance);
    }
}
