package com.weatherapp.view;

import com.weatherapp.controller.MainWindowController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

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
    private Label incorrectLabelCurrentLocation;

    @FXML
    private Label incorrectLabelVacationLocation;

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
        mainWindowController.checkWeatherBtnAction(yourCountry, yourCity, vacationCountry, vacationCity, incorrectLabelCurrentLocation, incorrectLabelVacationLocation);
    }

}
