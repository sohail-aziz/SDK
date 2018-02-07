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

public class LoginUseCase extends BaseUseCase<LoginUseCase.Params> {

    private final Repository repository;
    private final LiquidPayPrefs liquidPayPrefs;

    @Inject
    public LoginUseCase(Repository repository, LiquidPayPrefs liquidPayPrefs) {

        this.repository = repository;
        this.liquidPayPrefs = liquidPayPrefs;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, final Params params) {

        this.repository.login(config, params.username, params.password, new LiquidPayCallback<ResponseLogin>() {
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
        public final String username;
        public final String password;
        public final LiquidPayCallback<ResponseLogin> callback;

        public Params(String username, String password, LiquidPayCallback<ResponseLogin> callback) {
            this.username = username;
            this.password = password;
            this.callback = callback;
        }
    }
}
