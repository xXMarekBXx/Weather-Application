package com.weatherapp.model;

import org.json.JSONObject;

public class WeatherDataFactory {

    public static WeatherData fromJson(JSONObject json) {
        JSONObject firstForecast = json.getJSONArray("list").getJSONObject(0);

        JSONObject main = firstForecast.getJSONObject("main");
        JSONObject wind = firstForecast.getJSONObject("wind");
        JSONObject rain = firstForecast.optJSONObject("rain");

        String country = json.getJSONObject("city").getString("country");
        String city = json.getJSONObject("city").getString("name");
        String description = firstForecast.getJSONArray("weather").getJSONObject(0).getString("description");
        double temp = main.getDouble("temp");
        int humidity = main.getInt("humidity");
        double windSpeedMs = wind.getDouble("speed");
        double windSpeedKmh = windSpeedMs * 3.6;
        int precipitationChance = 0;
        if (rain != null && rain.has("3h")) {
            double rainVolume = rain.getDouble("3h");
            precipitationChance = Math.min((int)(rainVolume * 40), 100);
        }

        return new WeatherData(
                country,
                city,
                description,
                temp,
                humidity,
                windSpeedKmh,
                precipitationChance
        );
    }
}
