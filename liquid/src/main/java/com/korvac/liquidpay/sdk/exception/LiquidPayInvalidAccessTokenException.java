package com.korvac.liquidpay.sdk.exception;

/**
 * Exception for invalid session, should be thrown when access token cannot be refreshed
 * <p>
 * Created by sohail on 6/9/2017.
 */

public class LiquidPayInvalidAccessTokenException extends RuntimeException {

    public LiquidPayInvalidAccessTokenException() {
    }

    public LiquidPayInvalidAccessTokenException(String message) {
        super(message);
    }

    public LiquidPayInvalidAccessTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
