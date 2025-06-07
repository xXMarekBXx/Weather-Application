package com.weatherapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CountryValidatorTest {

    @Test
    void countryIsNull(){
        // Given
        CountryValidator countryValidator = new CountryValidator();

        // When
        String input = null;
        boolean result = countryValidator.isValidCountry(input);

        // Then
        assertFalse(result, "Null input should return false");
    }

    @Test
    void countryIsEmpty(){
        // Given
        CountryValidator countryValidator = new CountryValidator();

        // When
        String input = "";
        boolean result = countryValidator.isValidCountry(input);

        // Then
        assertFalse(result, "Empty string should return false");
    }

}

