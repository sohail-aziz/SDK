package com.korvac.liquidpay.sdk.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.korvac.liquidpay.sdk.data.response.BaseCard;
import com.korvac.liquidpay.sdk.data.response.Card3D;
import com.korvac.liquidpay.sdk.data.response.CardNon3D;
import com.korvac.liquidpay.sdk.data.response.Error;
import com.korvac.liquidpay.sdk.data.response.ResponseAddMembershipCard;
import com.korvac.liquidpay.sdk.data.response.ResponseAddStoredValueCard;
import com.korvac.liquidpay.sdk.data.response.ResponseAddVirtualAccount;
import com.korvac.liquidpay.sdk.data.response.ResponseAvailableMembershipCard;
import com.korvac.liquidpay.sdk.data.response.ResponseAvailableStoredValueCard;
import com.korvac.liquidpay.sdk.data.response.ResponseAvailableVirtualAccount;
import com.korvac.liquidpay.sdk.data.response.ResponseCancelPayment;
import com.korvac.liquidpay.sdk.data.response.ResponseCardBinDetails;
import com.korvac.liquidpay.sdk.data.response.ResponseChangePassword;
import com.korvac.liquidpay.sdk.data.response.ResponseCheckCardStatus;
import com.korvac.liquidpay.sdk.data.response.ResponseCheckEmailVerification;
import com.korvac.liquidpay.sdk.data.response.ResponseDeleteCard;
import com.korvac.liquidpay.sdk.data.response.ResponseDeleteVirtualAccount;
import com.korvac.liquidpay.sdk.data.response.ResponseForgotPassword;
import com.korvac.liquidpay.sdk.data.response.ResponseGetAddedMembershipCard;
import com.korvac.liquidpay.sdk.data.response.ResponseGetAddedVirtualAccount;
import com.korvac.liquidpay.sdk.data.response.ResponseGetPaymentOptions;
import com.korvac.liquidpay.sdk.data.response.ResponseGetStoredValueCard;
import com.korvac.liquidpay.sdk.data.response.ResponseGetStoredValueCardRequiredDetails;
import com.korvac.liquidpay.sdk.data.response.ResponseGetStoredValueCardTopupOptions;
import com.korvac.liquidpay.sdk.data.response.ResponseLinkCardToVirtualAccount;
import com.korvac.liquidpay.sdk.data.response.ResponseListCards;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.data.response.ResponsePayment;
import com.korvac.liquidpay.sdk.data.response.ResponsePaymentListEligibleMembershipCard;
import com.korvac.liquidpay.sdk.data.response.ResponsePaymentListEligibleVoucher;
import com.korvac.liquidpay.sdk.data.response.ResponseRetrieveMembershipCardRequiredInfo;
import com.korvac.liquidpay.sdk.data.response.ResponseRetrieveVirtualAccountRequiredInfo;
import com.korvac.liquidpay.sdk.data.response.ResponseSendOTP;
import com.korvac.liquidpay.sdk.data.response.ResponseSendVerifyEmail;
import com.korvac.liquidpay.sdk.data.response.ResponseTopUpStoredValueCard;
import com.korvac.liquidpay.sdk.data.response.ResponseUnLinkCardFromVirtualAccount;
import com.korvac.liquidpay.sdk.data.response.ResponseValidateUserDetails;
import com.korvac.liquidpay.sdk.data.response.ResponseVirtualAccountPaymentCards;
import com.korvac.liquidpay.sdk.data.response.ResponseVoucherGiftBox;
import com.korvac.liquidpay.sdk.data.response.ResponseVoucherSendToFriend;
import com.korvac.liquidpay.sdk.data.response.ResponseWalletAddBrandVoucher;
import com.korvac.liquidpay.sdk.data.response.ResponseWalletSaveVoucher;
import com.korvac.liquidpay.sdk.data.response.ResponseWalletVoucherList;
import com.korvac.liquidpay.sdk.domain.Repository;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.main.LiquidDateFormatter;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.Nonnegative;
import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @see <p>
 * Created by sohail on 5/5/2017.
 */


@Singleton
public final class RepositoryImpl implements Repository {

    private final LiquidPayAPI liquidPayAPI;
    private final SecurityUtils securityUtils;
    private final ErrorUtils errorUtils;
    private final LiquidDateFormatter liquidDateFormatter;

//    private AsyncHttpClient asyncHttpClient;

    @Inject
    public RepositoryImpl(@NonNull LiquidPayAPI liquidPayAPI, @NonNull SecurityUtils securityUtils, ErrorUtils errorUtils, LiquidDateFormatter liquidDateFormatter) {
        this.liquidPayAPI = liquidPayAPI;
        this.securityUtils = securityUtils;
        this.errorUtils = errorUtils;
        this.liquidDateFormatter = liquidDateFormatter;
//        asyncHttpClient = new AsyncHttpClient();
    }

    private String getBearerAccessToken(String accessToken) {
        return "Bearer " + accessToken;
    }

    @Override
    public void login(LiquidPayConfig config, String username, String password, @NonNull final LiquidPayCallback<ResponseLogin> loginListener) {

        LiquidPayConfig.DeviceInfo deviceInfo = config.deviceInfo;

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("username", username);
        treeMap.put("password", password);
        treeMap.put("device_type", deviceInfo.deviceType);
        treeMap.put("device_id", deviceInfo.gcmId);
        treeMap.put("device_uid", deviceInfo.deviceUID);
        treeMap.put("device_model", deviceInfo.deviceModel);
        treeMap.put("device_os", deviceInfo.deviceOS);

        String sign = null;
        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            loginListener.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            loginListener.onError(e, e.getMessage());
            return;
        }


