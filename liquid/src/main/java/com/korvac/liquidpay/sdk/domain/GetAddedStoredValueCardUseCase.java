package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseGetStoredValueCard;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by aldi on 6/13/2017.
 */

public class GetAddedStoredValueCardUseCase extends AuthenticatedUseCase<LiquidPayCallback<ResponseGetStoredValueCard>> {

    private final Repository repository;
    private final LiquidPayPrefs liquidPayPrefs;

    @Inject
    public GetAddedStoredValueCardUseCase(Repository repository, LiquidPayPrefs liquidPayPrefs) {
        super(liquidPayPrefs, repository);
        this.repository = repository;
        this.liquidPayPrefs = liquidPayPrefs;
    }

    @Override
    void callRealAPI(LiquidPayConfig config,String accessToken, final LiquidPayCallback<ResponseGetStoredValueCard> responseGetStoredValueCardLiquidPayCallback) {
        repository.getAddedStoredValueCard(config, accessToken, new LiquidPayCallback<ResponseGetStoredValueCard>() {
            @Override
            public void onSuccess(ResponseGetStoredValueCard response) {
                responseGetStoredValueCardLiquidPayCallback.onSuccess(response);
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                responseGetStoredValueCardLiquidPayCallback.onError(throwable, errorMessage);
            }
        });
    }

    @Override
    void error(Throwable throwable, String message, LiquidPayCallback<ResponseGetStoredValueCard> responseGetStoredValueCardLiquidPayCallback) {
        responseGetStoredValueCardLiquidPayCallback.onError(throwable, message);
    }
}
