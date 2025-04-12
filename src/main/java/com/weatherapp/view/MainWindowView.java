package com.weatherapp.view;

import com.weatherapp.controller.MainWindowController;
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
    void testBtn() {

        mainWindowController.testBtnAction(yourCountry, yourCity, vacationCountry, vacationCity);
    }

}
