package com.korvac.liquidpay.sdk.main;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.korvac.liquidpay.sdk.data.ErrorUtils;
import com.korvac.liquidpay.sdk.data.response.ResponseCheckEmailVerification;
import com.korvac.liquidpay.sdk.data.response.ResponseGetStoredValueCard;
import com.korvac.liquidpay.sdk.data.response.ResponseGetStoredValueCardRequiredDetails;
import com.korvac.liquidpay.sdk.data.response.ResponseGetStoredValueCardTopupOptions;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.data.response.ResponseSendOTP;
import com.korvac.liquidpay.sdk.data.response.ResponseSendVerifyEmail;
import com.korvac.liquidpay.sdk.data.response.ResponseValidateUserDetails;
import com.korvac.liquidpay.sdk.domain.Repository;
import com.korvac.liquidpay.sdk.utils.Logger;
import com.korvac.liquidpay.sdk.utils.PreConditions;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Intermediate api manager
 * calls REST APIs
 * <p>
 * Created by sohail on 5/17/2017.
 */

@Deprecated
@Singleton
public final class LiquidPayManager {


    private final Repository repository;
    private final ErrorUtils errorUtils;

    private final Logger logger;
    private LiquidPayConfig config;


    @Inject
    public LiquidPayManager(Repository repository, SharedPreferences sharedPreferences, ErrorUtils errorUtils, Logger logger) {
        this.repository = repository;
        this.errorUtils = errorUtils;
        this.logger = logger;
    }

    public void setConfig(LiquidPayConfig config) {
        this.config = config;
    }

    public void getAddedStoredValueCard(@NonNull String accessToken, @NonNull final LiquidPayCallback<ResponseGetStoredValueCard> getSVCListener) {
        PreConditions.checkState((config != null), "set config before calling this");
//        try {
//            repository.listUserStoreValueCards(config, accessToken, new LiquidPayCallback<ResponseGetStoredValueCard>() {
//                @Override
//                public void onResponse(Call<ResponseGetStoredValueCard> call, Response<ResponseGetStoredValueCard> response) {
//                    if (response.isSuccessful()) {
//                        getSVCListener.onSuccess(response.body());
//                    } else {
//                        List<Error> errors = errorUtils.parseErrorList(response);
//                        getSVCListener.onError(new LiquidPayException(errors, "error list"), "error list");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseGetStoredValueCard> call, Throwable t) {
//                    getSVCListener.onError(t, t.getMessage());
//                }
//            });
//        } catch (UnsupportedEncodingException e) {
//            getSVCListener.onError(e, e.getMessage());
//        } catch (NoSuchAlgorithmException e) {
//            getSVCListener.onError(e, e.getMessage());
//        }
    }

    public void getStoredValueCardRequiredDetails(@NonNull String accessToken, @NonNull String id, @NonNull final LiquidPayCallback<ResponseGetStoredValueCardRequiredDetails> getSVCDetailsListener) {
//        try {
//            repository.getStoreValueCardRequiredDetails(config, accessToken, id, new Callback<ResponseGetStoredValueCardRequiredDetails>() {
//                @Override
//                public void onResponse(Call<ResponseGetStoredValueCardRequiredDetails> call, Response<ResponseGetStoredValueCardRequiredDetails> response) {
//                    if (response.isSuccessful()) {
//                        getSVCDetailsListener.onSuccess(response.body());
//                    } else {
//                        List<Error> errors = errorUtils.parseErrorList(response);
//                        getSVCDetailsListener.onError(new LiquidPayException(errors, "error list"), "error list");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseGetStoredValueCardRequiredDetails> call, Throwable t) {
//                    getSVCDetailsListener.onError(t, t.getMessage());
//                }
//            });
//        } catch (UnsupportedEncodingException e) {
//            getSVCDetailsListener.onError(e, e.getMessage());
//        } catch (NoSuchAlgorithmException e) {
//            getSVCDetailsListener.onError(e, e.getMessage());
//        }
    }

