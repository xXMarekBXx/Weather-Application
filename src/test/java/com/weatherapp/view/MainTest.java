package com.weatherapp.view;

import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class MainTest {

    @BeforeAll
    static void initJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    @Test
    void fileShouldLoadCorrectly() throws IOException {

        // Given
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/MainWindow.fxml"));

        // When
        Parent root = fxmlLoader.load();

        // Then
        assertNotNull(root, "FXML file should be loaded successfully");
    }
}
