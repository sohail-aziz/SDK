package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseCheckEmailVerification;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * CheckEmailVerification usecase in clean architecture
 * <p>
 * Created by sohail on 6/12/2017.
 */


public class CheckEmailVerificationUseCase extends AuthenticatedUseCase<LiquidPayCallback<ResponseCheckEmailVerification>> {

    private final Repository repository;
    private final LiquidPayPrefs liquidPayPrefs;

    @Inject
    public CheckEmailVerificationUseCase(Repository repository, LiquidPayPrefs liquidPayPrefs) {
        super(liquidPayPrefs, repository);
        this.repository = repository;
        this.liquidPayPrefs = liquidPayPrefs;
    }


    @Override
    void callRealAPI(LiquidPayConfig config,String accessToken, final LiquidPayCallback<ResponseCheckEmailVerification> callback) {


        this.repository.checkEmailVerification(config, accessToken, new LiquidPayCallback<ResponseCheckEmailVerification>() {
            @Override
            public void onSuccess(ResponseCheckEmailVerification response) {
                callback.onSuccess(response);
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                callback.onError(throwable, errorMessage);
            }
        });


    }

    @Override
    void error(Throwable throwable, String message, LiquidPayCallback<ResponseCheckEmailVerification> callback) {
        callback.onError(throwable, message);
    }

}
