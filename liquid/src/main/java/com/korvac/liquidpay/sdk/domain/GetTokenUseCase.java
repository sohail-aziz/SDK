package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Login Usecase in clean architecure
 * <p>
 * Created by sohail on 6/13/2017.
 */

public class GetTokenUseCase extends BaseUseCase<GetTokenUseCase.Params> {

    private final Repository repository;
    private final LiquidPayPrefs liquidPayPrefs;

    @Inject
    public GetTokenUseCase(Repository repository, LiquidPayPrefs liquidPayPrefs) {

        this.repository = repository;
        this.liquidPayPrefs = liquidPayPrefs;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, final Params params) {

        this.repository.getToken(config, params.authorizationCode, params.redirectUri, new LiquidPayCallback<ResponseLogin>() {
            @Override
            public void onSuccess(ResponseLogin response) {
                //save loginWithPassword token info
                liquidPayPrefs.setTokenInfo(response.accessToken(), response.refreshToken(), response.expiresIn());
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
        public final String authorizationCode;
        public final String redirectUri;
        public final LiquidPayCallback<ResponseLogin> callback;

        public Params(String authorizationCode, String redirectUri, LiquidPayCallback<ResponseLogin> callback) {
            this.authorizationCode = authorizationCode;
            this.redirectUri = redirectUri;
            this.callback = callback;
        }
    }
}
