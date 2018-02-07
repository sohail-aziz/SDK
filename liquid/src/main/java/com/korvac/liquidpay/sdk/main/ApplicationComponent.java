package com.korvac.liquidpay.sdk.main;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Component connects dependencies with modules
 * <p>
 * Created by sohail on 5/9/2017.
 */


@Singleton
@Component(modules = {ApplicationModule.class})
interface ApplicationComponent {
    /**
     * this component is going to be inject in LiquidPayService
     *
     * @param target
     */
    void inject(LiquidPay target);

    void inject(LoginActivity target);


}
