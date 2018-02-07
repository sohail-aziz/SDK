package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseGetAddedVirtualAccount;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by Aldi on 11/14/2017.
 */

public class GetAddedVirtualAccountUseCase extends AuthenticatedUseCase<LiquidPayCallback<ResponseGetAddedVirtualAccount>> {

    private LiquidPayPrefs liquidPayPrefs;
    private Repository repository;

    @Inject
    public GetAddedVirtualAccountUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final LiquidPayCallback<ResponseGetAddedVirtualAccount> callback) {
        repository.getAddedVirtualAccount(config, accessToken, new LiquidPayCallback<ResponseGetAddedVirtualAccount>() {
            @Override
            public void onSuccess(ResponseGetAddedVirtualAccount response) {
                callback.onSuccess(response);
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                callback.onError(throwable, errorMessage);
            }
        });
    }

    @Override
    void error(Throwable throwable, String message, LiquidPayCallback<ResponseGetAddedVirtualAccount> callback) {
        callback.onError(throwable, message);
    }
}
