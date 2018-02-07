package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseAvailableVirtualAccount;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by Aldi on 11/10/2017.
 */

public class GetAvailableVirtualAccountUseCase extends AuthenticatedUseCase<GetAvailableVirtualAccountUseCase.Param> {

    private LiquidPayPrefs liquidPayPrefs;
    private Repository repository;

    @Inject
    public GetAvailableVirtualAccountUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Param param) {
        repository.getAvailableVirtualAccount(config, accessToken, param.offset, param.limit, new LiquidPayCallback<ResponseAvailableVirtualAccount>() {
            @Override
            public void onSuccess(ResponseAvailableVirtualAccount response) {
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

    public static final class Param {
        public int offset;
        public int limit;
        public LiquidPayCallback<ResponseAvailableVirtualAccount> callback;

        public Param(int offset, int limit, LiquidPayCallback<ResponseAvailableVirtualAccount> callback) {
            this.offset = offset;
            this.limit = limit;
            this.callback = callback;
        }
    }
}
