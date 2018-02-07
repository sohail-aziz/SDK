package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseGetPaymentOptions;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by aldi on 6/15/2017.
 */

public class GetPaymentOptionsUseCase extends AuthenticatedUseCase<GetPaymentOptionsUseCase.Params> {


    LiquidPayPrefs liquidPayPrefs;
    Repository repository;

    @Inject
    public GetPaymentOptionsUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config,String accessToken, final Params params) {

        repository.getPaymentOptions(config, accessToken, params.paymentId, new LiquidPayCallback<ResponseGetPaymentOptions>() {
            @Override
            public void onSuccess(ResponseGetPaymentOptions response) {
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
        public LiquidPayCallback<ResponseGetPaymentOptions> callback;

        public Params(String paymentId, LiquidPayCallback<ResponseGetPaymentOptions> callback) {
            this.paymentId = paymentId;
            this.callback = callback;
        }

    }
}