        Call<ResponseLogin> call = liquidPayAPI.loginWithPassword(config.apiKey, username, password,
                config.deviceInfo.deviceType, deviceInfo.gcmId, deviceInfo.deviceUID, deviceInfo.deviceModel, deviceInfo.deviceOS, nonce, sign);

        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                if (response.isSuccessful()) {
                    loginListener.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    loginListener.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

                loginListener.onError(t, t.getMessage());
            }
        });

    }

    @Override
    public void getToken(LiquidPayConfig config, String authorizationCode, String redirectUri, @NonNull final LiquidPayCallback<ResponseLogin> tokenListener) {

        LiquidPayConfig.DeviceInfo deviceInfo = config.deviceInfo;

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("authorization_code", authorizationCode);
        treeMap.put("redirect_uri", redirectUri);
        treeMap.put("device_type", deviceInfo.deviceType);
        treeMap.put("device_id", deviceInfo.gcmId);
        treeMap.put("device_uid", deviceInfo.deviceUID);
        treeMap.put("device_model", deviceInfo.deviceModel);
        treeMap.put("device_os", deviceInfo.deviceOS);

        String sign = null;
        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            tokenListener.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            tokenListener.onError(e, e.getMessage());
            return;
        }

        Call<ResponseLogin> call = liquidPayAPI.getToken(config.apiKey, authorizationCode, redirectUri,
                config.deviceInfo.deviceType, deviceInfo.gcmId, deviceInfo.deviceUID, deviceInfo.deviceModel, deviceInfo.deviceOS, nonce, sign);

        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    tokenListener.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    tokenListener.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                tokenListener.onError(t, t.getMessage());
            }
        });

    }


    @Override
    public void refreshToken(@NonNull LiquidPayConfig config, @NonNull String refreshToken, @NonNull final LiquidPayCallback<ResponseLogin> refreshTokenListener) {

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("refresh_token", refreshToken);

        String sign = null;

        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            refreshTokenListener.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            refreshTokenListener.onError(e, e.getMessage());
            return;
        }

        Call<ResponseLogin> call = liquidPayAPI.refreshToken(config.apiKey, refreshToken, nonce, sign);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                if (response.isSuccessful()) {
                    refreshTokenListener.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    refreshTokenListener.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                refreshTokenListener.onError(t, t.getMessage());
            }
        });

    }


    @Override
    public void checkEmailVerification(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                       @NonNull final LiquidPayCallback<ResponseCheckEmailVerification> checkEmailVerificationListener) {

        Call<ResponseCheckEmailVerification> call = liquidPayAPI.checkEmailVerificationStatus(config.apiKey, getBearerAccessToken(accessToken));
        call.enqueue(new Callback<ResponseCheckEmailVerification>() {
            @Override
            public void onResponse(Call<ResponseCheckEmailVerification> call, Response<ResponseCheckEmailVerification> response) {

                if (response.isSuccessful()) {
                    checkEmailVerificationListener.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    checkEmailVerificationListener.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseCheckEmailVerification> call, Throwable t) {
                checkEmailVerificationListener.onError(t, t.getMessage());
            }
        });

    }

    @Override
    public void sendVerifyEmail(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String email, boolean forceUpdate, final @NonNull LiquidPayCallback<ResponseSendVerifyEmail> callback) {

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("email_address", email);
        treeMap.put("force_update", forceUpdate);

        String sign = null;
        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseSendVerifyEmail> call = liquidPayAPI.sendVerifyEmail(config.apiKey, getBearerAccessToken(accessToken), email, forceUpdate, nonce, sign);
        call.enqueue(new Callback<ResponseSendVerifyEmail>() {
            @Override
            public void onResponse(Call<ResponseSendVerifyEmail> call, Response<ResponseSendVerifyEmail> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseSendVerifyEmail> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });

    }

    @Override
    public void changePassword(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String oldPassword, @NonNull String newPassword, @NonNull final LiquidPayCallback<ResponseChangePassword> callback) {

        final String nonce = String.valueOf(new Date().getTime());
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("password_old", oldPassword);
        treeMap.put("password_new", newPassword);

        String sign = null;
        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseChangePassword> call = liquidPayAPI.changePassword(config.apiKey, getBearerAccessToken(accessToken), nonce, sign, oldPassword, newPassword);

        call.enqueue(new Callback<ResponseChangePassword>() {
            @Override
            public void onResponse(Call<ResponseChangePassword> call, Response<ResponseChangePassword> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseChangePassword> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void forgotPassword(@NonNull LiquidPayConfig config, @NonNull String email, @NonNull final LiquidPayCallback<ResponseForgotPassword> callback) {
        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("email", email);

        String sign = null;
        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseForgotPassword> call = liquidPayAPI.forgotPassword(config.apiKey, nonce, sign, email);
        call.enqueue(new Callback<ResponseForgotPassword>() {
            @Override
            public void onResponse(Call<ResponseForgotPassword> call, Response<ResponseForgotPassword> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseForgotPassword> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void listCards(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull final LiquidPayCallback<ResponseListCards> callback) {

        Call<ResponseListCards> call = liquidPayAPI.listCards(config.apiKey, getBearerAccessToken(accessToken));
        call.enqueue(new Callback<ResponseListCards>() {
            @Override
            public void onResponse(Call<ResponseListCards> call, Response<ResponseListCards> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseListCards> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });

    }

    @Override
    public void deleteCard(@NonNull LiquidPayConfig config, @NonNull String accessToken, String cardId, @NonNull final LiquidPayCallback<ResponseDeleteCard> callback) {

        String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("id", cardId);
        String sign = null;

        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseDeleteCard> call = liquidPayAPI.deleteCard(config.apiKey, getBearerAccessToken(accessToken), cardId, cardId, nonce, sign);
        call.enqueue(new Callback<ResponseDeleteCard>() {
            @Override
            public void onResponse(Call<ResponseDeleteCard> call, Response<ResponseDeleteCard> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseDeleteCard> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });


    }

    @Override
    public void createPayment(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String payee, @NonNull String serviceType,
                              @NonNull String merchantRefNo, @NonNull String amount, @NonNull final LiquidPayCallback<ResponsePayment> callback) {

        String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("payee", payee);
        treeMap.put("service_type", serviceType);
        treeMap.put("merchant_ref_no", merchantRefNo);
        treeMap.put("amount", amount);
        String sign = null;

        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponsePayment> call = liquidPayAPI.createPayment(config.apiKey, getBearerAccessToken(accessToken), payee, serviceType, merchantRefNo, amount, nonce, sign);
        call.enqueue(new Callback<ResponsePayment>() {
            @Override
            public void onResponse(Call<ResponsePayment> call, Response<ResponsePayment> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponsePayment> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void payUsingVirtualAccount(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String paymentId, @NonNull String paymentCardId, @NonNull final LiquidPayCallback<ResponsePayment> callback) {

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();

        treeMap.put("source", paymentCardId);

        String sign = null;

        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponsePayment> call = liquidPayAPI.payUsingVirtualAccount(config.apiKey, getBearerAccessToken(accessToken), paymentId, paymentCardId, nonce, sign);
        call.enqueue(new Callback<ResponsePayment>() {
            @Override
            public void onResponse(Call<ResponsePayment> call, Response<ResponsePayment> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponsePayment> call, Throwable t) {
                callback.onError(t, t.getMessage());

            }
        });
    }

    @Override
    public void unLinkCardFromVirtualAccount(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String virtualAccountId, @NonNull String paymentCardId, @NonNull final LiquidPayCallback<ResponseUnLinkCardFromVirtualAccount> callback) {

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();

        treeMap.put("payment_card", paymentCardId);

        String sign = null;

        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseUnLinkCardFromVirtualAccount> call = liquidPayAPI.unLinkCardFromVirtualAccount(config.apiKey, getBearerAccessToken(accessToken),
                virtualAccountId, paymentCardId, nonce, sign);

        call.enqueue(new Callback<ResponseUnLinkCardFromVirtualAccount>() {
            @Override
            public void onResponse(Call<ResponseUnLinkCardFromVirtualAccount> call, Response<ResponseUnLinkCardFromVirtualAccount> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseUnLinkCardFromVirtualAccount> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void linkCardToVirtualAccount(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String virtualAccountId, @NonNull String paymentCardId, @NonNull final LiquidPayCallback<ResponseLinkCardToVirtualAccount> callback) {

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();

        treeMap.put("payment_card", paymentCardId);

        String sign = null;

        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseLinkCardToVirtualAccount> call = liquidPayAPI.linkCardToVirtualAccount(config.apiKey, getBearerAccessToken(accessToken),
                virtualAccountId, paymentCardId, nonce, sign);

        call.enqueue(new Callback<ResponseLinkCardToVirtualAccount>() {
            @Override
            public void onResponse(Call<ResponseLinkCardToVirtualAccount> call, Response<ResponseLinkCardToVirtualAccount> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseLinkCardToVirtualAccount> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void deleteVirtualAccount(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String virtualAccountId, @NonNull final LiquidPayCallback<ResponseDeleteVirtualAccount> callback) {

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();

        treeMap.put("id", virtualAccountId);

        String sign = null;

        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseDeleteVirtualAccount> call = liquidPayAPI.deleteVirtualAccount(config.apiKey,
                getBearerAccessToken(accessToken), virtualAccountId, virtualAccountId, nonce, sign);

        call.enqueue(new Callback<ResponseDeleteVirtualAccount>() {
            @Override
            public void onResponse(Call<ResponseDeleteVirtualAccount> call, Response<ResponseDeleteVirtualAccount> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseDeleteVirtualAccount> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void getAddedVirtualAccount(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull final LiquidPayCallback<ResponseGetAddedVirtualAccount> callback) {

        Call<ResponseGetAddedVirtualAccount> call = liquidPayAPI.getAddedVirtualAccount(config.apiKey, getBearerAccessToken(accessToken));

        call.enqueue(new Callback<ResponseGetAddedVirtualAccount>() {
            @Override
            public void onResponse(Call<ResponseGetAddedVirtualAccount> call, Response<ResponseGetAddedVirtualAccount> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseGetAddedVirtualAccount> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void addVirtualAccount(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String virtualAccountId, @NonNull String gender, @NonNull String dateOfBirth, @NonNull final LiquidPayCallback<ResponseAddVirtualAccount> callback) {

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();

        treeMap.put("id", virtualAccountId);
        treeMap.put("gender", gender);
        treeMap.put("dob", dateOfBirth);

        String sign = null;

        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseAddVirtualAccount> call = liquidPayAPI.addVirtualAccount(config.apiKey, getBearerAccessToken(accessToken),
                virtualAccountId, gender, dateOfBirth, nonce, sign);

        call.enqueue(new Callback<ResponseAddVirtualAccount>() {
            @Override
            public void onResponse(Call<ResponseAddVirtualAccount> call, Response<ResponseAddVirtualAccount> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseAddVirtualAccount> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });

    }

    @Override
    public void getAvailableVirtualAccount(@NonNull LiquidPayConfig config, @NonNull String accessToken, @Nonnegative int offset, @Nonnegative int limit, @NonNull final LiquidPayCallback<ResponseAvailableVirtualAccount> callback) {

        Call<ResponseAvailableVirtualAccount> call = liquidPayAPI.getAvailableVirtualAccount(config.apiKey, getBearerAccessToken(accessToken), offset, limit);

        call.enqueue(new Callback<ResponseAvailableVirtualAccount>() {
            @Override
            public void onResponse(Call<ResponseAvailableVirtualAccount> call, Response<ResponseAvailableVirtualAccount> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseAvailableVirtualAccount> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void retrieveVirtualAccountRequiredInfo(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String virtualAccountId, @NonNull final LiquidPayCallback<ResponseRetrieveVirtualAccountRequiredInfo> callback) {
        Call<ResponseRetrieveVirtualAccountRequiredInfo> call =
                liquidPayAPI.retrieveVirtualAccountRequiredInfo(config.apiKey, getBearerAccessToken(accessToken), virtualAccountId);

        call.enqueue(new Callback<ResponseRetrieveVirtualAccountRequiredInfo>() {
            @Override
            public void onResponse(Call<ResponseRetrieveVirtualAccountRequiredInfo> call, Response<ResponseRetrieveVirtualAccountRequiredInfo> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseRetrieveVirtualAccountRequiredInfo> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    /*######################## MEMBERSHIP CARD ################*/
    @Override
    public void getAvailableMembershipCard(@NonNull LiquidPayConfig config, @NonNull String accessToken, @Nonnegative int offset, @Nonnegative int limit, @NonNull final LiquidPayCallback<ResponseAvailableMembershipCard> callback) {

        Call<ResponseAvailableMembershipCard> call = liquidPayAPI.getAvailableMembershipcard(config.apiKey, getBearerAccessToken(accessToken),
                offset, limit);

        call.enqueue(new Callback<ResponseAvailableMembershipCard>() {
            @Override
            public void onResponse(Call<ResponseAvailableMembershipCard> call, Response<ResponseAvailableMembershipCard> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseAvailableMembershipCard> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });

    }

    @Override
    public void retrieveMembershipCardRequiredInfo(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String membershipCardId, @NonNull final LiquidPayCallback<ResponseRetrieveMembershipCardRequiredInfo> callback) {

        Call<ResponseRetrieveMembershipCardRequiredInfo> call =
                liquidPayAPI.retrieveMembershipCardRequiredInfo(config.apiKey, getBearerAccessToken(accessToken),
                        membershipCardId);

        call.enqueue(new Callback<ResponseRetrieveMembershipCardRequiredInfo>() {
            @Override
            public void onResponse(Call<ResponseRetrieveMembershipCardRequiredInfo> call, Response<ResponseRetrieveMembershipCardRequiredInfo> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseRetrieveMembershipCardRequiredInfo> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });

    }

    @Override
    public void getAddedMembershipCard(@NonNull LiquidPayConfig config, @NonNull String accessToken, @Nonnegative int offset, @Nonnegative int limit, @NonNull final LiquidPayCallback<ResponseGetAddedMembershipCard> callback) {

        Call<ResponseGetAddedMembershipCard> call = liquidPayAPI.getAddedMembershipCard(config.apiKey,
                getBearerAccessToken(accessToken), offset, limit);

        call.enqueue(new Callback<ResponseGetAddedMembershipCard>() {
            @Override
            public void onResponse(Call<ResponseGetAddedMembershipCard> call, Response<ResponseGetAddedMembershipCard> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseGetAddedMembershipCard> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void addMembershipCard(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String membershipCardId, @NonNull String gender, @NonNull String dateOfBirth, @NonNull final LiquidPayCallback<ResponseAddMembershipCard> callback) {

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();

        treeMap.put("id", membershipCardId);
        treeMap.put("gender", gender);
        treeMap.put("dob", dateOfBirth);

        String sign = null;

        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseAddMembershipCard> call = liquidPayAPI.addMembershipCard(config.apiKey, getBearerAccessToken(accessToken),
                membershipCardId, gender, dateOfBirth, nonce, sign);

        call.enqueue(new Callback<ResponseAddMembershipCard>() {
            @Override
            public void onResponse(Call<ResponseAddMembershipCard> call, Response<ResponseAddMembershipCard> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseAddMembershipCard> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });

    }


    @Override
    public void cancelPayment(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String paymentId, @NonNull final LiquidPayCallback<ResponseCancelPayment> callback) {

        Call<ResponseCancelPayment> call = liquidPayAPI.cancelPayment(config.apiKey, getBearerAccessToken(accessToken), paymentId, paymentId);
        call.enqueue(new Callback<ResponseCancelPayment>() {

            @Override
            public void onResponse(Call<ResponseCancelPayment> call, Response<ResponseCancelPayment> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseCancelPayment> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });

    }

    @Override
    public void getPaymentStatus(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String paymentId, final LiquidPayCallback<ResponsePayment> callback) {

        Call<ResponsePayment> call = liquidPayAPI.getPaymentStatus(config.apiKey, getBearerAccessToken(accessToken), paymentId);
        call.enqueue(new Callback<ResponsePayment>() {
            @Override
            public void onResponse(Call<ResponsePayment> call, Response<ResponsePayment> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponsePayment> call, Throwable t) {
                callback.onError(t, t.getMessage());

            }
        });
    }

    @Override
    public void getPaymentOptions(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String paymentId, @NonNull final LiquidPayCallback<ResponseGetPaymentOptions> callback) {

        Call<ResponseGetPaymentOptions> call = liquidPayAPI.getPaymentOptions(config.apiKey, getBearerAccessToken(accessToken), paymentId);

        call.enqueue(new Callback<ResponseGetPaymentOptions>() {
            @Override
            public void onResponse(Call<ResponseGetPaymentOptions> call, Response<ResponseGetPaymentOptions> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseGetPaymentOptions> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void payUsingStoredValueCard(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String paymentId,
                                        @NonNull String source, @NonNull final LiquidPayCallback<ResponsePayment> callback) {

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();

        treeMap.put("source", source);

        String sign = null;

        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponsePayment> call = liquidPayAPI.payUsingStoredValueCard(config.apiKey, getBearerAccessToken(accessToken), paymentId, source, nonce, sign);
        call.enqueue(new Callback<ResponsePayment>() {
            @Override
            public void onResponse(Call<ResponsePayment> call, Response<ResponsePayment> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponsePayment> call, Throwable t) {
                callback.onError(t, t.getMessage());

            }
        });
    }

    @Override
    public void payUsingCreditOrDebitCard(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String paymentId,
                                          @NonNull String source, @NonNull final LiquidPayCallback<ResponsePayment> callback) {

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();

        treeMap.put("source", source);

        String sign = null;

        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponsePayment> call = liquidPayAPI.payUsingCreditOrDebitCard(config.apiKey, getBearerAccessToken(accessToken), paymentId, source, nonce, sign);
        call.enqueue(new Callback<ResponsePayment>() {
            @Override
            public void onResponse(Call<ResponsePayment> call, Response<ResponsePayment> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponsePayment> call, Throwable t) {
                callback.onError(t, t.getMessage());

            }
        });
    }

    @Deprecated
    @Override
    public void addCard(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String cardNumber,
                        @NonNull String cardHolderName, @NonNull Date expiry, @NonNull String cvv, @NonNull final LiquidPayCallback<BaseCard> callback) {


        String expiryYear = liquidDateFormatter.getYearYYYY(expiry);
        String expiryMonth = liquidDateFormatter.getMonthMM(expiry);
        String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("pan", cardNumber);
        treeMap.put("card_holder", cardHolderName);
        treeMap.put("cvv", cvv);
        treeMap.put("expiry_year", expiryYear);
        treeMap.put("expiry_month", expiryMonth);

        String sign = null;
        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        final Gson gson = new Gson();
        Call<String> call = liquidPayAPI.addCard(config.apiKey, getBearerAccessToken(accessToken), cardNumber, cardHolderName, expiryYear, expiryMonth, cvv, nonce, sign);
        call.enqueue(new Callback<String>() {
                         @Override
                         public void onResponse(Call<String> call, Response<String> response) {

                             if (response.code() == 200) {  //Non-3d card success
                                 final String bodyString = response.body();
                                 Log.d(RepositoryImpl.class.getSimpleName(), "200:Non-3D card: response=" + bodyString);

                                 BaseCard baseCard = convertJsonType(bodyString);
                                 Log.d(RepositoryImpl.class.getSimpleName(), "200:Non-3D card: converted class=" + baseCard.toString());


//                                 CardNon3D cardNon3D = gson.fromJson(bodyString, CardNon3D.class);
                                 callback.onSuccess(baseCard);
                             } else if (response.code() == 307) { //3d guess
                                 final String errorBody;
                                 try {
                                     errorBody = response.errorBody().string();
                                     Log.d(RepositoryImpl.class.getSimpleName(), "307: 3D card: rspnose=" + errorBody);
                                     BaseCard baseCard = convertJsonType(errorBody);
                                     Log.d(RepositoryImpl.class.getSimpleName(), "307: 3D card: convertedClass=" + baseCard.toString());


//                                 Card3D card3D = gson.fromJson(errorBody, Card3D.class);
                                     callback.onSuccess(baseCard);
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }


                             } else {

                                 List<Error> errors = errorUtils.parseErrorList(response);
                                 callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                             }
                         }

                         @Override
                         public void onFailure(Call<String> call, Throwable t) {
                             callback.onError(t, t.getMessage());
                         }
                     }

        );


    }

    @Deprecated
    @Override
    public void addCardLoop(@NonNull LiquidPayConfig config, @NonNull String
            accessToken, @NonNull String cardNumber,
                            @NonNull String cardHolderName, @NonNull Date expiry, @NonNull String cvv,
                            @NonNull final LiquidPayCallback<BaseCard> callback) {


        /*
        String expiryYear = liquidDateFormatter.getYearYYYY(expiry);
        String expiryMonth = liquidDateFormatter.getMonthMM(expiry);
        String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("pan", cardNumber);
        treeMap.put("card_holder", cardHolderName);
        treeMap.put("cvv", cvv);
        treeMap.put("expiry_year", expiryYear);
        treeMap.put("expiry_month", expiryMonth);

        String sign = null;
        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }


        final String url = "https://liquidpay-dev.apigee.net/openapi/v1/consumer/wallet/paymentcards";
        asyncHttpClient.addHeader("Liquid-Api-Key", config.apiKey);
        asyncHttpClient.addHeader("Authorization", getBearerAccessToken(accessToken));

        RequestParams requestParam = new RequestParams();
        requestParam.add("pan", cardNumber);
        requestParam.add("card_holder", cardHolderName);
        requestParam.add("cvv", cvv);
        requestParam.add("expiry_year", expiryYear);
        requestParam.add("expiry_month", expiryMonth);
        requestParam.add("nonce", nonce);
        requestParam.add("sign", sign);

        final Gson gson = new Gson();
        asyncHttpClient.post(url, requestParam, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                if (statusCode == 200) { //Non-3D card success
                    Log.d("sohail", "addCardLoop:response:onSuccess=" + response.toString());
                    CardNon3D cardNon3D = gson.fromJson(response.toString(), CardNon3D.class);
                    callback.onSuccess(cardNon3D);

                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.d("sohail", "addCardLoop:response:onFailure(JSONObject):code=" + statusCode + "throwable=" + throwable.toString() + "  errorResponse=" + errorResponse.toString());

                if (statusCode == 307 && errorResponse != null) { //3D Card success

                    Card3D card3D = gson.fromJson(errorResponse.toString(), Card3D.class);
                    callback.onSuccess(card3D);
                    return;
                } else if (throwable instanceof HttpResponseException && errorResponse != null) { //server error

                    //{"type":"error","errors":[{"code":"internal_server_error","message":"We currently cannot process your request.  Please contact contact@liquidpay.com if the problem persists."}]}
                    ErrorList errors = gson.fromJson(errorResponse.toString(), ErrorList.class);
                    Log.d("sohail", "addCardLoop: parsedErros=" + errors.toString());
                    callback.onError(new LiquidPayException(errors.errors, "check error list"), "LiquidPayException: check error list");

                } else { //network error
                    callback.onError(throwable, throwable.getMessage());

                }
            }
        });*/


    }


    private BaseCard convertVisGsonObject(String jsonBody) {

        Log.d(RepositoryImpl.class.getSimpleName(), "converGsonObject: jsonBody=" + jsonBody);

        Gson gson = new Gson();
        try {
            JSONObject jsonObject = new JSONObject(jsonBody);

            final String type = jsonObject.getString("type");

            if (type.equalsIgnoreCase(BaseCard.TYPE_3D)) {
                Log.d(RepositoryImpl.class.getSimpleName(), "converGsonObject: 3d card");
                Card3D card3D = gson.fromJson(jsonBody.toString(), Card3D.class);
                return card3D;
            }

            if (type.equalsIgnoreCase(BaseCard.TYPE_NON_3D)) {
                Log.d(RepositoryImpl.class.getSimpleName(), "converGsonObject: Non 3d card");
                CardNon3D cardNon3D = gson.fromJson(jsonBody.toString(), CardNon3D.class);
                return cardNon3D;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(RepositoryImpl.class.getSimpleName(), "can't parse returning null");
        return null;
    }

    /**
     * Converts polymorphic types to Card3D and CardNon3D
     *
     * @param obj
     * @return
     */
    private BaseCard convertJsonType(String obj) {

        final String jsonBody = new String(obj);
        Log.d(RepositoryImpl.class.getSimpleName(), "converJsonType: json=" + jsonBody);

        final TypeToken<BaseCard> requestListTypeToken = new TypeToken<BaseCard>() {
        };

        RuntimeTypeAdapterFactory<BaseCard> cardAdapterFactory
                = RuntimeTypeAdapterFactory.of(BaseCard.class, "type");

        cardAdapterFactory.registerSubtype(Card3D.class, BaseCard.TYPE_3D);
        cardAdapterFactory.registerSubtype(CardNon3D.class, BaseCard.TYPE_NON_3D);


        // add the polymorphic specialization
        final Gson gson = new GsonBuilder().registerTypeAdapterFactory(cardAdapterFactory).create();
        return gson.fromJson(jsonBody, requestListTypeToken.getType());


    }

    @Override
    public void addStoredValueCard(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String nationality,
                                   @NonNull String id, @NonNull String identifier,
                                   @NonNull final LiquidPayCallback<ResponseAddStoredValueCard> callback) {


        String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("nationality", nationality);
        treeMap.put("id", id);
        treeMap.put("identifier", identifier);
        String sign = null;
        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseAddStoredValueCard> call = liquidPayAPI.addStoredValueCard(config.apiKey, getBearerAccessToken(accessToken), nationality, id, identifier, nonce, sign);
        call.enqueue(new Callback<ResponseAddStoredValueCard>() {
            @Override
            public void onResponse(Call<ResponseAddStoredValueCard> call, Response<ResponseAddStoredValueCard> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseAddStoredValueCard> call, Throwable t) {
                callback.onError(t, t.getMessage());

            }
        });
    }

    @Override
    public void topUpStoredValueCard(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String recipient,
                                     @NonNull String originator, @NonNull String topupAmount, @NonNull final LiquidPayCallback<ResponseTopUpStoredValueCard> callback) {

        String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();

        treeMap.put("originator", originator);
        treeMap.put("amount", topupAmount);
        String sign = null;
        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseTopUpStoredValueCard> call = liquidPayAPI.topUpStoredValueCard(config.apiKey, getBearerAccessToken(accessToken), recipient, originator, topupAmount, nonce, sign);
        call.enqueue(new Callback<ResponseTopUpStoredValueCard>() {
            @Override
            public void onResponse(Call<ResponseTopUpStoredValueCard> call, Response<ResponseTopUpStoredValueCard> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseTopUpStoredValueCard> call, Throwable t) {
                callback.onError(t, t.getMessage());

            }
        });
    }

    @Override
    public void getStoredValueCardTopUpOptions(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                               @NonNull String recipient, @NonNull final LiquidPayCallback<ResponseGetStoredValueCardTopupOptions> callback) {

        Call<ResponseGetStoredValueCardTopupOptions> call = liquidPayAPI.getStoredValueCardTopUpOptions(config.apiKey, getBearerAccessToken(accessToken), recipient);
        call.enqueue(new Callback<ResponseGetStoredValueCardTopupOptions>() {
            @Override
            public void onResponse(Call<ResponseGetStoredValueCardTopupOptions> call, Response<ResponseGetStoredValueCardTopupOptions> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseGetStoredValueCardTopupOptions> call, Throwable t) {
                callback.onError(t, t.getMessage());

            }
        });
    }

    @Override
    public void getAddedStoredValueCard(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                        @NonNull final LiquidPayCallback<ResponseGetStoredValueCard> callback) {

        Call<ResponseGetStoredValueCard> call = liquidPayAPI.getAddedStoredValueCard(config.apiKey, getBearerAccessToken(accessToken));
        call.enqueue(new Callback<ResponseGetStoredValueCard>() {
            @Override
            public void onResponse(Call<ResponseGetStoredValueCard> call, Response<ResponseGetStoredValueCard> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseGetStoredValueCard> call, Throwable t) {
                callback.onError(t, t.getMessage());

            }
        });
    }

    @Override
    public void getStoredValueCardRequiredDetails(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                                  @NonNull String id, @NonNull final LiquidPayCallback<ResponseGetStoredValueCardRequiredDetails> callback) {

        Call<ResponseGetStoredValueCardRequiredDetails> call = liquidPayAPI.getStoredValueCardRequiredDetails(config.apiKey, getBearerAccessToken(accessToken), id);
        call.enqueue(new Callback<ResponseGetStoredValueCardRequiredDetails>() {
            @Override
            public void onResponse(Call<ResponseGetStoredValueCardRequiredDetails> call, Response<ResponseGetStoredValueCardRequiredDetails> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }


            }

            @Override
            public void onFailure(Call<ResponseGetStoredValueCardRequiredDetails> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void getAvailableStoredValueCard(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                            @Nonnegative int offset, @Nonnegative int limit,
                                            @NonNull final LiquidPayCallback<ResponseAvailableStoredValueCard> callback) {

        Call<ResponseAvailableStoredValueCard> call = liquidPayAPI.getAvailableStoredValueCard(config.apiKey, getBearerAccessToken(accessToken),
                offset, limit);

        call.enqueue(new Callback<ResponseAvailableStoredValueCard>() {
            @Override
            public void onResponse(Call<ResponseAvailableStoredValueCard> call, Response<ResponseAvailableStoredValueCard> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseAvailableStoredValueCard> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });

    }

    @Override
    public void getCardBinDetails(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String cardNumber, @NonNull final LiquidPayCallback<ResponseCardBinDetails> callback) {

        Call<ResponseCardBinDetails> call = liquidPayAPI.getCardBinDetails(config.apiKey, getBearerAccessToken(accessToken), cardNumber);
        call.enqueue(new Callback<ResponseCardBinDetails>() {
            @Override
            public void onResponse(Call<ResponseCardBinDetails> call, Response<ResponseCardBinDetails> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseCardBinDetails> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });

    }

    @Override
    public void checkCardStatus(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String sourceId, @NonNull final LiquidPayCallback<ResponseCheckCardStatus> callback) {

        Call<ResponseCheckCardStatus> call = liquidPayAPI.checkCardStatus(config.apiKey, getBearerAccessToken(accessToken), sourceId);
        call.enqueue(new Callback<ResponseCheckCardStatus>() {
            @Override
            public void onResponse(Call<ResponseCheckCardStatus> call, Response<ResponseCheckCardStatus> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseCheckCardStatus> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }


    @Override
    public void submitRegistrationDetails(@NonNull LiquidPayConfig config, @NonNull String emailAddress,
                                          @NonNull String dialingCode, @NonNull String mobileNumber, @NonNull String otp,
                                          @NonNull String firstName, @NonNull String lastName, @NonNull String password,
                                          @NonNull String registeredSource, @NonNull String promoCode, @NonNull final LiquidPayCallback<ResponseLogin> callback) {

        LiquidPayConfig.DeviceInfo deviceInfo = config.deviceInfo;

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp

        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("email_address", emailAddress);
        treeMap.put("dialing_code", dialingCode);
        treeMap.put("mobile_number", mobileNumber);
        treeMap.put("otp", otp);
        treeMap.put("first_name", firstName);
        treeMap.put("last_name", lastName);
        treeMap.put("password", password);
        treeMap.put("device_id", deviceInfo.gcmId);
        treeMap.put("device_uid", deviceInfo.deviceUID);
        treeMap.put("device_model", deviceInfo.deviceModel);
        treeMap.put("device_os", deviceInfo.deviceOS);
        treeMap.put("device_type", deviceInfo.deviceType);
        treeMap.put("registered_source", registeredSource);
        treeMap.put("promo_code", promoCode);


        String sign = null;
        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseLogin> call = liquidPayAPI.submitRegistrationDetails(config.apiKey, emailAddress, dialingCode, mobileNumber, otp, firstName, lastName, password,
                deviceInfo.gcmId, deviceInfo.deviceUID, deviceInfo.deviceModel, deviceInfo.deviceOS, deviceInfo.deviceType, registeredSource, promoCode, nonce, sign);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }


            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void sendOTP(@NonNull LiquidPayConfig config, @NonNull String dialingCode, @NonNull String mobileNumber,
                        @NonNull final LiquidPayCallback<ResponseSendOTP> callback) {

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("dialing_code", dialingCode);
        treeMap.put("mobile_number", mobileNumber);

        String sign = null;
        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseSendOTP> call = liquidPayAPI.sendOTP(config.apiKey, dialingCode, mobileNumber, nonce, sign);
        call.enqueue(new Callback<ResponseSendOTP>() {
            @Override
            public void onResponse(Call<ResponseSendOTP> call, Response<ResponseSendOTP> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }

            }

            @Override
            public void onFailure(Call<ResponseSendOTP> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });

    }


    @Override
    public void validateUserDetails(LiquidPayConfig config, String mobile, String email, String promoCode, String firstName,
                                    String lastName, String dialingCode, final LiquidPayCallback<ResponseValidateUserDetails> callback) {

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("mobile_number", mobile);
        treeMap.put("email_address", email);
        treeMap.put("promo_code", promoCode);
        treeMap.put("first_name", firstName);
        treeMap.put("last_name", lastName);

        treeMap.put("dialing_code", dialingCode);

        String sign = null;
        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (UnsupportedEncodingException e) {
            callback.onError(e, e.getMessage());
            return;
        } catch (NoSuchAlgorithmException e) {
            callback.onError(e, e.getMessage());
            return;
        }


        Call<ResponseValidateUserDetails> call = liquidPayAPI.validateUserDetails(config.apiKey, mobile, email, promoCode, firstName, lastName, dialingCode, nonce, sign);
        call.enqueue(new Callback<ResponseValidateUserDetails>() {
            @Override
            public void onResponse(Call<ResponseValidateUserDetails> call, Response<ResponseValidateUserDetails> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {

                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"), "LiquidPayException: check error list");
                }

            }

            @Override
            public void onFailure(Call<ResponseValidateUserDetails> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });

    }

    @Override
    public void retrievePaymentListEligibleMembershipCard(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull String source,
            @NonNull String payee,
            @NonNull String currencyCode,
            @Nonnegative double amount,
            @Nonnegative int offset,
            @Nonnegative int limit,
            @NonNull final LiquidPayCallback<ResponsePaymentListEligibleMembershipCard> callback) {

        Call<ResponsePaymentListEligibleMembershipCard> call =
                liquidPayAPI.retrievePaymentListEligibleMembershipCard(
                        config.apiKey,
                        getBearerAccessToken(accessToken),
                        source,
                        payee,
                        currencyCode,
                        amount,
                        offset,
                        limit);

        call.enqueue(new Callback<ResponsePaymentListEligibleMembershipCard>() {
            @Override
            public void onResponse(
                    Call<ResponsePaymentListEligibleMembershipCard> call,
                    Response<ResponsePaymentListEligibleMembershipCard> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"),
                            "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(
                    Call<ResponsePaymentListEligibleMembershipCard> call, Throwable t) {

                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void retrievePaymentListEligibleVoucher(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull String source,
            @NonNull String payee,
            @NonNull String currencyCode,
            @Nonnegative double amount,
            @NonNull final LiquidPayCallback<ResponsePaymentListEligibleVoucher> callback) {

        Call<ResponsePaymentListEligibleVoucher> call =
                liquidPayAPI.retrievePaymentListEligibleVoucher(
                        config.apiKey,
                        getBearerAccessToken(accessToken),
                        source,
                        payee,
                        currencyCode,
                        amount);

        call.enqueue(new Callback<ResponsePaymentListEligibleVoucher>() {
            @Override
            public void onResponse(
                    Call<ResponsePaymentListEligibleVoucher> call,
                    Response<ResponsePaymentListEligibleVoucher> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"),
                            "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponsePaymentListEligibleVoucher> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void saveVoucherFromGiftboxToWallet(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull String id,
            @NonNull final LiquidPayCallback<ResponseWalletSaveVoucher> callback) {

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("voucher_ids", id);

        String sign;
        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (Exception e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseWalletSaveVoucher> call =
                liquidPayAPI.saveVoucherFromGiftboxToWallet(
                        config.apiKey,
                        getBearerAccessToken(accessToken),
                        nonce,
                        sign,
                        id);

        call.enqueue(new Callback<ResponseWalletSaveVoucher>() {
            @Override public void onResponse(
                    Call<ResponseWalletSaveVoucher> call,
                    Response<ResponseWalletSaveVoucher> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"),
                            "LiquidPayException: check error list");
                }
            }

            @Override public void onFailure(Call<ResponseWalletSaveVoucher> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void addBrandVoucherToWallet(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull String id,
            @NonNull final LiquidPayCallback<ResponseWalletAddBrandVoucher> callback) {

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("voucher_ids", id);

        String sign;
        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (Exception e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseWalletAddBrandVoucher> call =
                liquidPayAPI.addBrandVoucherToWallet(
                        config.apiKey,
                        getBearerAccessToken(accessToken),
                        nonce,
                        sign,
                        id);

        call.enqueue(new Callback<ResponseWalletAddBrandVoucher>() {
            @Override
            public void onResponse(
                    Call<ResponseWalletAddBrandVoucher> call,
                    Response<ResponseWalletAddBrandVoucher> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"),
                            "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseWalletAddBrandVoucher> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void getVoucherListOfWallet(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull final LiquidPayCallback<ResponseWalletVoucherList> callback) {

        Call<ResponseWalletVoucherList> call = liquidPayAPI.getVoucherListOfWallet(
                config.apiKey, getBearerAccessToken(accessToken));

        call.enqueue(new Callback<ResponseWalletVoucherList>() {
            @Override
            public void onResponse(
                    Call<ResponseWalletVoucherList> call,
                    Response<ResponseWalletVoucherList> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"),
                            "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseWalletVoucherList> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void sendVoucherToFriend(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull String id,
            @NonNull String mobile,
            @NonNull final LiquidPayCallback<ResponseVoucherSendToFriend> callback) {

        final String nonce = String.valueOf(new Date().getTime()); //compute nonce unix timestamp
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("voucher_ids", id);
        treeMap.put("destination_mobile", mobile);

        String sign;
        try {
            sign = securityUtils.getSignature(treeMap, nonce, config.apiSecret);
        } catch (Exception e) {
            callback.onError(e, e.getMessage());
            return;
        }

        Call<ResponseVoucherSendToFriend> call =
                liquidPayAPI.sendVoucherToFriend(
                        config.apiKey,
                        getBearerAccessToken(accessToken),
                        nonce,
                        sign,
                        id,
                        mobile);

        call.enqueue(new Callback<ResponseVoucherSendToFriend>() {
            @Override
            public void onResponse(
                    Call<ResponseVoucherSendToFriend> call,
                    Response<ResponseVoucherSendToFriend> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"),
                            "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseVoucherSendToFriend> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void getVoucherGiftBoxListing(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull final LiquidPayCallback<ResponseVoucherGiftBox> callback) {

        Call<ResponseVoucherGiftBox> call =
                liquidPayAPI.getVoucherGiftBoxListing(
                        config.apiKey,
                        getBearerAccessToken(accessToken));

        call.enqueue(new Callback<ResponseVoucherGiftBox>() {
            @Override
            public void onResponse(
                    Call<ResponseVoucherGiftBox> call,
                    Response<ResponseVoucherGiftBox> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"),
                            "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseVoucherGiftBox> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }

    @Override
    public void getPaymentCardsLinkToVirtualAccount(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull String virtualAccountId,
            @NonNull final LiquidPayCallback<ResponseVirtualAccountPaymentCards> callback) {

        Call<ResponseVirtualAccountPaymentCards> call =
                liquidPayAPI.getPaymentCardsLinkToVirtualAccount(
                        config.apiKey,
                        getBearerAccessToken(accessToken),
                        virtualAccountId);

        call.enqueue(new Callback<ResponseVirtualAccountPaymentCards>() {
            @Override
            public void onResponse(
                    Call<ResponseVirtualAccountPaymentCards> call,
                    Response<ResponseVirtualAccountPaymentCards> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    List<Error> errors = errorUtils.parseErrorList(response);
                    callback.onError(new LiquidPayException(errors, "check error list"),
                            "LiquidPayException: check error list");
                }
            }

            @Override
            public void onFailure(Call<ResponseVirtualAccountPaymentCards> call, Throwable t) {
                callback.onError(t, t.getMessage());
            }
        });
    }
}
