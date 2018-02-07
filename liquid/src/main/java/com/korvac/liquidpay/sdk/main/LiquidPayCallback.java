package com.korvac.liquidpay.sdk.main;

/**
 * Result Callback used to return API result
 * <p>
 * All APIs should return results via this callback
 * <p>
 * Created by sohail on 5/19/2017.
 */

public interface LiquidPayCallback<T> {

    void onSuccess(T response);

    void onError(Throwable throwable, String errorMessage);
}
