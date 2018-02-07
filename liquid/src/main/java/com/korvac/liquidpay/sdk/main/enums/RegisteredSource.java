package com.korvac.liquidpay.sdk.main.enums;

/**
 * Allowed registered sources
 *
 * Created by sohail on 6/16/2017.
 */

public class RegisteredSource {

    public static final String NONE = "";

    public static boolean isValid(String source) {

        if (NONE.equals(source)) {
            return true;
        }


        return false;
    }
}
