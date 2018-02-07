package com.korvac.liquidpay.sdk.main.enums;

/**
 * LiquidPay accepted card types
 * <p>
 * Created by sohail on 6/16/2017.
 */

public class CardType {

    public static final String CREDIT_CARD = "credit";
    public static final String DEBIT_CARD = "debit";
    public static final String OTHER = "other";

    private static final String[] allowedTypes = {CREDIT_CARD, DEBIT_CARD, OTHER};


    public static boolean isValid(String cardType) {

        for (String s : allowedTypes) {
            if (s.equals(cardType)) {
                return true;
            }
        }


        return false;
    }

}
