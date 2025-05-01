package com.weatherapp.controller;

import com.weatherapp.model.CountryValidator;
import com.weatherapp.model.WeatherData;
import com.weatherapp.model.WeatherService;
import javafx.scene.control.Label;
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

    public void checkWeatherBtnAction(TextField yourCountry, TextField yourCity, TextField vacationCountry, TextField vacationCity, Label incorrectLabel1, Label incorrectLabel2){

        incorrectLabel1.setVisible(false);
        incorrectLabel2.setVisible(false);

        //Current Location
        System.out.println("Current Location:");
        System.out.println();

        if(CountryValidator.isValidCountry(yourCountry.getText())){
            try {
                WeatherService service = new WeatherService();
                WeatherData data = service.getWeather(yourCity.getText());
                System.out.println(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            incorrectLabel1.setVisible(true);
            incorrectLabel1.setText("You passed incorrect country or country code");
            System.out.println("You passed incorrect country or country code");
        }

        System.out.println();

        //Destiny Location
        System.out.println("Destiny Location:");
        System.out.println();

        if(CountryValidator.isValidCountry(vacationCountry.getText())){
            try {
                WeatherService service = new WeatherService();
                WeatherData data = service.getWeather(vacationCity.getText());
                System.out.println(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            incorrectLabel2.setVisible(true);
            incorrectLabel2.setText("You passed incorrect country or country code");
            System.out.println("You passed incorrect country or country code");
        }

        System.out.println();
        System.out.println("-----------------------------------------");
    }
}
