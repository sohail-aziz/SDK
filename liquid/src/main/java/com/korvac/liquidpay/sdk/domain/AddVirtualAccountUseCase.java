package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseAddVirtualAccount;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by Aldi on 11/14/2017.
 */

public class AddVirtualAccountUseCase extends AuthenticatedUseCase<AddVirtualAccountUseCase.Params> {

    private LiquidPayPrefs liquidPayPrefs;
    private Repository repository;

    @Inject
    public AddVirtualAccountUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.addVirtualAccount(config, accessToken, params.virtualAccountId, params.gender, params.dateOfBirth, new LiquidPayCallback<ResponseAddVirtualAccount>() {
            @Override
            public void onSuccess(ResponseAddVirtualAccount response) {
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

        public final String virtualAccountId, gender, dateOfBirth;

        public final LiquidPayCallback<ResponseAddVirtualAccount> callback;

        public Params(String virtualAccountId, String gender, String dateOfBirth, LiquidPayCallback<ResponseAddVirtualAccount> callback) {
            this.virtualAccountId = virtualAccountId;
            this.gender = gender;
            this.dateOfBirth = dateOfBirth;
            this.callback = callback;
        }

    }
}
