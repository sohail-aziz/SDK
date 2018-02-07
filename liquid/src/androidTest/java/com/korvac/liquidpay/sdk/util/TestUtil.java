package com.korvac.liquidpay.sdk.util;

import java.util.Random;

/**
 * Created by Automation on 1/6/17.
 */

public class TestUtil {

    public static String generateRandomNumber(){
        Random rand = new Random();
        int  n = rand.nextInt(9000000) + 80000000;
        return String.valueOf(n);
    }
}
