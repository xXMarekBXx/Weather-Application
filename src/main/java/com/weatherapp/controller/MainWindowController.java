package com.weatherapp.controller;

import com.weatherapp.model.CountryValidator;
import com.weatherapp.model.WeatherData;
import com.weatherapp.model.WeatherService;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainWindowController {

    private final WeatherService service = new WeatherService();

    public void textFieldCleaner(TextField textToClear) {
        String defaultText = textToClear.getText();
        textToClear.setOnMouseClicked(event -> {
            if (textToClear.getText().equals(defaultText)) {
                textToClear.clear();
            }
        });
    }

    public void showCityWeatherOnTextArea(TextField city, TextArea ta) throws Exception {
        WeatherData data = service.getWeather(city.getText());
        System.out.println(data);
        ta.setVisible(true);
        ta.setText(data.toString());
    }

    public void setAllItemsInvisible(Label incorrectLabel1, Label incorrectLabel2, TextArea... textAreas) {
        incorrectLabel1.setVisible(false);
        incorrectLabel2.setVisible(false);
        for (TextArea ta : textAreas) {
            ta.setVisible(false);
        }
    }

    public void checkWeatherBtnAction(TextField yourCountry, TextField yourCity, TextField vacationCountry, TextField vacationCity,
                                      Label incorrectLabel1, Label incorrectLabel2, TextArea currentLocationTA, TextArea destinyLocationTA) {

        setAllItemsInvisible(incorrectLabel1, incorrectLabel2, currentLocationTA, destinyLocationTA);

        if (validateWeather("Current Location", yourCity, yourCountry, incorrectLabel1)) {
            showWeather(yourCity, currentLocationTA);
        }

        if (validateWeather("Destiny Location", vacationCity, vacationCountry, incorrectLabel2)) {
            showWeather(vacationCity, destinyLocationTA);
        }

        System.out.println("-----------------------------------------");
    }

    private boolean validateWeather(String locationName, TextField city, TextField country, Label errorLabel) {
        System.out.println(locationName + ":");
        System.out.println();

        if (city.getText().isEmpty()) {
            showError(errorLabel, "Please enter " + locationName.toLowerCase() + " city", locationName + " city field empty!");
            return false;
        }

        if (!CountryValidator.isValidCountry(country.getText())) {
            showError(errorLabel, "You passed incorrect country or country code", "Incorrect country or country code for " + locationName);
            return false;
        }

        return true;
    }

    private void showWeather(TextField city, TextArea textArea) {
        try {
            showCityWeatherOnTextArea(city, textArea);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showError(Label label, String errorMessage, String consoleMessage) {
        label.setVisible(true);
        label.setText(errorMessage);
        System.out.println(consoleMessage);
    }
}
