package com.weatherapp.model;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class WeatherDataFactory {

    private static final int DAYS_COUNT = 5;
    private static final int TIME_STEP = 8; // Time 24h in API

    public static WeatherData fromJson(JSONObject json) {
        List<DailyWeatherData> forecastList = new ArrayList<>();

        for (int i = 0; i < DAYS_COUNT; i++) {
            forecastList.add(extractDailyForecast(json, i * TIME_STEP));
        }

        return new WeatherData(formatWeatherForecast(forecastList));
    }

    private static DailyWeatherData extractDailyForecast(JSONObject json, int index) {
        JSONObject dailyForecast = json.getJSONArray("list").getJSONObject(index);
        JSONObject main = dailyForecast.getJSONObject("main");
        JSONObject wind = dailyForecast.getJSONObject("wind");
        JSONObject rain = dailyForecast.optJSONObject("rain");

        String date = dailyForecast.getString("dt_txt").split(" ")[0];
        String description = dailyForecast.getJSONArray("weather").getJSONObject(0).getString("description");
        double temp = main.getDouble("temp");
        int humidity = main.getInt("humidity");
        double windSpeedKmh = wind.getDouble("speed") * 3.6;
        int precipitationChance = (rain != null && rain.has("3h")) ? Math.min((int)(rain.getDouble("3h") * 40), 100) : 0;

        return new DailyWeatherData(index / TIME_STEP + 1, date, description, temp, humidity, windSpeedKmh, precipitationChance);
    }

    private static String formatWeatherForecast(List<DailyWeatherData> forecastList) {
        StringBuilder forecastBuilder = new StringBuilder();
        forecastBuilder.append("Weather Forecast:\n");
        forecastBuilder.append("---------------------\n");

        for (DailyWeatherData dailyWeather : forecastList) {
            forecastBuilder.append(dailyWeather).append("---------------------\n");
        }

        return forecastBuilder.toString();
    }
}
