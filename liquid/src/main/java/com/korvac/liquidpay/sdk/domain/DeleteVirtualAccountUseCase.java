package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseDeleteVirtualAccount;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by Aldi on 11/14/2017.
 */

public class DeleteVirtualAccountUseCase extends AuthenticatedUseCase<DeleteVirtualAccountUseCase.Params> {


    private LiquidPayPrefs liquidPayPrefs;
    private Repository repository;

    @Inject
    public DeleteVirtualAccountUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.deleteVirtualAccount(config, accessToken, params.virtualAccountId, new LiquidPayCallback<ResponseDeleteVirtualAccount>() {
            @Override
            public void onSuccess(ResponseDeleteVirtualAccount response) {
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

    public static class Params{

        public final String virtualAccountId;
        public final LiquidPayCallback<ResponseDeleteVirtualAccount> callback;

        public Params(String virtualAccountId, LiquidPayCallback<ResponseDeleteVirtualAccount> callback) {
            this.virtualAccountId = virtualAccountId;
            this.callback = callback;
        }
    }

}