    public void getStoredValueCardTopUpOptions(@NonNull String accessToken, @NonNull String recipient, @NonNull final LiquidPayCallback<ResponseGetStoredValueCardTopupOptions> topUpOptionListener) {
//        try {
//            repository.getStoredValueCardTopUpOptions(config, accessToken, recipient, new Callback<ResponseGetStoredValueCardTopupOptions>() {
//                @Override
//                public void onResponse(Call<ResponseGetStoredValueCardTopupOptions> call, Response<ResponseGetStoredValueCardTopupOptions> response) {
//                    if (response.isSuccessful()) {
//                        topUpOptionListener.onSuccess(response.body());
//                    } else {
//                        List<Error> errors = errorUtils.parseErrorList(response);
//                        topUpOptionListener.onError(new LiquidPayException(errors, "error list"), "error list");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseGetStoredValueCardTopupOptions> call, Throwable t) {
//                    topUpOptionListener.onError(t, t.getMessage());
//                }
//            });
//        } catch (UnsupportedEncodingException e) {
//            topUpOptionListener.onError(e, e.getMessage());
//        } catch (NoSuchAlgorithmException e) {
//            topUpOptionListener.onError(e, e.getMessage());
//        }
    }

    public void sendEmailVerify(@NonNull String accessToken, @NonNull final String email, boolean forceUpdate, final @NonNull LiquidPayCallback<ResponseSendVerifyEmail> sendVerifyEmailListener) {
        PreConditions.checkState((config != null), "set config before calling this");

//        try {
//            repository.resendEmailVerification(config, accessToken, email, forceUpdate, new Callback<ResponseSendVerifyEmail>() {
//                @Override
//                public void onResponse(Call<ResponseSendVerifyEmail> call, Response<ResponseSendVerifyEmail> response) {
//                    if (response.isSuccessful()) {
//                        sendVerifyEmailListener.onSuccess(response.body());
//                    } else {
//
//                        List<Error> errors = errorUtils.parseErrorList(response);
//                        sendVerifyEmailListener.onError(new LiquidPayException(errors, "check error list"), "check error list");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseSendVerifyEmail> call, Throwable t) {
//                    sendVerifyEmailListener.onError(t, t.getMessage());
//                }
//            });
//
//        } catch (UnsupportedEncodingException e) {
//            sendVerifyEmailListener.onError(e, e.getMessage());
//
//        } catch (NoSuchAlgorithmException e) {
//
//            sendVerifyEmailListener.onError(e, e.getMessage());
//        }
    }

    public void checkEmailVerificationV2(@NonNull String accessToken, final @NonNull LiquidPayCallback<ResponseCheckEmailVerification> checkEmailVerificationListener) {
        PreConditions.checkState((config != null), "set config before calling this");


//        try {
//            repository.checkEmailVerificationStatus(config, accessToken, new Callback<ResponseCheckEmailVerification>() {
//                @Override
//                public void onResponse(Call<ResponseCheckEmailVerification> call, Response<ResponseCheckEmailVerification> response) {
//                    if (response.isSuccessful()) {
//                        checkEmailVerificationListener.onSuccess(response.body());
//                    } else {
//
//                        List<Error> errors = errorUtils.parseErrorList(response);
//                        checkEmailVerificationListener.onError(new LiquidPayException(errors, "check error list"), "check error list");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseCheckEmailVerification> call, Throwable t) {
//                    checkEmailVerificationListener.onError(t, t.getMessage());
//                }
//            });
//        } catch (UnsupportedEncodingException e) {
//            checkEmailVerificationListener.onError(e, e.getMessage());
//        } catch (NoSuchAlgorithmException e) {
//            checkEmailVerificationListener.onError(e, e.getMessage());
//        }
    }

