package com.korvac.liquidpay.sdk.model;

/**
 * Created by sohail on 5/22/2017.
 */

public class ValidStatus {

    public final boolean isValid;
    public final String description;

    public ValidStatus(boolean isValid, String description) {
        this.isValid = isValid;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ValidStatus{" +
                "isValid=" + isValid +
                ", description='" + description + '\'' +
                '}';
    }
}
