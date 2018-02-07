package com.korvac.liquidpay.sdk.utils;

/**
 * Generic Logger interface
 * Created by sohail on 5/8/2017.
 */

public interface Logger {

    void d(String message, Object... args);

    void e(String message, Object... args);

    void v(String message, Object... args);

    void w(String message, Object... args);
}
