package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseAddStoredValueCard;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by aldi on 6/14/2017.
 */

public class AddStoredValueCardUseCase extends AuthenticatedUseCase<AddStoredValueCardUseCase.Params> {

    LiquidPayPrefs liquidPayPrefs;
    Repository repository;

    @Inject
    public AddStoredValueCardUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, final String accessToken, final Params params) {
        repository.addStoredValueCard(config, accessToken, params.nationality, params.id,  params.identifier, new LiquidPayCallback<ResponseAddStoredValueCard>() {
            @Override
            public void onSuccess(ResponseAddStoredValueCard response) {
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
        public String nationality;
        public String id;

        public String identifier;
        public LiquidPayCallback<ResponseAddStoredValueCard> callback;

        public Params(String nationality, String id, String identifier, LiquidPayCallback<ResponseAddStoredValueCard> callback) {
            this.nationality = nationality;
            this.id = id;

            this.identifier = identifier;
            this.callback = callback;

        }

    }
}
