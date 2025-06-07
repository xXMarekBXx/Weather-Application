package com.weatherapp.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.*;

public class WeatherServiceTest {


    private String buildTestUrl(String baseUrl, String apiKey, String city) {
        return baseUrl + "?q=" + city + "&appid=" + apiKey + "&units=metric&lang=en";
    }

    @Test
    void buildUrlShouldReturnProperString() {
        // Given
        String TEST_API_KEY = "testApiKey";
        String TEST_BASE_URL = "testBaseUrl";
        String testCity = "testCity";

        // When
        String result = buildTestUrl(TEST_BASE_URL, TEST_API_KEY, testCity);

        // Then
        String expected = TEST_BASE_URL + "?q=" + testCity + "&appid=" + TEST_API_KEY + "&units=metric&lang=en";

        assertEquals(expected, result, "URL should be constructed properly");
    }

    @Test
    void buildUrlShouldReturnProperStringWhenLongCityName() {
        // Given
        String TEST_API_KEY = "testApiKey";
        String TEST_BASE_URL = "testBaseUrl";
        String testCity = "SuperLongCityNameThatNeverEnds";

        // When
        String result = buildTestUrl(TEST_BASE_URL, TEST_API_KEY, testCity);

        // Then
        String expected = TEST_BASE_URL + "?q=" + testCity + "&appid=" + TEST_API_KEY + "&units=metric&lang=en";

        assertEquals(expected, result, "URL should be constructed properly");
    }

    @Test
    void buildUrlShouldReturnProperStringWhenUnexpectedSign() {
        // Given
        String TEST_API_KEY = "testApiKey";
        String TEST_BASE_URL = "testBaseUrl";
        String testCity = "ęśążźćã";

        // When
        String result = buildTestUrl(TEST_BASE_URL, TEST_API_KEY, testCity);

        // Then
        String expected = TEST_BASE_URL + "?q=" + testCity + "&appid=" + TEST_API_KEY + "&units=metric&lang=en";

        assertEquals(expected, result, "URL should be constructed properly");
    }

    @Test
    void openConnectionShouldReturnHttpURLConnection() throws Exception {
        // Given
        String testUrl = "https://example.com";

        // When
        HttpURLConnection connection = WeatherService.openConnection(testUrl);

        // Then
        assertNotNull(connection, "Connection should not be null");
        assertEquals("GET", connection.getRequestMethod(), "Request method should be GET");
    }

    @Test
    void openConnectionShouldHandleInvalidURL() {
        // Given
        String testUrl = "ftp://invalid.com";

        // When & Then
        assertThrows(MalformedURLException.class, () -> WeatherService.openConnection(testUrl),
                "Should throw MalformedURLException for invalid URL");
    }

    @Test
    void openConnectionShouldHandleNullURL() {
        // Given
        String testUrl = null;

        // When & Then
        assertThrows(MalformedURLException.class, () -> WeatherService.openConnection(testUrl),
                "Should throw MalformedURLException for null URL");
    }

    private String readResponseFromStream(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line).append("\n");
            }
            return responseBuilder.toString().trim();
        }
    }

    @Test
    void readResponseShouldWorkCorrectly() throws Exception {
        // Given
        String testResponse = "testLine1\ntestLine2\ntestLine3";
        InputStream inputStream = new ByteArrayInputStream(testResponse.getBytes());

        // When
        String result = readResponseFromStream(inputStream);

        // Then
        assertEquals(testResponse, result, "Response should match expected content");
    }

    @Test
    void readResponseShouldHandleNullInput() throws Exception {
        // Given
        InputStream inputStream = null;

        // When
        String result = readResponseFromStream(inputStream);

        // Then
        assertNull(result, "Response should be null when input stream is null");
    }
}
