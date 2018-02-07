package com.korvac.liquidpay.sdk.exception;

import com.korvac.liquidpay.sdk.data.response.Error;

import java.util.List;

/**
 * LiquidPay exception for all exceptions including business exceptions
 * check error list for detailed description
 * <p>
 * Created by sohail on 5/16/2017.
 */

public class LiquidPayException extends Exception {

    private List<Error> errorList;

    public LiquidPayException(List<Error> errors, String message) {
        super(message);
        this.errorList = errors;
    }

    public List<Error> getErrorList() {
        return errorList;
    }


}
