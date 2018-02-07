package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseChangePassword;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by Aldi on 11/8/2017.
 */

public class ChangePasswordUseCase extends AuthenticatedUseCase<ChangePasswordUseCase.Params> {

    private LiquidPayPrefs liquidPayPrefs;
    private Repository repository;

    @Inject
    public ChangePasswordUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.changePassword(config, accessToken, params.oldPassword, params.newPassword, new LiquidPayCallback<ResponseChangePassword>() {
            @Override
            public void onSuccess(ResponseChangePassword response) {
                params.callback.onSuccess(response);
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                params.callback.onError(throwable,errorMessage);
            }
        });
    }

    @Override
    void error(Throwable throwable, String message, Params params) {
        params.callback.onError(throwable, message);
    }

    public static class Params{

        public String oldPassword;
        public String newPassword;
        public LiquidPayCallback<ResponseChangePassword> callback;

        public Params(String oldPassword, String newPassword, LiquidPayCallback<ResponseChangePassword> callback) {
            this.oldPassword = oldPassword;
            this.newPassword = newPassword;
            this.callback = callback;
        }
    }
}
