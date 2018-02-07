package com.korvac.liquidpay.sdk.domain;

import android.util.Log;

import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.exception.LiquidPayInvalidAccessTokenException;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import java.util.Date;

/**
 * AutoRefreshes the accessToken if expires
 * <p>
 * All the UseCases require AccessToken must inherit from this UseCase
 * <p>
 * Created by sohail on 6/12/2017.
 */

public abstract class AuthenticatedUseCase<Params> {


    abstract void callRealAPI(LiquidPayConfig config, final String accessToken, final Params params);

    abstract void error(Throwable throwable, String message, final Params params);

    public final LiquidPayPrefs liquidPayPrefs;
    public final Repository repository;


    public AuthenticatedUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {

        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }


    public void execute(final LiquidPayConfig config, final String accessToken, final Params params) {
        long expiry = liquidPayPrefs.getTokenExpiryTimeStamp();
        long currentTime = new Date().getTime();

        if (currentTime < expiry) {
            //access token still valid
            Log.d(AuthenticatedUseCase.class.getSimpleName(), "execute: accessTokenValid");
            callRealAPI(config, accessToken, params);
        } else {
            Log.d(AuthenticatedUseCase.class.getSimpleName(), "execute: accessTokenExpired: refreshing");
            repository.refreshToken(config, liquidPayPrefs.getRefreshToken(), new LiquidPayCallback<ResponseLogin>() {
                @Override
                public void onSuccess(ResponseLogin response) {
                    liquidPayPrefs.setTokenInfo(response.accessToken(), response.refreshToken(), response.expiresIn());
                    callRealAPI(config, response.accessToken(), params);
                }

                @Override
                public void onError(Throwable throwable, String errorMessage) {
                    if (throwable instanceof LiquidPayException) {
                        //server error, session expired
                        liquidPayPrefs.clearTokenInfo();
                        error(new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword"), "Access token expire, please re-loginWithPassword", params);
                    } else {
                        error(throwable, errorMessage, params);
                    }


                }
            });

        }
    }


}
