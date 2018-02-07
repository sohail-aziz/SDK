package com.korvac.liquidpay.sdk.main;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Validator Unit Tests
 * Created by sohail on 6/16/2017.
 */

public class ValidatorTest2 {

    @Test
    public void testValidAmount() {

        boolean isValid = Validator.isValidAmount("22.55");
        assertThat(isValid, is(true));
    }

    @Test
    public void testWithoutDecimal() {
        boolean isValid = Validator.isValidAmount("22");
        assertThat(isValid, is(false));
    }

    @Test
    public void testLargeDecimal() {

        boolean isValid = Validator.isValidAmount("2.222");
        assertThat(isValid, is(false));
    }

    @Test
    public void testNegativeNumber() {
        boolean isValid = Validator.isValidAmount("-2.22");
        assertThat(isValid, is(false));
    }

    @Test
    public void testWithoutLeftDigit() {

        boolean isValid = Validator.isValidAmount(".22");

        assertThat(isValid, is(false));
    }

    @Test
    public void validEmailTest() {

        boolean isValid = Validator.isValidEmail("sohail@aziz.com");

        assertThat(isValid, is(true));
    }

    @Test
    public void inValidEmailTest() {

        boolean isValid = Validator.isValidEmail("shailaziz.com");
        assertThat(isValid, is(false));
    }

    @Test
    public void validNumberTest() {

        boolean isValid = Validator.isValidNumber("123343243");
        assertThat(isValid, is(true));
    }

    @Test
    public void inValidNumberTest() {

        boolean isValid = Validator.isValidNumber("3423424jljl34234");

        assertThat(isValid, is(false));

    }
}
