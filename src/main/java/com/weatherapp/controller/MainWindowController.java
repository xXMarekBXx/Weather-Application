package com.weatherapp.controller;

import com.weatherapp.model.WeatherData;
import com.weatherapp.model.WeatherService;
import javafx.scene.control.TextField;

public class MainWindowController {

    public void textFieldCleaner(TextField textToClear) {

        String defaultText = textToClear.getText();

        textToClear.setOnMouseClicked(event -> {

            if (textToClear.getText().equals(defaultText)) {
                textToClear.clear();
            }
        });
    }

    public void checkWeatherBtnAction(TextField yourCountry, TextField yourCity, TextField vacationCountry, TextField vacationCity ){

        //Current Location
        System.out.println("Current Location:");
        System.out.println();
        String yourCountryTextFieldInput = yourCountry.getText();

        try {
            WeatherService service = new WeatherService();
            WeatherData data = service.getWeather(yourCity.getText());
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();

        //Destiny Location
        System.out.println("Destiny Location:");
        System.out.println();
        String vacationCountryTextFieldInput = vacationCountry.getText();

        try {
            WeatherService service = new WeatherService();
            WeatherData data = service.getWeather(vacationCity.getText());
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("-----------------------------------------");
    }
}
