package com.korvac.liquidpay.sdk.main;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by sohail on 6/16/2017.
 */

public class Validator {


    public static boolean isValidEmail(String email) {
        //TODO implement email regix matching
        if (email == null || email.isEmpty()) {
            return false;
        }

        return Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    public static boolean isValidNumber(String number) {
        if (number == null || number.isEmpty()) {
            return false;
        }

        return TextUtils.isDigitsOnly(number);
    }

    /**
     * Checks if amount is in valid format
     * valid format is non-negative, with exactly 2 decimal points.
     *
     * @param amount
     * @return
     */
    public static boolean isValidAmount(String amount) {
        if (amount == null || amount.isEmpty()) {
            return false;
        }

        final String parts[] = amount.split("\\.");
        if (parts.length == 2) {

            if (parts[0].length() > 0 && parts[1].length() == 2) {
                return (TextUtils.isDigitsOnly(parts[0]) &&
                        TextUtils.isDigitsOnly(parts[1]));
            } else {
                return false;
            }
        }
        return false;
    }
}
