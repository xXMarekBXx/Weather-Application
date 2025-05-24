package com.weatherapp.model;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class WeatherDataFactory {

    private static final int DAYS_COUNT = 5;
    private static final int TIME_STEP = 8; // The API provides a forecast every 3 hours, so we multiple it by 8 to get one full day

    public static WeatherData fromJson(JSONObject json) {
        List<DailyWeatherData> forecastList = new ArrayList<>();

        for (int i = 0; i < DAYS_COUNT; i++) {
            forecastList.add(extractDailyForecast(json, i * TIME_STEP));
        }

        return new WeatherData(formatWeatherForecast(forecastList));
    }

    private static DailyWeatherData extractDailyForecast(JSONObject json, int index) {
        JSONObject dailyForecast = json.getJSONArray("list").getJSONObject(index);

        String date = extractDate(dailyForecast);
        String description = extractDescription(dailyForecast);
        double temp = extractTemperature(dailyForecast);
        int humidity = extractHumidity(dailyForecast);
        double windSpeedKmh = extractWindSpeed(dailyForecast);
        int precipitationChance = extractPrecipitationChance(dailyForecast);

        return new DailyWeatherData(index / TIME_STEP + 1, date, description, temp, humidity, windSpeedKmh, precipitationChance);
    }

    private static String extractDate(JSONObject dailyForecast) {
        return dailyForecast.getString("dt_txt").split(" ")[0];
    }

    private static String extractDescription(JSONObject dailyForecast) {
        return dailyForecast.getJSONArray("weather").getJSONObject(0).getString("description");
    }

    private static double extractTemperature(JSONObject dailyForecast) {
        return dailyForecast.getJSONObject("main").getDouble("temp");
    }

    private static int extractHumidity(JSONObject dailyForecast) {
        return dailyForecast.getJSONObject("main").getInt("humidity");
    }

    private static double extractWindSpeed(JSONObject dailyForecast) {
        return dailyForecast.getJSONObject("wind").getDouble("speed") * 3.6;
    }

    private static int extractPrecipitationChance(JSONObject dailyForecast) {
        JSONObject rain = dailyForecast.optJSONObject("rain");
        return (rain != null && rain.has("3h")) ? Math.min((int) (rain.getDouble("3h") * 40), 100) : 0;
    }

    private static String formatWeatherForecast(List<DailyWeatherData> forecastList) {
        StringBuilder forecastBuilder = new StringBuilder();
        forecastBuilder.append("Weather Forecast:\n");
        forecastBuilder.append("---------------------\n");

        for (DailyWeatherData dailyWeather : forecastList) {
            forecastBuilder.append(dailyWeather).append("\n---------------------\n");
        }

        return forecastBuilder.toString();
    }
}
