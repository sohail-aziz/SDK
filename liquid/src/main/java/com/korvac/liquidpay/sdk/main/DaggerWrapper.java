package com.korvac.liquidpay.sdk.main;

import android.content.Context;


/**
 * Created by sohail on 5/9/2017.
 */

class DaggerWrapper {

    private static ApplicationComponent mComponent;

    public static ApplicationComponent getComponent(Context context) {
        if (mComponent == null) {
            initComponent(context);
        }
        return mComponent;
    }

    private static void initComponent(Context context) {
        mComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(context))
                .build();
    }
}
