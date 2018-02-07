package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.main.LiquidPayConfig;

/**
 * Created by sohail on 6/13/2017.
 */

public abstract class BaseUseCase<Params> {

    abstract void callRealAPI(LiquidPayConfig config, final Params params);

    abstract void error(Throwable throwable, String message, final Params params);


    public void execute(final LiquidPayConfig config, final Params params) {
        callRealAPI(config, params);
    }
}
