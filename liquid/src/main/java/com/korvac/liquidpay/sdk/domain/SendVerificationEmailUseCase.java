package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseSendVerifyEmail;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * SendVerificationEmail use case in clean architecture.
 * Created by sohail on 6/13/2017.
 */

public class SendVerificationEmailUseCase extends AuthenticatedUseCase<SendVerificationEmailUseCase.Params> {

    private final Repository repository;
    private final LiquidPayPrefs liquidPayPrefs;

    @Inject
    public SendVerificationEmailUseCase(Repository repository, LiquidPayPrefs liquidPayPrefs) {
        super(liquidPayPrefs, repository);
        this.repository = repository;
        this.liquidPayPrefs = liquidPayPrefs;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, final String accessToken, final Params params) {

        this.repository.sendVerifyEmail(config, accessToken, params.emailAddress, params.forceUpdate, new LiquidPayCallback<ResponseSendVerifyEmail>() {
            @Override
            public void onSuccess(ResponseSendVerifyEmail response) {
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


    /**
     * Input parameter class for this use case
     */
    public static final class Params {

        public final String emailAddress;
        public final boolean forceUpdate;
        public final LiquidPayCallback<ResponseSendVerifyEmail> callback;

        public Params(String emailAddress, boolean forceUpdate, LiquidPayCallback<ResponseSendVerifyEmail> callback) {
            this.emailAddress = emailAddress;
            this.forceUpdate = forceUpdate;
            this.callback = callback;
        }
    }
}
