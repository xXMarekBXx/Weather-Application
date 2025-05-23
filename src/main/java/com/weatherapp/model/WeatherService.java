package com.weatherapp.model;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {

    private static final String API_KEY = "19791abead51e0e73112ac0a93e44fc3";

    public WeatherData getWeather(String city) throws Exception {
        String urlString = "https://api.openweathermap.org/data/2.5/forecast?q=" +
                city + "&appid=" + API_KEY + "&units=metric&lang=en";

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }

        reader.close();
        JSONObject jsonResponse = new JSONObject(responseBuilder.toString());

        return WeatherDataFactory.fromJson(jsonResponse);
    }
}
