package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseCheckCardStatus;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by aldi on 6/15/2017.
 */

public class CheckCardStatusUseCase extends AuthenticatedUseCase<CheckCardStatusUseCase.Params> {

    LiquidPayPrefs liquidPayPrefs;
    Repository repository;

    @Inject
    public CheckCardStatusUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, final String accessToken, final Params params){

        repository.checkCardStatus(config, accessToken, params.sourceId, new LiquidPayCallback<ResponseCheckCardStatus>() {
            @Override
            public void onSuccess(ResponseCheckCardStatus response) {
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

        public String sourceId;
        public LiquidPayCallback<ResponseCheckCardStatus> callback;

        public Params(String sourceId, LiquidPayCallback<ResponseCheckCardStatus> callback) {
            this.sourceId = sourceId;
            this.callback = callback;
        }
    }
}
