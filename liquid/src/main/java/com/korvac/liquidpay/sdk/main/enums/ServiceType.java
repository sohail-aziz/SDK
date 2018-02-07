package com.korvac.liquidpay.sdk.main.enums;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * LiquidPay payment service Types
 * <p>
 * Created by sohail on 6/14/2017.
 */

@Singleton
public final class ServiceType {
    public static final String SCAN_PAY = "SP";
    public static final String PAY_AT_COUNTER = "PAC";
    public static final String PAY_AT_TABLE = "PAT";

    private static final String[] allowedTypes = {SCAN_PAY, PAY_AT_COUNTER, PAY_AT_TABLE};

    @Inject
    public ServiceType() {
    }

    public static boolean isValid(String serviceType) {

        for (String s : allowedTypes) {
            if (s.equals(serviceType)) {
                return true;
            }
        }

        return false;

    }


}
