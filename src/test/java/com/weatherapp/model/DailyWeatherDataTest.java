package com.weatherapp.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DailyWeatherDataTest {

    @Test
    void toStringMethodShouldReturnFormattedWeatherData() {
        // Given
        DailyWeatherData weatherData = new DailyWeatherData(
                3, "2025-06-07", "Sunny", 28.5, 60, 15.2, 10);

        // When
        String result = weatherData.toString().trim();

        // Then
        String expected = """
        Day 3 - 2025-06-07
        Description: Sunny
        Temperature: 28,5°C
        Humidity: 60%
        Wind: 15,2 km/h
        Rain Chance: 10%
        """.trim();

        assertEquals(expected, result, "toString() should return correctly formatted weather data");
    }
}