    public void checkEmailVerification(@NonNull String accessToken, final @NonNull LiquidPayCallback<ResponseCheckEmailVerification> checkEmailVerificationListener) {
        PreConditions.checkState((config != null), "set config before calling this");

//        try {
//            repository.checkEmailVerificationStatus(config, accessToken, new Callback<ResponseCheckEmailVerification>() {
//                @Override
//                public void onResponse(Call<ResponseCheckEmailVerification> call, Response<ResponseCheckEmailVerification> response) {
//                    if (response.isSuccessful()) {
//                        checkEmailVerificationListener.onSuccess(response.body());
//                    } else {
//
//                        List<Error> errors = errorUtils.parseErrorList(response);
//                        checkEmailVerificationListener.onError(new LiquidPayException(errors, "check error list"), "check error list");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseCheckEmailVerification> call, Throwable t) {
//                    checkEmailVerificationListener.onError(t, t.getMessage());
//                }
//            });
//        } catch (UnsupportedEncodingException e) {
//            checkEmailVerificationListener.onError(e, e.getMessage());
//        } catch (NoSuchAlgorithmException e) {
//            checkEmailVerificationListener.onError(e, e.getMessage());
//        }
    }

    public void submitRegistrationDetails(@NonNull String emailAddress,
                                          @NonNull String dialingCode, @NonNull String mobileNumber,
                                          @NonNull String otp, @NonNull String firstName,
                                          @NonNull String lastName, @NonNull String password,
                                          @NonNull String registeredSource, @NonNull String promoCode, @NonNull final LiquidPayCallback<ResponseLogin> submitRegistrationListener) {

        PreConditions.checkState((config != null), "set config before calling this");

//        try {
//            repository.submitRegistrationDetails(config, emailAddress, dialingCode, mobileNumber, otp, firstName, lastName, password, registeredSource, promoCode, new Callback<ResponseLogin>() {
//                @Override
//                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
//                    logger.d("submitRegistration:onResponse=" + response.toString());
//
//                    if (response.isSuccessful()) {
//                        submitRegistrationListener.onSuccess(response.body());
//                    } else {
//
//                        List<Error> errors = errorUtils.parseErrorList(response);
//                        submitRegistrationListener.onError(new LiquidPayException(errors, "check error list"), "check error list");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseLogin> call, Throwable t) {
//                    logger.e("submitRegisteration:onFailure:t=" + t.toString());
//                    submitRegistrationListener.onError(t, t.getMessage());
//                }
//            });
//        } catch (UnsupportedEncodingException e) {
//            submitRegistrationListener.onError(e, e.getMessage());
//        } catch (NoSuchAlgorithmException e) {
//            submitRegistrationListener.onError(e, e.getMessage());
//        }

    }

    public void sendOneTimePassword(@NonNull String dialingCode, @NonNull String mobileNumber, final LiquidPayCallback<ResponseSendOTP> otpListener) {
        PreConditions.checkState((config != null), "set config before calling this");
//        try {
//            repository.sendOTP(config, dialingCode, mobileNumber, new Callback<ResponseSendOTP>() {
//                @Override
//                public void onResponse(Call<ResponseSendOTP> call, Response<ResponseSendOTP> response) {
//
//                    if (response.isSuccessful()) {
//                        otpListener.onSuccess(response.body());
//                    } else {
//
//                        List<Error> errors = errorUtils.parseErrorList(response);
//                        otpListener.onError(new LiquidPayException(errors, "check error list"), "check error list");
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<ResponseSendOTP> call, Throwable t) {
//                    logger.e("setndOneTimePassword:onFailure:t=" + t.toString());
//                    otpListener.onError(t, t.getMessage());
//                }
//            });
//        } catch (UnsupportedEncodingException e) {
//            otpListener.onError(e, e.getMessage());
//        } catch (NoSuchAlgorithmException e) {
//            otpListener.onError(e, e.getMessage());
//        }
    }

