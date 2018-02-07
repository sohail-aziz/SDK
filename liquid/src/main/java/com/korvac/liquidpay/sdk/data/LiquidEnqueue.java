package com.korvac.liquidpay.sdk.data;

import com.korvac.liquidpay.sdk.data.response.Error;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aldi on 11/8/2017.
 */

public class LiquidEnqueue<RESPONSE> implements Callback<RESPONSE> {

    private LiquidPayCallback<RESPONSE> callback;
    private ErrorUtils errorUtils;

    public LiquidEnqueue(LiquidPayCallback<RESPONSE> callback, ErrorUtils errorUtils) {
        this.callback = callback;
        this.errorUtils = errorUtils;
    }

    @Override
    public void onResponse(Call<RESPONSE> call, Response<RESPONSE> response) {
        if (response.isSuccessful()) {
            callback.onSuccess(response.body());
        } else {
            List<Error> errors = errorUtils.parseErrorList(response);
            callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
        }
    }

    @Override
    public void onFailure(Call<RESPONSE> call, Throwable t) {
        callback.onError(t, t.getMessage());
    }
}
