package com.weatherapp.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WeatherDataTest {

    @Test
    void toStringMethodShouldReturnDescription() {
        // Given
        WeatherData weatherData = new WeatherData("Cloudy");

        // When
        String result = weatherData.toString();

        // Then
        assertEquals("Cloudy", result, "toString() should return the correct description");
    }
}
