package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseListCards;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by sohail on 6/16/2017.
 */


public class ListCardsUseCase extends AuthenticatedUseCase<LiquidPayCallback<ResponseListCards>> {

    LiquidPayPrefs liquidPayPrefs;
    Repository repository;


    @Inject
    public ListCardsUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final LiquidPayCallback<ResponseListCards> callback) {

        this.repository.listCards(config, accessToken, new LiquidPayCallback<ResponseListCards>() {
            @Override
            public void onSuccess(ResponseListCards response) {
                callback.onSuccess(response);
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                callback.onError(throwable, errorMessage);
            }
        });

    }

    @Override
    void error(Throwable throwable, String message, LiquidPayCallback<ResponseListCards> responseListCardsLiquidPayCallback) {
        responseListCardsLiquidPayCallback.onError(throwable, message);
    }


}
