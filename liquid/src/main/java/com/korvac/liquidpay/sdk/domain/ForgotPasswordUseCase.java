package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseForgotPassword;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by Aldi on 11/7/2017.
 */

public class ForgotPasswordUseCase extends BaseUseCase<ForgotPasswordUseCase.Params> {

    private final LiquidPayPrefs liquidPayPrefs;
    private final Repository repository;

    @Inject
    public ForgotPasswordUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {

        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }


    @Override
    void callRealAPI(LiquidPayConfig config, final Params params) {
        repository.forgotPassword(config, params.email, new LiquidPayCallback<ResponseForgotPassword>() {
            @Override
            public void onSuccess(ResponseForgotPassword response) {
                params.callback.onSuccess(response);
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                params.callback.onError(throwable, errorMessage);
            }
        });
    }

    @Override
    void error(Throwable throwable, String message, Params params) {

    }

    public final static class Params {
        String email;
        LiquidPayCallback<ResponseForgotPassword> callback;

        public Params(String email, LiquidPayCallback<ResponseForgotPassword> callback) {
            this.email = email;
            this.callback = callback;
        }
    }
}
