package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseValidateUserDetails;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;

import javax.inject.Inject;

/**
 * Created by aldi on 6/14/2017.
 */

public class ValidateUserDetailsUseCase extends BaseUseCase<ValidateUserDetailsUseCase.Params> {

    Repository repository;

    @Inject
    public ValidateUserDetailsUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, final Params params) {
        repository.validateUserDetails(config, params.mobile, params.emailAddress, params.promoCode, params.firstName, params.lastName, params.dialingCode, new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
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

        public String firstName;
        public String lastName;
        public String emailAddress;

        public String dialingCode;
        public String mobile;
        public String promoCode;
        public LiquidPayCallback<ResponseValidateUserDetails> callback;

        public Params(String firstName, String lastName, String emailAddress, String dialingCode, String mobile, String promoCode, LiquidPayCallback<ResponseValidateUserDetails> callback) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.emailAddress = emailAddress;

            this.dialingCode = dialingCode;
            this.mobile = mobile;
            this.promoCode = promoCode;
            this.callback = callback;
        }
    }
}
