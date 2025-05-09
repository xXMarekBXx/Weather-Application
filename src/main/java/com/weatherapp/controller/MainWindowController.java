package com.weatherapp.controller;

import com.weatherapp.model.CountryValidator;
import com.weatherapp.model.WeatherData;
import com.weatherapp.model.WeatherService;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainWindowController {

    WeatherService service = new WeatherService();

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

    public void setAllItemsInvisible(Label incorrectLabel1,
                                     Label incorrectLabel2,
                                     TextArea currentLocationDay1TA,
                                     TextArea destinyLocationDay1TA){

        incorrectLabel1.setVisible(false);
        incorrectLabel2.setVisible(false);
        currentLocationDay1TA.setVisible(false);
        destinyLocationDay1TA.setVisible(false);
    }

    public void checkWeatherBtnAction(TextField yourCountry,
                                      TextField yourCity,
                                      TextField vacationCountry,
                                      TextField vacationCity,
                                      Label incorrectLabel1,
                                      Label incorrectLabel2,
                                      TextArea currentLocationDay1TA,
                                      TextArea destinyLocationDay1TA){

        setAllItemsInvisible(incorrectLabel1, incorrectLabel2, currentLocationDay1TA, destinyLocationDay1TA);

        //Current Location
        System.out.println("Current Location:");
        System.out.println();

        if(yourCity.getText().isEmpty()){
            incorrectLabel1.setVisible(true);
            incorrectLabel1.setText("Please enter Your current city");
            System.out.println("Current city filed empty!");
        }else if(CountryValidator.isValidCountry(yourCountry.getText())){
            try {
                showCityWeatherOnTextArea(yourCity, currentLocationDay1TA);
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

        if(vacationCity.getText().isEmpty()){
            incorrectLabel2.setVisible(true);
            incorrectLabel2.setText("Please enter Your vacation city");
            System.out.println("Vacation city filed empty!");
        }else if(CountryValidator.isValidCountry(vacationCountry.getText())){
            try {
                showCityWeatherOnTextArea(vacationCity, destinyLocationDay1TA);
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
