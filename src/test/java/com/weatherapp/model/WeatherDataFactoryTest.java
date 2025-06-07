package com.weatherapp.model;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataFactoryTest {

    @Test
    void formatWeatherForecastMethodShouldReturnCorrectlyFormattedOutputWhenListEmpty() {
        // Given
        List<DailyWeatherData> forecastList = List.of();

        // When
        String result = WeatherDataFactory.formatWeatherForecast(forecastList).trim();

        // Then
        String expected = """
        Weather Forecast:
        ---------------------
        """.trim();

        assertEquals(expected, result, "Formatted forecast should match expected output");
    }

    @Test
    void formatWeatherForecastMethodShouldReturnCorrectlyFormattedOutputWhenOnlyOneDayInList() {
        // Given
        List<DailyWeatherData> forecastList = List.of(
                new DailyWeatherData(1, "2025-06-07", "Sunny", 28.5, 60, 15.2, 10)
        );

        // When
        String result = WeatherDataFactory.formatWeatherForecast(forecastList).trim();

        // Then
        String expected = """
                Weather Forecast:
                ---------------------
                Day 1 - 2025-06-07
                Description: Sunny
                Temperature: 28,5°C
                Humidity: 60%
                Wind: 15,2 km/h
                Rain Chance: 10%
                
                ---------------------
                """.trim();

        assertEquals(expected, result, "Formatted forecast should match expected output");
    }

    @Test
    void formatWeatherForecastMethodShouldReturnCorrectlyFormattedOutputWhenTwoDaysInList() {
        // Given
        List<DailyWeatherData> forecastList = List.of(
                new DailyWeatherData(1, "2025-06-07", "Sunny", 28.5, 60, 15.2, 10),
                new DailyWeatherData(2, "2025-06-08", "Cloudy", 25.5, 50, 10.2, 1)
        );

        // When
        String result = WeatherDataFactory.formatWeatherForecast(forecastList).trim();

        // Then
        String expected = """
                Weather Forecast:
                ---------------------
                Day 1 - 2025-06-07
                Description: Sunny
                Temperature: 28,5°C
                Humidity: 60%
                Wind: 15,2 km/h
                Rain Chance: 10%
                
                ---------------------                
                Day 2 - 2025-06-08
                Description: Cloudy
                Temperature: 25,5°C
                Humidity: 50%
                Wind: 10,2 km/h
                Rain Chance: 1%
                
                ---------------------
                """.trim();

        assertEquals(expected, result, "Formatted forecast should match expected output");
    }

    @Test
    void formatWeatherForecastMethodShouldReturnCorrectlyFormattedOutputWhenFiveDaysInList() {
        // Given
        List<DailyWeatherData> forecastList = List.of(
                new DailyWeatherData(1, "2025-06-07", "Sunny", 0, 0, 0, 0),
                new DailyWeatherData(2, "2025-06-08", "Cloudy", 21.5, 20, 11.2, 1),
                new DailyWeatherData(3, "2025-06-09", "Raining", 22.5, 30, 12.2, 5),
                new DailyWeatherData(4, "2025-06-10", "Foggy", 23.5, 40, 13.2, 10),
                new DailyWeatherData(5, "2025-06-11", "Windy", 24.5, 50, 14.2, 100)

        );

        // When
        String result = WeatherDataFactory.formatWeatherForecast(forecastList).trim();

        // Then
        String expected = """
        Weather Forecast:
        ---------------------
        Day 1 - 2025-06-07
        Description: Sunny
        Temperature: 0,0°C
        Humidity: 0%
        Wind: 0,0 km/h
        Rain Chance: 0%
        
        ---------------------                
        Day 2 - 2025-06-08
        Description: Cloudy
        Temperature: 21,5°C
        Humidity: 20%
        Wind: 11,2 km/h
        Rain Chance: 1%
        
        ---------------------
        Day 3 - 2025-06-09
        Description: Raining
        Temperature: 22,5°C
        Humidity: 30%
        Wind: 12,2 km/h
        Rain Chance: 5%
        
        ---------------------
        Day 4 - 2025-06-10
        Description: Foggy
        Temperature: 23,5°C
        Humidity: 40%
        Wind: 13,2 km/h
        Rain Chance: 10%
        
        ---------------------
        Day 5 - 2025-06-11
        Description: Windy
        Temperature: 24,5°C
        Humidity: 50%
        Wind: 14,2 km/h
        Rain Chance: 100%
        
        ---------------------
        """.trim();

        assertEquals(expected, result, "Formatted forecast should match expected output");
    }

    @Test
    void extractPrecipitationChanceShouldReturnZeroWhenRainIsNull() {
        // Given
        JSONObject forecast = new JSONObject();

        // When
        int result = WeatherDataFactory.extractPrecipitationChance(forecast);

        // Then
        assertEquals(0, result, "Should return 0 when 'rain' object is missing");
    }

    @Test
    void extractPrecipitationChanceShouldCalculateProperly() {
        // Given
        JSONObject forecast = new JSONObject().put("rain", new JSONObject().put("3h", 2.0));

        // When
        int result = WeatherDataFactory.extractPrecipitationChance(forecast);

        // Then
        assertEquals(80, result, "Should correctly calculate precipitation chance");
    }

    public static double extractWindSpeed(JSONObject dailyForecast) {
        return dailyForecast.getJSONObject("wind").getDouble("speed") * 3.6;
    }

    @Test
    void extractWindSpeedShouldReturnZeroWhenWindIsNull() {
        // Given
        JSONObject forecast = new JSONObject();

        // When
        double result = WeatherDataFactory.extractWindSpeed(forecast);

        // Then
        assertEquals(0.0, result, "Should return 0 when 'wind' object is missing");
    }

    @Test
    void extractWindSpeedShouldCalculateProperly() {
        // Given
        JSONObject forecast = new JSONObject().put("wind", new JSONObject().put("speed", 5.0));

        // When
        double result = WeatherDataFactory.extractWindSpeed(forecast);

        // Then
        assertEquals(18.0, result, "Should correctly convert m/s to km/h");
    }

    @Test
    void extractHumidityShouldReturnZeroWhenHumidityIsNull() {
        // Given
        JSONObject forecast = new JSONObject();

        // When
        int result = WeatherDataFactory.extractHumidity(forecast);

        // Then
        assertEquals(0, result, "Should return 0 when 'humidity' object is missing");
    }

    @Test
    void extractHumidityShouldCalculateProperly() {
        // Given
        JSONObject forecast = new JSONObject().put("main", new JSONObject().put("humidity", 75));

        // When
        int result = WeatherDataFactory.extractHumidity(forecast);

        // Then
        assertEquals(75, result, "Should correctly calculate humidity");
    }

    @Test
    void extractTemperatureShouldReturnZeroWhenTemperatureIsNull() {
        // Given
        JSONObject forecast = new JSONObject();

        // When
        double result = WeatherDataFactory.extractTemperature(forecast);

        // Then
        assertEquals(0.0, result, "Should return 0 when 'temperature' object is missing");
    }

    @Test
    void extractTemperatureShouldCalculateProperly() {
        // Given
        JSONObject forecast = new JSONObject().put("main", new JSONObject().put("temp", 25.5));

        // When
        double result = WeatherDataFactory.extractTemperature(forecast);

        // Then
        assertEquals(25.5, result, "Should correctly calculate temperature");
    }

    @Test
    void extractDescriptionShouldReturnNothingWhenDescriptionIsNull() {
        // Given
        JSONObject forecast = new JSONObject();

        // When
        String result = WeatherDataFactory.extractDescription(forecast);

        // Then
        assertEquals("", result, "Should return '' when 'description' object is missing");

    }

    @Test
    void extractDescriptionShouldShowDataProperly() {
        // Given
        JSONObject forecast = new JSONObject().put("weather", new JSONArray().put(new JSONObject().put("description", "TestWeather")));

        // When
        String result = WeatherDataFactory.extractDescription(forecast);

        // Then
        assertEquals("TestWeather", result, "Should return proper description");
    }

    @Test
    void extractDateShouldReturnNothingWhenDateIsNull() {
        // Given
        JSONObject forecast = new JSONObject();

        // When
        String result = WeatherDataFactory.extractDate(forecast);

        // Then
        assertEquals("", result, "Should return '' when 'date' object is missing");

    }

    @Test
    void extractDateShouldShowDataProperly() {
        // Given
        JSONObject forecast = new JSONObject().put("dt_txt", "2025-06-07 12:00:00");

        // When
        String result = WeatherDataFactory.extractDate(forecast);

        // Then
        assertEquals("2025-06-07", result, "Should return proper date");
    }

    @Test
    void extractDailyForecastShouldShowDataProperly() {
        // Given
        int index = 0;
        int TIME_STEP = 8;
        JSONObject json = new JSONObject().put("list", new JSONArray().put(new JSONObject().put("dt_txt", "2025-06-07 12:00:00")));
        JSONObject dailyForecast = json.getJSONArray("list").getJSONObject(index);

        String date = WeatherDataFactory.extractDate(dailyForecast);
        String description = WeatherDataFactory.extractDescription(dailyForecast);
        double temp = WeatherDataFactory.extractTemperature(dailyForecast);
        int humidity = WeatherDataFactory.extractHumidity(dailyForecast);
        double windSpeedKmh = WeatherDataFactory.extractWindSpeed(dailyForecast);
        int precipitationChance = WeatherDataFactory.extractPrecipitationChance(dailyForecast);


        // When
        DailyWeatherData result = new DailyWeatherData(index / TIME_STEP + 1, date, description, temp, humidity, windSpeedKmh, precipitationChance);

        // Then
        String expected = """
        Day 1 - 2025-06-07
        Description:\s
        Temperature: 0,0°C
        Humidity: 0%
        Wind: 0,0 km/h
        Rain Chance: 0%
        """.trim();

        assertEquals(expected, result.toString().trim(), "Should return proper data");
    }

    @Test
    void extractDailyForecastShouldShowDataProperlyForSecondDay() {
        // Given
        int index = 1;
        int dayNumber = index + 1;
        JSONObject json = new JSONObject().put("list", new JSONArray()
                                          .put(new JSONObject().put("dt_txt", "2025-06-07 07:00:00"))
                                          .put(new JSONObject().put("dt_txt", "2025-06-08 08:00:00")));

        JSONObject dailyForecast = json.getJSONArray("list").getJSONObject(index);

        String date = WeatherDataFactory.extractDate(dailyForecast);
        String description = WeatherDataFactory.extractDescription(dailyForecast);
        double temp = WeatherDataFactory.extractTemperature(dailyForecast);
        int humidity = WeatherDataFactory.extractHumidity(dailyForecast);
        double windSpeedKmh = WeatherDataFactory.extractWindSpeed(dailyForecast);
        int precipitationChance = WeatherDataFactory.extractPrecipitationChance(dailyForecast);


        // When
        DailyWeatherData result = new DailyWeatherData(dayNumber, date, description, temp, humidity, windSpeedKmh, precipitationChance);

        // Then
        String expected = """
        Day 2 - 2025-06-08
        Description:\s
        Temperature: 0,0°C
        Humidity: 0%
        Wind: 0,0 km/h
        Rain Chance: 0%
        """.trim();

        assertEquals(expected, result.toString().trim(), "Should return proper data");
    }

    @Test
    void extractDailyForecastShouldShowDataProperlyForFifthDay() {
        // Given
        int index = 4;
        int dayNumber = index + 1;
        JSONObject json = new JSONObject().put("list", new JSONArray()
                .put(new JSONObject().put("dt_txt", "2025-06-07 07:00:00"))
                .put(new JSONObject().put("dt_txt", "2025-06-08 08:00:00"))
                .put(new JSONObject().put("dt_txt", "2025-06-09 09:00:00"))
                .put(new JSONObject().put("dt_txt", "2025-06-10 10:00:00"))
                .put(new JSONObject().put("dt_txt", "2025-06-11 11:00:00")));

        JSONObject dailyForecast = json.getJSONArray("list").getJSONObject(index);

        String date = WeatherDataFactory.extractDate(dailyForecast);
        String description = WeatherDataFactory.extractDescription(dailyForecast);
        double temp = WeatherDataFactory.extractTemperature(dailyForecast);
        int humidity = WeatherDataFactory.extractHumidity(dailyForecast);
        double windSpeedKmh = WeatherDataFactory.extractWindSpeed(dailyForecast);
        int precipitationChance = WeatherDataFactory.extractPrecipitationChance(dailyForecast);


        // When
        DailyWeatherData result = new DailyWeatherData(dayNumber, date, description, temp, humidity, windSpeedKmh, precipitationChance);

        // Then
        String expected = """
        Day 5 - 2025-06-11
        Description:\s
        Temperature: 0,0°C
        Humidity: 0%
        Wind: 0,0 km/h
        Rain Chance: 0%
        """.trim();

        assertEquals(expected, result.toString().trim(), "Should return proper data");
    }

    @Test
    void extractDailyForecastShouldShowDataProperlyEvenIfInvalidDate() {
        // Given
        int index = 0;
        int TIME_STEP = 8;
        JSONObject json = new JSONObject().put("list", new JSONArray().put(new JSONObject().put("dt_txt", "2025-06-07 12:00:00")));
        JSONObject dailyForecast = json.getJSONArray("list").getJSONObject(index);

        String date = "";
        String description = WeatherDataFactory.extractDescription(dailyForecast);
        double temp = WeatherDataFactory.extractTemperature(dailyForecast);
        int humidity = WeatherDataFactory.extractHumidity(dailyForecast);
        double windSpeedKmh = WeatherDataFactory.extractWindSpeed(dailyForecast);
        int precipitationChance = WeatherDataFactory.extractPrecipitationChance(dailyForecast);


        // When
        DailyWeatherData result = new DailyWeatherData(index / TIME_STEP + 1, date, description, temp, humidity, windSpeedKmh, precipitationChance);

        // Then
        String expected = """
        Day 1 -\s
        Description:\s
        Temperature: 0,0°C
        Humidity: 0%
        Wind: 0,0 km/h
        Rain Chance: 0%
        """.trim();

        assertEquals(expected, result.toString().trim(), "Should return proper data");
    }

    @Test
    void extractDailyForecastShouldAvoidIndexOutOfBoundsException() {
        // Given
        JSONObject json = new JSONObject().put("list", new JSONArray().put(new JSONObject().put("dt_txt", "2025-06-07 12:00:00")));
        int index = json.getJSONArray("list").length() - 1;
        int TIME_STEP = 8;
        JSONObject dailyForecast = json.getJSONArray("list").getJSONObject(index);

        String date = WeatherDataFactory.extractDate(dailyForecast);
        String description = WeatherDataFactory.extractDescription(dailyForecast);
        double temp = WeatherDataFactory.extractTemperature(dailyForecast);
        int humidity = WeatherDataFactory.extractHumidity(dailyForecast);
        double windSpeedKmh = WeatherDataFactory.extractWindSpeed(dailyForecast);
        int precipitationChance = WeatherDataFactory.extractPrecipitationChance(dailyForecast);


        // When
        DailyWeatherData result = new DailyWeatherData(index / TIME_STEP + 1, date, description, temp, humidity, windSpeedKmh, precipitationChance);

        // Then
        String expected = """
        Day 1 - 2025-06-07
        Description:\s
        Temperature: 0,0°C
        Humidity: 0%
        Wind: 0,0 km/h
        Rain Chance: 0%
        """.trim();

        assertEquals(expected, result.toString().trim(), "Should return proper data");
    }

    @Test
    void addingDataToForecastListShouldWork(){

        // Given
        List<DailyWeatherData> forecastList = new ArrayList<>();
        int DAYS_COUNT = 1;

        DailyWeatherData exampleData = new DailyWeatherData(1, "2025-06-07", "Sunny", 25.0, 60, 15.0, 10);

        // When
        for (int i = 0; i < DAYS_COUNT; i++) {
            forecastList.add(exampleData);
        }

        String expected = """
        Day 1 - 2025-06-07
        Description: Sunny
        Temperature: 25,0°C
        Humidity: 60%
        Wind: 15,0 km/h
        Rain Chance: 10%
        """.trim();

        // Then
        assertEquals(DAYS_COUNT, forecastList.size(), "List should contain correct number of elements");
        assertEquals(exampleData, forecastList.get(0), "First element should match example data");
        assertEquals(exampleData.toString().trim(), expected, "Data should be matched");
    }

    @Test
    void addingMultipleDataToForecastListShouldWork(){

        // Given
        List<DailyWeatherData> forecastList = new ArrayList<>();
        int DAYS_COUNT = 5;

        DailyWeatherData exampleData = new DailyWeatherData(1, "2025-06-07", "Sunny", 25.0, 60, 15.0, 10);

        // When
        for (int i = 0; i < DAYS_COUNT; i++) {
            forecastList.add(exampleData);
        }

        // Then
        assertEquals(exampleData, forecastList.get(4), "Fifth element should match example data");
    }

    @Test
    void gettingLastElementFromListShouldWork(){

        // Given
        List<DailyWeatherData> forecastList = new ArrayList<>();
        int DAYS_COUNT = 100;

        DailyWeatherData exampleData = new DailyWeatherData(1, "2025-06-07", "Sunny", 25.0, 60, 15.0, 10);

        // When
        for (int i = 0; i < DAYS_COUNT; i++) {
            forecastList.add(exampleData);
        }

        // Then
        assertEquals(exampleData, forecastList.get(forecastList.size()-1), "Last element should match example data");
        assertEquals(DAYS_COUNT, forecastList.size());
    }
}