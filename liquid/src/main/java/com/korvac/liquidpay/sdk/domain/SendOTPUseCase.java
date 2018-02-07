package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseSendOTP;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by aldi on 6/14/2017.
 */

public class SendOTPUseCase extends BaseUseCase<SendOTPUseCase.Params> {

    Repository repository;

    @Inject
    public SendOTPUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, final Params params) {
        repository.sendOTP(config, params.dialingCode, params.mobileNumber, new LiquidPayCallback<ResponseSendOTP>() {
            @Override
            public void onSuccess(ResponseSendOTP response) {
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
        params.callback.onError(throwable, message);
    }

    public static final class Params{
        public String dialingCode;
        public String mobileNumber;
        public LiquidPayCallback<ResponseSendOTP> callback;

        public Params(String dialingCode, String mobileNumber, LiquidPayCallback<ResponseSendOTP> callback) {
            this.dialingCode = dialingCode;
            this.mobileNumber = mobileNumber;
            this.callback = callback;
        }

    }
}
