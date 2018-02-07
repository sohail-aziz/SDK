package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseAvailableStoredValueCard;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by Aldi on 11/16/2017.
 */

public class GetAvailableStoredValueCardUseCase extends AuthenticatedUseCase<GetAvailableStoredValueCardUseCase.Params> {


    private LiquidPayPrefs liquidPayPrefs;
    private Repository repository;

    @Inject
    public GetAvailableStoredValueCardUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.getAvailableStoredValueCard(config, accessToken, params.offset, params.limit, new LiquidPayCallback<ResponseAvailableStoredValueCard>() {
            @Override
            public void onSuccess(ResponseAvailableStoredValueCard response) {
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

    public static class Params {

        public final int offset;
        public final int limit;
        public final LiquidPayCallback<ResponseAvailableStoredValueCard> callback;

        public Params(int offset, int limit, LiquidPayCallback<ResponseAvailableStoredValueCard> callback) {
            this.offset = offset;
            this.limit = limit;
            this.callback = callback;
        }
    }

}
