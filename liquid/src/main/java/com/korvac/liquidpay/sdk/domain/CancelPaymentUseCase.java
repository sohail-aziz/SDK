package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseCancelPayment;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by Aldi on 11/7/2017.
 */

public class CancelPaymentUseCase extends AuthenticatedUseCase<CancelPaymentUseCase.Params> {

    LiquidPayPrefs liquidPayPrefs;
    Repository repository;

    @Inject
    public CancelPaymentUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.cancelPayment(config, accessToken, params.paymentId, new LiquidPayCallback<ResponseCancelPayment>() {
            @Override
            public void onSuccess(ResponseCancelPayment response) {
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

        public String paymentId;
        public LiquidPayCallback<ResponseCancelPayment> callback;

        public Params(String paymentId, LiquidPayCallback<ResponseCancelPayment> callback) {
            this.paymentId = paymentId;
            this.callback = callback;
        }
    }
}
