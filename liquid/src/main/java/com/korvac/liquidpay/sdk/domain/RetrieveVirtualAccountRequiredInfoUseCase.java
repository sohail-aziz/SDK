package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseRetrieveVirtualAccountRequiredInfo;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by Aldi on 11/10/2017.
 */

public class RetrieveVirtualAccountRequiredInfoUseCase extends AuthenticatedUseCase<RetrieveVirtualAccountRequiredInfoUseCase.Param> {

    private LiquidPayPrefs liquidPayPrefs;
    private Repository repository;

    @Inject
    public RetrieveVirtualAccountRequiredInfoUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Param param) {
        repository.retrieveVirtualAccountRequiredInfo(config, accessToken, param.virtualAccountId, new LiquidPayCallback<ResponseRetrieveVirtualAccountRequiredInfo>() {
            @Override
            public void onSuccess(ResponseRetrieveVirtualAccountRequiredInfo response) {
                param.callback.onSuccess(response);
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                param.callback.onError(throwable, errorMessage);
            }
        });
    }

    @Override
    void error(Throwable throwable, String message, Param param) {
        param.callback.onError(throwable, message);
    }

    public final static class Param{
        public String virtualAccountId;
        public LiquidPayCallback<ResponseRetrieveVirtualAccountRequiredInfo> callback;

        public Param(String virtualAccountId, LiquidPayCallback<ResponseRetrieveVirtualAccountRequiredInfo> callback) {
            this.virtualAccountId = virtualAccountId;
            this.callback = callback;
        }
    }
}
