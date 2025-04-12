package com.weatherapp.controller;

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

    public void testBtnAction(TextField yourCountry, TextField yourCity, TextField vacationCountry, TextField vacationCity ){

        //Current Location
        String yourCountryTextFieldInput = yourCountry.getText();
        String yourCityTextFieldInput = yourCity.getText();

        //Destiny Location
        String vacationCountryTextFieldInput = vacationCountry.getText();
        String vacationCityTextFieldInput = vacationCity.getText();

        //Current Location
        System.out.println("Current Country: " + yourCountryTextFieldInput + ", Your City: " + yourCityTextFieldInput);
        //Destiny Location
        System.out.println("Destiny Country: " + vacationCountryTextFieldInput + ", Destiny City: " + vacationCityTextFieldInput);

        System.out.println("-----------------------------------------");
    }
}
