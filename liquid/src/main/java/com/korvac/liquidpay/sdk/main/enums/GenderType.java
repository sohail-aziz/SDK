package com.korvac.liquidpay.sdk.main.enums;

/**
 * Created by sohail on 6/15/2017.
 */

public class GenderType {

    public static final String MALE = "M";
    public static final String FEMALE = "F";

    public static boolean isValid(String gender) {

        if (MALE.equals(gender) || FEMALE.equals(gender)) {
            return true;
        } else {
            return false;
        }
    }
}
