package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by aldi on 6/14/2017.
 */

public class SubmitRegistrationDetailsUseCase extends BaseUseCase<SubmitRegistrationDetailsUseCase.Params> {

    private final Repository repository;
    private final LiquidPayPrefs liquidPayPrefs;

    @Inject
    public SubmitRegistrationDetailsUseCase(Repository repository, LiquidPayPrefs liquidPayPrefs) {

        this.repository = repository;
        this.liquidPayPrefs = liquidPayPrefs;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, final Params params) {
        repository.submitRegistrationDetails(config, params.emailAddress, params.dialingCode, params.mobileNumber, params.otp, params.firstName,
                params.lastName, params.password, params.registeredSource, params.promoCode, new LiquidPayCallback<ResponseLogin>() {
            @Override
            public void onSuccess(ResponseLogin response) {
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

    public static final class Params{

        public String emailAddress;
        public String dialingCode;
        public String mobileNumber;
        public String otp;
        public String firstName;
        public String lastName;
        public String password;
        public String registeredSource;
        public String promoCode;
        public LiquidPayCallback<ResponseLogin> callback;

        public Params(String emailAddress, String dialingCode, String mobileNumber, String otp, String firstName, String lastName,
                      String password, String registeredSource, String promoCode, LiquidPayCallback<ResponseLogin> callback) {

            this.emailAddress = emailAddress;
            this.dialingCode = dialingCode;
            this.mobileNumber = mobileNumber;
            this.otp = otp;
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
            this.registeredSource = registeredSource;
            this.promoCode = promoCode;
            this.callback = callback;
        }

    }
}
