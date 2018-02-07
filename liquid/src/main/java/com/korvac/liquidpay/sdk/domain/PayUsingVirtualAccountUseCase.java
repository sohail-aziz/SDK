package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponsePayment;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by aldi on 6/15/2017.
 */

public class PayUsingVirtualAccountUseCase extends AuthenticatedUseCase<PayUsingVirtualAccountUseCase.Params> {

    LiquidPayPrefs liquidPayPrefs;
    Repository repository;

    @Inject
    public PayUsingVirtualAccountUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config,String accessToken, final Params params) {

        repository.payUsingVirtualAccount(config, accessToken, params.paymentId, params.paymentCardId, new LiquidPayCallback<ResponsePayment>() {
            @Override
            public void onSuccess(ResponsePayment response) {
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

    public static final class Params {
        public String paymentId;
        public String paymentCardId;
        public LiquidPayCallback<ResponsePayment> callback;

        public Params(String paymentId, String paymentCardId, LiquidPayCallback<ResponsePayment> callback) {
            this.paymentId = paymentId;
            this.paymentCardId = paymentCardId;
            this.callback = callback;
        }
    }

}
