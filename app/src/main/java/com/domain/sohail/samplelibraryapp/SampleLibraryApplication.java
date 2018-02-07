package com.domain.sohail.samplelibraryapp;

import android.app.Application;

import com.korvac.liquidpay.sdk.main.LiquidPay;

import timber.log.Timber;

/**
 * Created by sohail on 5/8/2017.
 */

public class SampleLibraryApplication extends Application {

    private static final String API_KEY = "K16ZaL8GIaJ6IejyTYGaFZbohgb3si1J";
    public static final String API_SECRET = "c3b268862bb6af82";


    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            //  Timber.plant(new CrashReportingTree());
        }

        LiquidPay.getInstance().init(this, API_KEY, API_SECRET, "");
    }
}
