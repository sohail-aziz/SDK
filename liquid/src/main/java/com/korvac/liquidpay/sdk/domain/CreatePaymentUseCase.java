package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponsePayment;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by aldi on 6/14/2017.
 */

public class CreatePaymentUseCase extends AuthenticatedUseCase<CreatePaymentUseCase.Params> {

    LiquidPayPrefs liquidPayPrefs;
    Repository repository;

    @Inject
    public CreatePaymentUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, final String accessToken, final Params params){

        repository.createPayment(config, accessToken, params.payee, params.serviceType, params.merchantRefNo, params.amount, new LiquidPayCallback<ResponsePayment>() {
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

    public static final class Params{
        public String payee;
        public String serviceType;
        public String merchantRefNo;
        public String amount;
        public LiquidPayCallback<ResponsePayment> callback;

        public Params(String payee, String serviceType, String merchantRefNo, String amount, LiquidPayCallback<ResponsePayment> callback) {
            this.payee = payee;
            this.serviceType = serviceType;
            this.merchantRefNo = merchantRefNo;
            this.amount = amount;
            this.callback = callback;
        }
    }
}
