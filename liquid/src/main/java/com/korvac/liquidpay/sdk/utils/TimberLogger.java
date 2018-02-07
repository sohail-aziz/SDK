package com.korvac.liquidpay.sdk.utils;

import timber.log.Timber;

/**
 * Created by sohail on 5/8/2017.
 */

public class TimberLogger implements Logger {
    @Override
    public void d(String message, Object... args) {
        Timber.d(message, args);
    }

    @Override
    public void e(String message, Object... args) {
        Timber.e(message, args);
    }

    @Override
    public void v(String message, Object... args) {
        Timber.v(message, args);
    }

    @Override
    public void w(String message, Object... args) {
        Timber.w(message, args);
    }
}