    /**
     * Asynchronously refreshes user token using refresh token
     *
     * @param refreshToken         refreshToken as received earlier in {{@link #login(String, String, LiquidPayCallback)}}
     * @param refreshTokenListener result callback {@link LiquidPayCallback}
     */
    public void refreshToken(@NonNull String refreshToken, @NonNull final LiquidPayCallback<ResponseLogin> refreshTokenListener) {

        PreConditions.checkState((config != null), "set config before calling this");
//        try {
//            repository.refreshToken(config, refreshToken, new Callback<ResponseLogin>() {
//                @Override
//                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
//
//                    logger.d("refreshToken:onResponse: response=" + response.toString());
//
//                    if (response.isSuccessful()) {
//                        refreshTokenListener.onSuccess(response.body());
//                    } else {
//
//                        List<Error> errors = errorUtils.parseErrorList(response);
//                        refreshTokenListener.onError(new LiquidPayException(errors, "check error list"), "check error list");
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<ResponseLogin> call, Throwable t) {
//
//                    logger.e("refreshToken:onFailure:t=" + t);
//                    refreshTokenListener.onError(t, t.getMessage());
//                }
//            });
//        } catch (UnsupportedEncodingException e) {
//            refreshTokenListener.onError(e, e.getMessage());
//        } catch (NoSuchAlgorithmException e) {
//            refreshTokenListener.onError(e, e.getMessage());
//        }
    }

    /**
     * Asynchronously Logs In using email and password
     * call {@link #setConfig(LiquidPayConfig)} before calling this
     *
     * @param userName      user email
     * @param password      user password
     * @param loginListener callback {@link LiquidPayCallback<ResponseLogin>}
     * @throws IllegalStateException if config is not set
     */
    public void login(@NonNull String userName, @NonNull String password, @NonNull final LiquidPayCallback<ResponseLogin> loginListener) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        PreConditions.checkState((config != null), "set config before calling this");


//        repository.loginWithPassword(config, userName, password, new Callback<ResponseLogin>() {
//            @Override
//            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
//
//                logger.d("loginWithPassword:onResponse: response=" + response.toString());
//
//                if (response.isSuccessful()) {
//                    loginListener.onSuccess(response.body());
//                } else {
//
//                    List<Error> errors = errorUtils.parseErrorList(response);
//                    loginListener.onError(new LiquidPayException(errors, "check error list"), "check error list");
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseLogin> call, Throwable t) {
//                logger.e("loginWithPassword:onFailure:t=" + t);
//
//                loginListener.onError(t, t.getMessage());
//            }
//        });

    }

    /**
     * Asynchronously validates user details
     * call {@link #setConfig(LiquidPayConfig)} before calling this
     *
     * @param mobile           user mobile number
     * @param email            user email
     * @param promoCode        promotional code (if any)
     * @param firstName        user first name
     * @param lastName         user last name
     * @param password         user password
     * @param dialingCode      mobile country dialing code
     * @param validateListener callback {@link LiquidPayCallback<ResponseValidateUserDetails>}
     */
    public void validateUserDetails(@NonNull String mobile, @NonNull String email, @NonNull String promoCode,
                                    @NonNull String firstName, @NonNull String lastName, @NonNull String password, @NonNull String dialingCode, @NonNull final LiquidPayCallback<ResponseValidateUserDetails> validateListener) {


        PreConditions.checkState((config != null), "set config before calling this");
//        try {
//            repository.validateRegistrationDetails(config, mobile, email, promoCode, firstName, lastName, password, dialingCode, new Callback<ResponseValidateUserDetails>() {
//                @Override
//                public void onResponse(Call<ResponseValidateUserDetails> call, Response<ResponseValidateUserDetails> response) {
//
//                    if (response.isSuccessful()) {
//                        //success
//                        validateListener.onSuccess(response.body());
//                    } else {
//                        //failure
//                        List<Error> errors = errorUtils.parseErrorList(response);
//
//                        validateListener.onError(new LiquidPayException(errors, "check error list"), "check error list");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseValidateUserDetails> call, Throwable t) {
//
//                    validateListener.onError(t, t.getMessage());
//
//                }
//            });
//        } catch (UnsupportedEncodingException e) {
//            validateListener.onError(e, e.getMessage());
//        } catch (NoSuchAlgorithmException e) {
//            validateListener.onError(e, e.getMessage());
//        }
    }


}
