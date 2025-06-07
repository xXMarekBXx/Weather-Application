package com.weatherapp.model;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherService {

    private static final String API_KEY = "19791abead51e0e73112ac0a93e44fc3";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";

    public WeatherData getWeather(String city) throws Exception {
        String urlString = buildUrl(city);
        String jsonResponse = fetchWeatherData(urlString);
        return parseWeatherData(jsonResponse);
    }

    private String buildUrl(String city) {
        return BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric&lang=en";
    }

    public static HttpURLConnection openConnection(String urlString) throws IOException {
        URL url = new URL(urlString);

        if (!url.getProtocol().startsWith("http")) {
            throw new MalformedURLException("Invalid protocol: " + url.getProtocol());
        }

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

    private String readResponse(HttpURLConnection connection) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            return responseBuilder.toString();
        }
    }

    private String fetchWeatherData(String urlString) throws Exception {
        HttpURLConnection connection = openConnection(urlString);
        return readResponse(connection);
    }

    public static WeatherData parseWeatherData(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        return WeatherDataFactory.fromJson(jsonObject);
    }
}
