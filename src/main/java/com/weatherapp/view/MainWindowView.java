package com.weatherapp.view;

import com.weatherapp.controller.MainWindowController;
import com.weatherapp.model.WeatherData;
import com.weatherapp.model.WeatherService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainWindowView {

    @FXML
    private TextField yourCountry;

    @FXML
    private TextField yourCity;

    @FXML
    private TextField vacationCountry;

    @FXML
    private TextField vacationCity;

    MainWindowController mainWindowController;

    @FXML
    public void initialize() {

        mainWindowController = new MainWindowController();

        mainWindowController.textFieldCleaner(yourCountry);
        mainWindowController.textFieldCleaner(yourCity);
        mainWindowController.textFieldCleaner(vacationCountry);
        mainWindowController.textFieldCleaner(vacationCity);
    }

    @FXML
    void checkWeatherBtn() {
        mainWindowController.checkWeatherBtnAction(yourCountry, yourCity, vacationCountry, vacationCity);
    }

}
