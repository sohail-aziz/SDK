package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseCardBinDetails;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by aldi on 6/14/2017.
 */

public class GetCardBinDetailsUseCase extends AuthenticatedUseCase<GetCardBinDetailsUseCase.Params> {

    LiquidPayPrefs liquidPayPrefs;
    Repository repository;

    @Inject
    public GetCardBinDetailsUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config,String accessToken, final Params params) {
        repository.getCardBinDetails(config, accessToken, params.cardNumber, new LiquidPayCallback<ResponseCardBinDetails>() {
            @Override
            public void onSuccess(ResponseCardBinDetails response) {
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
        public String cardNumber;
        public LiquidPayCallback<ResponseCardBinDetails> callback;

        public Params(String cardNumber, LiquidPayCallback<ResponseCardBinDetails> callback) {
            this.cardNumber = cardNumber;
            this.callback = callback;
        }
    }
}
