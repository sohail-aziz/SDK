package com.korvac.liquidpay.sdk.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.korvac.liquidpay.sdk.data.response.BaseCard;
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
import com.korvac.liquidpay.sdk.domain.AddCardUseCase;
import com.korvac.liquidpay.sdk.domain.AddMembershipCardUseCase;
import com.korvac.liquidpay.sdk.domain.AddStoredValueCardUseCase;
import com.korvac.liquidpay.sdk.domain.AddVirtualAccountUseCase;
import com.korvac.liquidpay.sdk.domain.CancelPaymentUseCase;
import com.korvac.liquidpay.sdk.domain.ChangePasswordUseCase;
import com.korvac.liquidpay.sdk.domain.CheckCardStatusUseCase;
import com.korvac.liquidpay.sdk.domain.CheckEmailVerificationUseCase;
import com.korvac.liquidpay.sdk.domain.CreatePaymentUseCase;
import com.korvac.liquidpay.sdk.domain.DeleteCardUseCase;
import com.korvac.liquidpay.sdk.domain.DeleteVirtualAccountUseCase;
import com.korvac.liquidpay.sdk.domain.ForgotPasswordUseCase;
import com.korvac.liquidpay.sdk.domain.GetAddedMembershipCardUseCase;
import com.korvac.liquidpay.sdk.domain.GetAddedStoredValueCardUseCase;
import com.korvac.liquidpay.sdk.domain.GetAddedVirtualAccountUseCase;
import com.korvac.liquidpay.sdk.domain.GetAvailableMembershipCardUseCase;
import com.korvac.liquidpay.sdk.domain.GetAvailableStoredValueCardUseCase;
import com.korvac.liquidpay.sdk.domain.GetAvailableVirtualAccountUseCase;
import com.korvac.liquidpay.sdk.domain.GetCardBinDetailsUseCase;
import com.korvac.liquidpay.sdk.domain.GetPaymentOptionsUseCase;
import com.korvac.liquidpay.sdk.domain.GetPaymentStatusUseCase;
import com.korvac.liquidpay.sdk.domain.GetStoredValueCardRequiredDetailsUseCase;
import com.korvac.liquidpay.sdk.domain.GetStoredValueCardTopUpOptionsUseCase;
import com.korvac.liquidpay.sdk.domain.GetVoucherGiftBoxListingUseCase;
import com.korvac.liquidpay.sdk.domain.LinkCardToVirtualAccountUseCase;
import com.korvac.liquidpay.sdk.domain.ListCardsUseCase;
import com.korvac.liquidpay.sdk.domain.LoginUseCase;
import com.korvac.liquidpay.sdk.domain.PayUsingCreditOrDebitCardUseCase;
import com.korvac.liquidpay.sdk.domain.PayUsingStoredValueCardUseCase;
import com.korvac.liquidpay.sdk.domain.PayUsingVirtualAccountUseCase;
import com.korvac.liquidpay.sdk.domain.RetrieveMembershipCardRequiredInfoUseCase;
import com.korvac.liquidpay.sdk.domain.RetrievePaymentListEligibleMembershipCardUseCase;
import com.korvac.liquidpay.sdk.domain.RetrievePaymentListEligibleVoucherUseCase;
import com.korvac.liquidpay.sdk.domain.RetrieveVirtualAccountRequiredInfoUseCase;
import com.korvac.liquidpay.sdk.domain.SendOTPUseCase;
import com.korvac.liquidpay.sdk.domain.SendVerificationEmailUseCase;
import com.korvac.liquidpay.sdk.domain.SubmitRegistrationDetailsUseCase;
import com.korvac.liquidpay.sdk.domain.TopUpStoredValueCardUseCase;
import com.korvac.liquidpay.sdk.domain.UnLinkCardTFromVirtualAccountUseCase;
import com.korvac.liquidpay.sdk.domain.ValidateUserDetailsUseCase;
import com.korvac.liquidpay.sdk.domain.VirtualAccountGetLinkPaymentCardsUseCase;
import com.korvac.liquidpay.sdk.domain.VoucherSendToFriendUseCase;
import com.korvac.liquidpay.sdk.domain.WalletAddBrandVoucherUseCase;
import com.korvac.liquidpay.sdk.domain.WalletGetVoucherListUseCase;
import com.korvac.liquidpay.sdk.domain.WalletSaveVoucherFromGiftboxUseCase;
import com.korvac.liquidpay.sdk.exception.LiquidPayInvalidAccessTokenException;
import com.korvac.liquidpay.sdk.main.enums.RegisteredSource;
import com.korvac.liquidpay.sdk.main.enums.ServiceType;
import com.korvac.liquidpay.sdk.utils.PreConditions;

import java.util.Date;

import javax.annotation.Nonnegative;
import javax.inject.Inject;

/**
 * Main Service class to access
 * all the LiquidPay platform features
 * <p>
 * Created by sohail on 5/5/2017.
 */

public final class LiquidPay {


    @Inject
    LiquidPayPrefs liquidPayPrefs;

    private static LiquidPay instance;
    public boolean isInitialized = false;
    private static LiquidPayConfig config;


    @Inject
    LoginUseCase loginUseCase;
    @Inject
    CheckEmailVerificationUseCase checkEmailUseCase;
    @Inject
    SendVerificationEmailUseCase sendVerificationEmailUseCase;
    @Inject
    ValidateUserDetailsUseCase validateUserDetailsUseCase;
    @Inject
    SubmitRegistrationDetailsUseCase submitRegistrationDetailsUseCase;
    @Inject
    SendOTPUseCase sendOTPUseCase;
    @Inject
    CreatePaymentUseCase createPaymentUseCase;
    @Inject
    GetPaymentStatusUseCase getPaymentStatusUseCase;
    @Inject
    AddStoredValueCardUseCase addStoredValueCardUseCase;
    @Inject
    TopUpStoredValueCardUseCase topUpStoredValueCardUseCase;
    @Inject
    PayUsingStoredValueCardUseCase payUsingStoredValueCardUseCase;
    @Inject
    GetAddedStoredValueCardUseCase getAddedStoredValueCardUseCase;
    @Inject
    GetStoredValueCardRequiredDetailsUseCase getStoredValueCardRequiredDetailsUseCase;
    @Inject
    GetStoredValueCardTopUpOptionsUseCase getStoredValueCardTopUpOptionsUseCase;

    @Inject
    GetAvailableStoredValueCardUseCase getAvailableStoredValueCardUseCase;

    @Inject WalletSaveVoucherFromGiftboxUseCase walletSaveVoucherFromGiftboxUseCase;

    @Inject
    WalletAddBrandVoucherUseCase walletAddBrandVoucherUseCase;

    @Inject
    WalletGetVoucherListUseCase walletGetVoucherListUseCase;

    @Inject
    VoucherSendToFriendUseCase voucherSendToFriendUseCase;

    @Inject
    GetVoucherGiftBoxListingUseCase getVoucherGiftBoxListingUseCase;

    @Inject
    GetCardBinDetailsUseCase getCardBinDetailsUseCase;

    @Inject
    CheckCardStatusUseCase checkCardStatusUseCase;
    @Inject
    PayUsingCreditOrDebitCardUseCase payUsingCardUseCase;

    @Inject
    GetPaymentOptionsUseCase getPaymentOptionsUseCase;

    @Inject
    AddCardUseCase addCardUseCase;
    @Inject
    DeleteCardUseCase deleteCardUseCase;
    @Inject
    ListCardsUseCase listCardsUseCase;

    @Inject
    ForgotPasswordUseCase forgotPasswordUseCase;
    @Inject
    ChangePasswordUseCase changePasswordUseCase;

    @Inject
    CancelPaymentUseCase cancelPaymentUseCase;

    @Inject
    RetrieveVirtualAccountRequiredInfoUseCase retrieveVirtualAccountRequiredInfoUseCase;
    @Inject
    GetAvailableVirtualAccountUseCase getAvailableVirtualAccountUseCase;
    @Inject
    AddVirtualAccountUseCase addVirtualAccountUseCase;
    @Inject
    GetAddedVirtualAccountUseCase getAddedVirtualAccountUseCase;
    @Inject
    DeleteVirtualAccountUseCase deleteVirtualAccountUseCase;
    @Inject
    LinkCardToVirtualAccountUseCase linkCardToVirtualAccountUseCase;
    @Inject
    UnLinkCardTFromVirtualAccountUseCase unLinkCardTFromVirtualAccountUseCase;
    @Inject
    PayUsingVirtualAccountUseCase payUsingVirtualAccountUseCase;
    @Inject
    VirtualAccountGetLinkPaymentCardsUseCase virtualAccountGetLinkPaymentCardsUseCase;

    @Inject
    RetrievePaymentListEligibleMembershipCardUseCase retrievePaymentListEligibleMembershipCardUseCase;
    @Inject
    GetAvailableMembershipCardUseCase getAvailableMembershipCardUseCase;
    @Inject
    RetrievePaymentListEligibleVoucherUseCase retrievePaymentListEligibleVoucherUseCase;
    @Inject
    RetrieveMembershipCardRequiredInfoUseCase retrieveMembershipCardRequiredInfoUseCase;
    @Inject
    AddMembershipCardUseCase addMembershipCardUseCase;
    @Inject
    GetAddedMembershipCardUseCase getAddedMembershipCardUseCase;

    private Context context;

    private LiquidPay() {
    }


    public synchronized static LiquidPay getInstance() {

        if (instance == null) {
            instance = new LiquidPay();
        }

        return instance;
    }

    public LiquidPayConfig getConfig() {
        return config;
    }

    private String getAccessToken() {
        return liquidPayPrefs.getAccessToken();
    }

    /**
     * Checkes whether access token is valid or not
     *
     * @return true if access token is valid
     */
    public boolean isAccessTokenValid() {
        return !liquidPayPrefs.getAccessToken().isEmpty();
    }


    /**
     * Initializes LiquidPay SDK
     * <p>
     *
     * @param context Android  context
     * @param apiKey  Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param gcmId   Unique gcm Registration token for push notifications See <a href="https://developers.google.com/cloud-messaging/android/client#sample-register">Obtain a registration token</a>
     * @throws NullPointerException for null context, api key or secret
     * @deprecated As in SDK v2.0 use {{@link #init(Context, String, String, String, String, String, String)}} instead.
     */
    @Deprecated
    public void init(@NonNull Context context, @NonNull String apiKey, @NonNull String apiSecret, @Nullable String gcmId) {

        PreConditions.checkNotNull(context, "Context is null");
        PreConditions.checkNotNull(apiKey, "api key is null");
        PreConditions.checkNotNull(apiSecret, "api secret is null");

        DaggerWrapper.getComponent(context).inject(this);

        config = new LiquidPayConfig(context, apiKey, apiSecret, gcmId);
        isInitialized = true;

        this.context = context;
    }

    /**
     * Initializes LiquidPay SDK
     * <p>
     *
     * @param context      Android  context
     * @param apiKey       Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param gcmId        Unique gcm Registration token for push notifications See <a href="https://developers.google.com/cloud-messaging/android/client#sample-register">Obtain a registration token</a>
     * @param state        Unique state provided by Liquid Pay
     * @param responseType Unique response type provided by Liquid Pay
     * @param redirectUri  Registered/provided redirect URI for Single SignOn
     * @throws NullPointerException for null context, api key, secret, state, response type or redirect URI
     */

    public void init(@NonNull Context context, @NonNull String apiKey, @NonNull String apiSecret, @Nullable String gcmId, String state, String responseType, String redirectUri) {

        PreConditions.checkNotNull(context, "Context is null");
        PreConditions.checkNotNull(apiKey, "api key is null");
        PreConditions.checkNotNull(apiSecret, "api secret is null");
        PreConditions.checkNotNull(state, "state is null");
        PreConditions.checkNotNull(responseType, "response type is null");
        PreConditions.checkNotNull(redirectUri, "redirect URI is null");

        DaggerWrapper.getComponent(context).inject(this);

        config = new LiquidPayConfig(context, apiKey, apiSecret, gcmId, state, responseType, redirectUri);
        isInitialized = true;

        this.context = context;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    /**
     * Starts OAuth login flow
     * returns result in onActivityResult with following extras
     * <p>
     * RESULT_CANCELLED
     * <ul>
     * <li>code:  error code [String]</li>
     * <li>error: error message [String]</li>
     * </ul>
     * <p>
     * RESULT_OK
     * <ul>
     * <li>response: success response see {@link ResponseLogin}</li>
     * </ul>
     *
     * @param activity    activity context
     * @param requestCode request code
     * @param scopes      one or more scopes defined in {@link LiquidPayScope}
     * @throws NullPointerException     when activity or scopes are null
     * @throws IllegalArgumentException when no scope are provided
     */
    public void startOAuthLogin(Activity activity, int requestCode, String[] scopes) {

        PreConditions.checkNotNull(activity, "activity context is null");
        PreConditions.checkNotNull(scopes, "scopes array is null");

        PreConditions.checkArgument(scopes.length > 0, "please provide at least one scope");


        Intent loginActivity = new Intent(this.context, LoginActivity.class);
        loginActivity.putExtra(LoginActivity.KEY_SCOPE_ARRAY, scopes);

        activity.startActivityForResult(loginActivity, requestCode);

    }

    /**
     * Forgot Password, send reset password to user email
     * <p>
     * call {@link #init(Context, String, String, String, String, String, String)} before calling this method
     *
     * @param email                  User email address
     * @param forgotPasswordListener result callback {@link ResponseForgotPassword}
     * @throws IllegalStateException when init is not called first
     * @throws NullPointerException  when arguments are null
     */
    public void forgotPassword(@NonNull String email, final LiquidPayCallback<ResponseForgotPassword> forgotPasswordListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkNotNull(email, "Email is null");
        PreConditions.checkNotNull(forgotPasswordListener, "forgotPasswordListener is null");

        forgotPasswordUseCase.execute(config, new ForgotPasswordUseCase.Params(email, forgotPasswordListener));
    }

    /**
     * Change Password
     * <p>
     * call {@link #init(Context, String, String, String, String, String, String)} before calling this method
     *
     * @param oldPassword            User old password
     * @param newPassword            User new password
     * @param changePasswordListener result callback {@link ResponseChangePassword}
     * @throws IllegalStateException                when init is not called first
     * @throws NullPointerException                 when arguments are null
     * @throws LiquidPayInvalidAccessTokenException exception if session expired
     */
    public void changePassword(@NonNull String oldPassword, @NonNull String newPassword, @NonNull LiquidPayCallback<ResponseChangePassword> changePasswordListener) {
        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkNotNull(oldPassword, "oldPassword is null");
        PreConditions.checkNotNull(newPassword, "newPassword is null");
        PreConditions.checkNotNull(changePasswordListener, "changePasswordListener is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        changePasswordUseCase.execute(config, getAccessToken(), new ChangePasswordUseCase.Params(oldPassword, newPassword, changePasswordListener));
    }

    /**
     * Logs In using email and password
     * <p>
     * call {@link #init(Context, String, String, String)} before calling this method
     *
     * @param userName      user email
     * @param password      user password
     * @param loginListener result callback {@link LiquidPayCallback}
     * @throws IllegalStateException when init is not called first
     * @throws NullPointerException  when arguments are null
     * @deprecated As in SDK v2.0 use {@link #startOAuthLogin(Activity, int, String[])} instead
     */
    @Deprecated
    public void loginWithPassword(@NonNull String userName, @NonNull String password, @NonNull final LiquidPayCallback<ResponseLogin> loginListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkNotNull(userName, "userName is null");
        PreConditions.checkNotNull(password, "password is null");
        PreConditions.checkNotNull(loginListener, "loginListener is null");

        loginUseCase.execute(config, new LoginUseCase.Params(userName, password, loginListener));
    }

    /**
     * Checks if registraion email has been verified or not
     * <p>
     * Call {@link #init(Context, String, String, String)} before calling this method
     *
     * @param checkEmailVerificationListener result callback{@link LiquidPayCallback}
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     */
    public void checkEmailVerificationStatus(@NonNull final LiquidPayCallback<ResponseCheckEmailVerification> checkEmailVerificationListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkNotNull(checkEmailVerificationListener, "checkEmailVerificationStatus is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("access token not valid, please loginWithPassword again");
        }

        checkEmailUseCase.execute(config, getAccessToken(), checkEmailVerificationListener);

    }

    /**
     * Re-sends Email verification link for registration completion
     * call {@link #submitRegistrationDetails(String, String, String, String, String, String, String, String, String, LiquidPayCallback)} before calling this
     * check if access token is valid before calling this method {@link #isAccessTokenValid()}
     * <p>
     *
     * @param emailAddress            email address
     * @param forceUpdate             true if registered email address needs to be updated
     * @param sendVerifyEmailListener result callback
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     * @throws IllegalArgumentException             if email address is not valid
     */
    public void resendEmailVerification(@NonNull final String emailAddress, final boolean forceUpdate, @NonNull final LiquidPayCallback<ResponseSendVerifyEmail> sendVerifyEmailListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkArgument(Validator.isValidEmail(emailAddress), "Invalid EmailAddress");

        PreConditions.checkNotNull(sendVerifyEmailListener, "sendVerifyEmailListener is null");


        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("access token not valid, please loginWithPassword again");
        }

        sendVerificationEmailUseCase.execute(config, getAccessToken(), new SendVerificationEmailUseCase.Params(emailAddress, forceUpdate, sendVerifyEmailListener));

    }

    /**
     * Validates user details.
     * <p>
     * Call {@link #init(Context, String, String, String)} before calling this method
     *
     * @param mobile           mobile number
     * @param email            email address
     * @param promoCode        promotional code
     * @param firstName        user's first name
     * @param lastName         user's last name
     * @param dialingCode      mobile country code e.g 65
     * @param validateListener result callback {@link LiquidPayCallback}
     * @throws IllegalStateException    if SDK not initialized
     * @throws NullPointerException     if arguments are null
     * @throws IllegalArgumentException if email address, mobile or dialing code is not valid
     */
    public void validateRegistrationDetails(@NonNull String mobile, @NonNull String email, @NonNull String promoCode,
                                            @NonNull String firstName, @NonNull String lastName,
                                            @NonNull String dialingCode, @NonNull final LiquidPayCallback<ResponseValidateUserDetails> validateListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.getInstance().init() first");

        PreConditions.checkArgument(Validator.isValidNumber(mobile), "invalid mobile number");
        PreConditions.checkArgument(Validator.isValidEmail(email), "invalid email address");
        PreConditions.checkArgument(Validator.isValidNumber(dialingCode), "invalid dialing code");

        PreConditions.checkNotNull(promoCode, "promoCode is null");
        PreConditions.checkNotNull(firstName, "firstName is null");
        PreConditions.checkNotNull(lastName, "lastName is null");


        PreConditions.checkNotNull(validateListener, "validateListener is null");

        validateUserDetailsUseCase.execute(config, new ValidateUserDetailsUseCase.Params(firstName, lastName, email, dialingCode, mobile, promoCode, validateListener));
    }

    /**
     * Registers new user
     * <p>
     * Call {@link #init(Context, String, String, String)} and
     * {@link #getOTPForRegistration(String, String, LiquidPayCallback)}
     * before calling this method
     *
     * @param emailAddress     user valid email address
     * @param otp              One Time password as received via {@link #getOTPForRegistration(String, String, LiquidPayCallback)}
     * @param firstName        user first name
     * @param lastName         user last name
     * @param password         user password for user account
     * @param registeredSource A valid registered source see {@link com.korvac.liquidpay.sdk.main.enums.RegisteredSource#NONE}
     * @param promoCode        optional (can be null)
     * @throws IllegalStateException    if SDK not initialized
     * @throws NullPointerException     if arguments are null
     * @throws IllegalArgumentException if dialingCode, mobile, email or registered source is invalid
     */
    public void submitRegistrationDetails(@NonNull String emailAddress, @NonNull String dialingCode, @NonNull String mobileNumber,
                                          @NonNull String otp, @NonNull String firstName,
                                          @NonNull String lastName, @NonNull String password,
                                          @NonNull String registeredSource, @Nullable String promoCode, @NonNull final LiquidPayCallback<ResponseLogin> submitRegistrationListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkArgument(Validator.isValidNumber(dialingCode), "invalid dialing code");
        PreConditions.checkArgument(Validator.isValidNumber(mobileNumber), "invalid mobileNumber");
        PreConditions.checkArgument(Validator.isValidEmail(emailAddress), "invalid email address");
        PreConditions.checkArgument(RegisteredSource.isValid(registeredSource), "invalid registered source");

        PreConditions.checkNotNull(otp, "otp is null");
        PreConditions.checkNotNull(firstName, "firstName is null");
        PreConditions.checkNotNull(lastName, "lastName is null");
        PreConditions.checkNotNull(password, "password is null");

        PreConditions.checkNotNull(submitRegistrationListener, "submitRegistrationListener is null");

        String promo = (promoCode == null) ? "" : promoCode;

        submitRegistrationDetailsUseCase.execute(config, new SubmitRegistrationDetailsUseCase.Params(emailAddress, dialingCode,
                mobileNumber, otp, firstName, lastName, password, registeredSource, promo, submitRegistrationListener));
    }

    /**
     * Sends One Time Password for registration on given mobile number
     * <p>
     * Call {@link #init(Context, String, String, String)} before calling this method
     *
     * @param dialingCode  User country dialing code
     * @param mobileNumber User mobile number
     * @param otpListener  result callback{@link LiquidPayCallback}
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     * @throws IllegalArgumentException             if dialing code or mobile number is invalid
     */
    public void getOTPForRegistration(@NonNull String dialingCode, @NonNull String mobileNumber, @NonNull final LiquidPayCallback<ResponseSendOTP> otpListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.getInstance.init() first");

        PreConditions.checkArgument(Validator.isValidNumber(dialingCode), "invalid dialing code");
        PreConditions.checkArgument(Validator.isValidNumber(mobileNumber), "invalid mobile number");
        PreConditions.checkNotNull(otpListener, "otpListener is null");


        sendOTPUseCase.execute(config, new SendOTPUseCase.Params(dialingCode, mobileNumber, otpListener));
    }

    /**
     * Asynchronously creates payment with specified amount and merchant information
     * <p>
     *
     * @param payee                 merchant id
     * @param serviceType           ServiceType see {@link ServiceType} for available serviceTypes
     * @param merchantRefNo         merchant reference number
     * @param amount                amount to pay
     * @param createPaymentListener result callback
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     * @throws IllegalArgumentException             for Illegal arguments e.g ServiceType, amount
     */
    public void createPayment(@NonNull String payee, @NonNull String serviceType, @NonNull String merchantRefNo, @NonNull String amount, @NonNull LiquidPayCallback<ResponsePayment> createPaymentListener) {
        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.getInstance.init() first");
        PreConditions.checkNotNull(payee, "payee code is null");
        PreConditions.checkArgument(ServiceType.isValid(serviceType), "invalid service type: use ServiceType. for valid types");
        PreConditions.checkNotNull(merchantRefNo, "merchantRefNo code is null");

        PreConditions.checkArgument(Validator.isValidAmount(amount), "invalid amount. Pass exactly 2 decimal value");

        PreConditions.checkNotNull(createPaymentListener, "createPaymentListener code is null");
        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        createPaymentUseCase.execute(config, getAccessToken(), new CreatePaymentUseCase.Params(payee, serviceType, merchantRefNo, amount, createPaymentListener));

    }

    /**
     * Cancel payment
     * <p>
     * call {@link #init(Context, String, String, String, String, String, String)} before calling this method
     *
     * @param paymentId             Payment id
     * @param cancelPaymentListener result callback {@link ResponseCancelPayment}
     * @throws IllegalStateException                when init is not called first
     * @throws NullPointerException                 when arguments are null
     * @throws LiquidPayInvalidAccessTokenException exception if session expired
     */
    public void cancelPayment(@NonNull String paymentId, @NonNull LiquidPayCallback<ResponseCancelPayment> cancelPaymentListener) {
        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkNotNull(paymentId, "Payment Id is null");
        PreConditions.checkNotNull(cancelPaymentListener, "cancelPaymentListener Id is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        cancelPaymentUseCase.execute(config, getAccessToken(), new CancelPaymentUseCase.Params(paymentId, cancelPaymentListener));

    }

    /**
     * Gets payment options for this payment e.g credit cards or store value cards
     * <p>
     *
     * @param paymentId paymentId see {@link #createPayment(String, String, String, String, LiquidPayCallback)}
     * @param listener  result callback
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     */
    public void getPaymentOptions(String paymentId, LiquidPayCallback<ResponseGetPaymentOptions> listener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.getInstance.init() first");

        PreConditions.checkNotNull(paymentId, "paymentId code is null");
        PreConditions.checkNotNull(listener, "listener  is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        getPaymentOptionsUseCase.execute(config, getAccessToken(), new GetPaymentOptionsUseCase.Params(paymentId, listener));


    }

    /**
     * Checks status of the current payment
     * <p>
     *
     * @param paymentId                paymentId see {@link #createPayment(String, String, String, String, LiquidPayCallback)}
     * @param getPaymentStatusListener result callback
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     */
    public void checkPaymentStatus(@NonNull String paymentId, @NonNull LiquidPayCallback<ResponsePayment> getPaymentStatusListener) {
        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.getInstance.init() first");
        PreConditions.checkNotNull(paymentId, "paymentId is null");
        PreConditions.checkNotNull(getPaymentStatusListener, "getPaymentStatusListener code is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        getPaymentStatusUseCase.execute(config, getAccessToken(), new GetPaymentStatusUseCase.Params(paymentId, getPaymentStatusListener));
    }

    /**
     * Asynchronously adds storedValue card to current user's wallet
     * <p>
     *
     * @param nationality                user nationality e.g SG
     * @param svcId                      Id of the stored value card to be added
     * @param userNID                    user unique id (NRIC)
     * @param addStoredValueCardListener result callback
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     * @throws IllegalArgumentException             for Illegal arguments e.g gender
     */
    public void addStoreValueCard(@NonNull String svcId, @NonNull String userNID, @NonNull String nationality, @NonNull LiquidPayCallback<ResponseAddStoredValueCard> addStoredValueCardListener) {


        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.getInstance.init() first");

        PreConditions.checkNotNull(nationality, "nationality  is null");
        PreConditions.checkNotNull(svcId, "userNID  is null");

        PreConditions.checkNotNull(userNID, "svcID  is null");
        PreConditions.checkNotNull(addStoredValueCardListener, "addStoredValueCardListener code is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        addStoredValueCardUseCase.execute(config, getAccessToken(), new AddStoredValueCardUseCase.Params(nationality, svcId, userNID, addStoredValueCardListener));
    }

    /**
     * Top-ups stored value card
     * <p>
     *
     * @param recipient                    SVC Id to be topped up
     * @param originator                   source Id (Credit/Debit cards or LiquidCash SVC)
     * @param topupAmount                  topup amount
     * @param topUpStoredValueCardListener result callback
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     * @throws IllegalArgumentException             if amount is not valid
     */
    public void topUpStoreValueCard(@NonNull String recipient, @NonNull String originator, @NonNull String topupAmount,
                                    @NonNull LiquidPayCallback<ResponseTopUpStoredValueCard> topUpStoredValueCardListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.getInstance.init() first");

        PreConditions.checkNotNull(recipient, "recipient code is null");
        PreConditions.checkNotNull(originator, "originator code is null");
        PreConditions.checkArgument(Validator.isValidAmount(topupAmount), "invalid amount: provide in 02.20 format");

        PreConditions.checkNotNull(topUpStoredValueCardListener, "topUpStoredValueCardListener code is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        topUpStoredValueCardUseCase.execute(config, getAccessToken(), new TopUpStoredValueCardUseCase.Params(recipient, originator, topupAmount, topUpStoredValueCardListener));
    }

    /**
     * Pays via Stored Value Card
     * <p>
     *
     * @param paymentId                       valid payment id see{@link #createPayment(String, String, String, String, LiquidPayCallback)}
     * @param source                          source id (Credit/Debit card or LiquidCash SVC)
     * @param payUsingStoredValueCardListener result callback
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     */
    public void payUsingStoreValueCard(@NonNull String paymentId, @NonNull String source, @NonNull LiquidPayCallback<ResponsePayment> payUsingStoredValueCardListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.getInstance.init() first");

        PreConditions.checkNotNull(paymentId, "paymentId code is null");
        PreConditions.checkNotNull(source, "source code is null");
        PreConditions.checkNotNull(payUsingStoredValueCardListener, "payUsingStoredValueCardListener code is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        payUsingStoredValueCardUseCase.execute(config, getAccessToken(), new PayUsingStoredValueCardUseCase.Params(paymentId, source, payUsingStoredValueCardListener));
    }


    /**
     * Gets current user's added stored value cards
     * <p>
     *
     * @param getStoredValueCardListener result callback
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     */
    public void listUserStoreValueCards(@NonNull final LiquidPayCallback<ResponseGetStoredValueCard> getStoredValueCardListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkNotNull(getStoredValueCardListener, "getStoredValueCardListener is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        getAddedStoredValueCardUseCase.execute(config, getAccessToken(), getStoredValueCardListener);

    }

    /**
     * Gets stored value card pre-requisite details
     * <p>
     *
     * @param id                                        valid store value card Id see
     * @param getStoredValueCardRequiredDetailsListener result callback
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     */
    public void getStoreValueCardRequiredDetails(@NonNull final String id, @NonNull final LiquidPayCallback<ResponseGetStoredValueCardRequiredDetails> getStoredValueCardRequiredDetailsListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkNotNull(id, "id is null");
        PreConditions.checkNotNull(getStoredValueCardRequiredDetailsListener, "getStoredValueCardRequiredDetailsListener is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }
        getStoredValueCardRequiredDetailsUseCase.execute(config, getAccessToken(), new GetStoredValueCardRequiredDetailsUseCase.Params(id, getStoredValueCardRequiredDetailsListener));

    }

    /**
     * Gets top-up options for Stored Value Card
     * <p>
     *
     * @param recipient            SVC id to be topped up
     * @param topUpOptionsListener
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     */
    public void getStoredValueCardTopUpOptions(@NonNull String recipient, @NonNull final LiquidPayCallback<ResponseGetStoredValueCardTopupOptions> topUpOptionsListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkNotNull(recipient, "recipient is null");
        PreConditions.checkNotNull(topUpOptionsListener, "topUpOptionsListener is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }
        getStoredValueCardTopUpOptionsUseCase.execute(config, getAccessToken(), new GetStoredValueCardTopUpOptionsUseCase.Params(recipient, topUpOptionsListener));
    }

    /**
     * @param offset
     * @param limit
     * @param availableStoredValueCardListener
     */
    public void getAvailableStoredValueCard(@Nonnegative int offset, @Nonnegative int limit, @NonNull LiquidPayCallback<ResponseAvailableStoredValueCard> availableStoredValueCardListener) {
        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkPositionIndex(offset, 100, "maximum offset is 100");
        PreConditions.checkPositionIndex(limit, 100, "limit min value 0 and max value 100");
        PreConditions.checkNotNull(availableStoredValueCardListener, "availableStoredValueCardListener is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        getAvailableStoredValueCardUseCase.execute(config, getAccessToken(), new GetAvailableStoredValueCardUseCase.Params(offset, limit, availableStoredValueCardListener));

    }

    /**
     * Collect voucher from Giftbox to wallet
     *
     * @param id       voucher id
     * @param callback Result callback {@link ResponseWalletSaveVoucher}
     */
    public void saveVoucherFromGiftboxToWallet(
            @NonNull String id,
            @NonNull LiquidPayCallback<ResponseWalletSaveVoucher> callback) {

        PreConditions.checkState(
                isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkNotNull(id, "id is null");
        PreConditions.checkNotNull(callback, "callback is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException(
                    "Access token expire, please re-loginWithPassword");
        }

        walletSaveVoucherFromGiftboxUseCase.execute(
                config,
                getAccessToken(),
                new WalletSaveVoucherFromGiftboxUseCase.Params(id, callback));
    }

    /**
     * Add brand vouchers to wallet
     *
     * @param id       voucher id
     * @param callback Result callback {@link ResponseWalletAddBrandVoucher}
     */
    public void addBrandVoucherToWallet(
            @NonNull String id,
            @NonNull LiquidPayCallback<ResponseWalletAddBrandVoucher> callback) {

        PreConditions.checkState(
                isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkNotNull(id, "id is null");
        PreConditions.checkNotNull(callback, "callback is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException(
                    "Access token expire, please re-loginWithPassword");
        }

        walletAddBrandVoucherUseCase.execute(
                config,
                getAccessToken(),
                new WalletAddBrandVoucherUseCase.Params(id, callback));
    }

    /**
     * Get Listing of vouchers in wallet
     *
     * @param callback Result callback {@link ResponseWalletVoucherList}
     */
    public void getVoucherListOfWallet(
            @NonNull LiquidPayCallback<ResponseWalletVoucherList> callback) {

        PreConditions.checkState(
                isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkNotNull(callback, "callback is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException(
                    "Access token expire, please re-loginWithPassword");
        }

        walletGetVoucherListUseCase.execute(
                config,
                getAccessToken(),
                new WalletGetVoucherListUseCase.Params(callback));
    }

    /**
     * Send voucher to friend
     *
     * @param id       voucher id
     * @param mobile   destination mobile
     * @param callback Result callback {@link ResponseVoucherSendToFriend}
     */
    public void sendVoucherToFriend(
            @NonNull String id,
            @NonNull String mobile,
            @NonNull LiquidPayCallback<ResponseVoucherSendToFriend> callback) {

        PreConditions.checkState(
                isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkNotNull(id, "id is null");
        PreConditions.checkNotNull(mobile, "mobile is null");
        PreConditions.checkNotNull(callback, "callback is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException(
                    "Access token expire, please re-loginWithPassword");
        }

        voucherSendToFriendUseCase.execute(
                config,
                getAccessToken(),
                new VoucherSendToFriendUseCase.Params(id, mobile, callback));
    }

    /**
     * Get Voucher GiftBox Listing
     *
     * @param callback Result callback {@link ResponseVoucherGiftBox}
     */
    public void getVoucherGiftBoxListing(@NonNull LiquidPayCallback<ResponseVoucherGiftBox> callback) {
        PreConditions.checkState(
                isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkNotNull(callback, "callback is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException(
                    "Access token expire, please re-loginWithPassword");
        }

        getVoucherGiftBoxListingUseCase.execute(config, getAccessToken(),
                new GetVoucherGiftBoxListingUseCase.Params(callback));
    }

    /**
     * Retrieve a list of eligible Voucher for Payment
     *
     * @param source       Credit or Debit Card source ID
     * @param payee        Payee
     * @param currencyCode Currency Code
     * @param amount       Payment amount
     * @param callback     Result callback {@link ResponsePaymentListEligibleVoucher}
     */
    public void retrievePaymentListEligibleVoucher(
            @NonNull String source,
            @NonNull String payee,
            @NonNull String currencyCode,
            @Nonnegative double amount,
            @NonNull LiquidPayCallback<ResponsePaymentListEligibleVoucher> callback) {

        PreConditions.checkState(
                isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkNotNull(source, "source is null");
        PreConditions.checkNotNull(payee, "payee is null");
        PreConditions.checkNotNull(currencyCode, "currencyCode is null");
        PreConditions.checkNotNull(callback, "callback is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException(
                    "Access token expire, please re-loginWithPassword");
        }

        retrievePaymentListEligibleVoucherUseCase.execute(
                config,
                getAccessToken(),
                new RetrievePaymentListEligibleVoucherUseCase.Params(
                        source,
                        payee,
                        currencyCode,
                        amount,
                        callback)
        );
    }

    /**
     * Retrieve list of eligible membership card for payment
     *
     * @param source       Credit or Debit Card source ID
     * @param payee        Payee
     * @param currencyCode Currency Code
     * @param amount       Payment amount
     * @param offset
     * @param limit
     * @param callback     Result callback {@link ResponsePaymentListEligibleMembershipCard}
     */
    public void retrievePaymentListEligibleMembershipCard(
            @NonNull String source,
            @NonNull String payee,
            @NonNull String currencyCode,
            @Nonnegative double amount,
            @Nonnegative int offset,
            @Nonnegative int limit,
            LiquidPayCallback<ResponsePaymentListEligibleMembershipCard> callback) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkNotNull(source, "source is null");
        PreConditions.checkNotNull(payee, "payee is null");
        PreConditions.checkNotNull(currencyCode, "currencyCode is null");

        PreConditions.checkPositionIndex(offset, 100, "maximum offset is 100");
        PreConditions.checkPositionIndex(limit, 100, "limit min value 1 and max value 100");
        PreConditions.checkNotNull(callback, "callback is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        retrievePaymentListEligibleMembershipCardUseCase.execute(
                config,
                getAccessToken(),
                new RetrievePaymentListEligibleMembershipCardUseCase.Params(
                        source,
                        payee,
                        currencyCode,
                        amount,
                        offset,
                        limit,
                        callback)
        );

    }

    /**
     * @param offset
     * @param limit
     * @param availableMembershipCardListener
     */
    public void getAvailableMembershipCard(@Nonnegative int offset, @Nonnegative int limit, LiquidPayCallback<ResponseAvailableMembershipCard> availableMembershipCardListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkPositionIndex(offset, 100, "maximum offset is 100");
        PreConditions.checkPositionIndex(limit, 100, "limit min value 0 and max value 100");
        PreConditions.checkNotNull(availableMembershipCardListener, "availableMembershipCardListener is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        getAvailableMembershipCardUseCase.execute(config, getAccessToken(), new GetAvailableMembershipCardUseCase.Params(offset, limit, availableMembershipCardListener));

    }

    /**
     * @param membershipCardId
     * @param retrieveMembershipCardRequiredInfoListener
     */
    public void getMembershipCardRequiredInfo(@NonNull String membershipCardId, @NonNull LiquidPayCallback<ResponseRetrieveMembershipCardRequiredInfo> retrieveMembershipCardRequiredInfoListener) {
        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkNotNull(membershipCardId, "source is null");
        PreConditions.checkNotNull(retrieveMembershipCardRequiredInfoListener, "retrieveMembershipCardRequiredInfoListener is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        retrieveMembershipCardRequiredInfoUseCase.execute(config, getAccessToken(), new RetrieveMembershipCardRequiredInfoUseCase.Params(membershipCardId, retrieveMembershipCardRequiredInfoListener));
    }

    /**
     * @param membershipCardId
     * @param gender
     * @param dateOfBirth
     * @param addMembershipCardlistener
     */
    public void addMembershipCard(@NonNull String membershipCardId, @NonNull String gender, @NonNull String dateOfBirth, @NonNull LiquidPayCallback<ResponseAddMembershipCard> addMembershipCardlistener) {
        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkNotNull(membershipCardId, "source is null");
        PreConditions.checkNotNull(gender, "gender is null");
        PreConditions.checkNotNull(dateOfBirth, "dateOfBirth is null");
        PreConditions.checkNotNull(addMembershipCardlistener, "addMembershipCardlistener is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        addMembershipCardUseCase.execute(config, getAccessToken(), new AddMembershipCardUseCase.Params(membershipCardId, gender, dateOfBirth, addMembershipCardlistener));

    }

    public void getAddedMembershipCard(@Nonnegative int offset, @Nonnegative int limit, @NonNull LiquidPayCallback<ResponseGetAddedMembershipCard> getAddedMembershipCardListener) {
        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkPositionIndex(offset, 100, "maximum offset is 100");
        PreConditions.checkPositionIndex(limit, 100, "limit min value 0 and max value 100");
        PreConditions.checkNotNull(getAddedMembershipCardListener, "getAddedMembershipCardListener is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        getAddedMembershipCardUseCase.execute(config, getAccessToken(), new GetAddedMembershipCardUseCase.Params(offset, limit, getAddedMembershipCardListener));

    }

    /**
     * @param virtualAccountId
     * @param gender
     * @param dateOfBirth
     * @param addVirtualAccountListener
     */
    public void addVirtualAccount(@NonNull String virtualAccountId, @NonNull String gender, @NonNull String dateOfBirth, @NonNull LiquidPayCallback<ResponseAddVirtualAccount> addVirtualAccountListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkNotNull(virtualAccountId, "virtualAccountId is null");
        PreConditions.checkNotNull(gender, "gender is null");
        PreConditions.checkNotNull(dateOfBirth, "dateOfBirth is null");
        PreConditions.checkNotNull(addVirtualAccountListener, "addVirtualAccountListener is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        addVirtualAccountUseCase.execute(config, getAccessToken(), new AddVirtualAccountUseCase.Params(virtualAccountId, gender, dateOfBirth, addVirtualAccountListener));

    }

    /**
     * @param getAddedVirtualAccountListener
     */
    public void getAddedVirtualAccount(LiquidPayCallback<ResponseGetAddedVirtualAccount> getAddedVirtualAccountListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkNotNull(getAddedVirtualAccountListener, "getAddedVirtualAccountListener is null");
        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }
        getAddedVirtualAccountUseCase.execute(config, getAccessToken(), getAddedVirtualAccountListener);

    }

    public void deleteVirtualAccount(@NonNull String virtualAccountId, @NonNull LiquidPayCallback<ResponseDeleteVirtualAccount> deleteVirtualAccountListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkNotNull(virtualAccountId, "virtualAccountId is null");
        PreConditions.checkNotNull(deleteVirtualAccountListener, "deleteVirtualAccountListener is null");
        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        deleteVirtualAccountUseCase.execute(config, getAccessToken(), new DeleteVirtualAccountUseCase.Params(virtualAccountId, deleteVirtualAccountListener));

    }

    /**
     * @param virtualAccountId
     * @param paymentCardId
     * @param linkCardToVirtualAccountListener
     */
    public void linkCreditOrDebitCardToVirtualAccount(@NonNull String virtualAccountId, @NonNull String paymentCardId, @NonNull LiquidPayCallback<ResponseLinkCardToVirtualAccount> linkCardToVirtualAccountListener) {
        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkNotNull(virtualAccountId, "virtualAccountId is null");
        PreConditions.checkNotNull(paymentCardId, "paymentCardId is null");
        PreConditions.checkNotNull(linkCardToVirtualAccountListener, "linkCardToVirtualAccountListener is null");
        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        linkCardToVirtualAccountUseCase.execute(config, getAccessToken(), new LinkCardToVirtualAccountUseCase.Params(virtualAccountId, paymentCardId, linkCardToVirtualAccountListener));

    }

    /**
     * @param virtualAccountId
     * @param paymentCardId
     * @param unLinkCardFromVirtualAccountListener
     */
    public void unLinkCreditOrDebitCardFromVirtualAccount(@NonNull String virtualAccountId, @NonNull String paymentCardId, @NonNull LiquidPayCallback<ResponseUnLinkCardFromVirtualAccount> unLinkCardFromVirtualAccountListener) {
        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkNotNull(virtualAccountId, "virtualAccountId is null");
        PreConditions.checkNotNull(paymentCardId, "paymentCardId is null");
        PreConditions.checkNotNull(unLinkCardFromVirtualAccountListener, "unLinkCardFromVirtualAccountListener is null");
        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        unLinkCardTFromVirtualAccountUseCase.execute(config, getAccessToken(), new UnLinkCardTFromVirtualAccountUseCase.Params(virtualAccountId, paymentCardId, unLinkCardFromVirtualAccountListener));

    }

    public void payUsingVirtualAccount(@NonNull String paymentId, @NonNull String paymentCardId, @NonNull LiquidPayCallback<ResponsePayment> paymentListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkNotNull(paymentId, "paymentId is null");
        PreConditions.checkNotNull(paymentCardId, "paymentCardId is null");
        PreConditions.checkNotNull(paymentListener, "paymentListener is null");
        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        payUsingVirtualAccountUseCase.execute(config, getAccessToken(), new PayUsingVirtualAccountUseCase.Params(paymentId, paymentCardId, paymentListener));

    }

    /**
     * Get a list of payment cards to link virtual accounts
     *
     * @param virtualAccountId Id Source of Virtual Account
     * @param callback         Result callback {@link ResponseVirtualAccountPaymentCards}
     */
    public void getPaymentCardsLinkToVirtualAccount(
            @NonNull String virtualAccountId,
            @NonNull LiquidPayCallback<ResponseVirtualAccountPaymentCards> callback) {

        PreConditions.checkState(
                isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkNotNull(virtualAccountId, "virtualAccountId is null");
        PreConditions.checkNotNull(callback, "callback is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException(
                    "Access token expire, please re-loginWithPassword");
        }

        virtualAccountGetLinkPaymentCardsUseCase.execute(
                config,
                getAccessToken(),
                new VirtualAccountGetLinkPaymentCardsUseCase.Params(virtualAccountId, callback));
    }

    /**
     * Retrieve available virtual account
     * <p>
     * call {@link #init(Context, String, String, String, String, String, String)}
     * before calling this methodbasefrag
     */
    public void getAvailableVirtualAccount(@Nonnegative int offset, @Nonnegative int limit, @NonNull LiquidPayCallback<ResponseAvailableVirtualAccount> availableVirtualAccountListener) {
        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkPositionIndex(offset, 100, "maximum offset is 100");
        PreConditions.checkPositionIndex(limit, 100, "limit min value 0 and max value 100");
        PreConditions.checkNotNull(availableVirtualAccountListener, "availableVirtualAccountListener is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        getAvailableVirtualAccountUseCase.execute(config, getAccessToken(), new GetAvailableVirtualAccountUseCase.Param(offset, limit, availableVirtualAccountListener));

    }

    /**
     * Retrieve Virtual Account Required Information to add into user wallet
     * <p>
     * call {@link #init(Context, String, String, String, String, String, String)}
     * before calling this method
     *
     * @param virtualAccountId                                  Virtual account id to add
     * @param retrieveVirtualAccountAddToWalletRequiredListener callback result {@link ResponseRetrieveVirtualAccountRequiredInfo}
     * @throws IllegalStateException if SDK not initialized
     * @throws NullPointerException  if arguments are null
     */
    public void getVirtualAccountAddToWalletRequiredInfo(@NonNull String virtualAccountId, @NonNull final LiquidPayCallback<ResponseRetrieveVirtualAccountRequiredInfo> retrieveVirtualAccountAddToWalletRequiredListener) {
        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkNotNull(virtualAccountId, "virtualAccountId is null");
        PreConditions.checkNotNull(retrieveVirtualAccountAddToWalletRequiredListener, "retrieveVirtualAccountAddToWalletRequiredListener is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        retrieveVirtualAccountRequiredInfoUseCase.execute(config, getAccessToken(),
                new RetrieveVirtualAccountRequiredInfoUseCase.Param(
                        virtualAccountId, retrieveVirtualAccountAddToWalletRequiredListener));

    }

    /**
     * Gets card details
     * <p>
     *
     * @param cardNumber             valid card number (without any space or dash (-) )
     * @param cardBinDetailsListener
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     * @throws IllegalArgumentException             if card number is invalid
     */
    private void getCardBinDetails(@NonNull String cardNumber, @NonNull LiquidPayCallback<ResponseCardBinDetails> cardBinDetailsListener) {


        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkArgument(Validator.isValidNumber(cardNumber), "invalid cardNumber");
        PreConditions.checkNotNull(cardBinDetailsListener, "cardBinDetailsListener is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }
        getCardBinDetailsUseCase.execute(config, getAccessToken(), new GetCardBinDetailsUseCase.Params(cardNumber, cardBinDetailsListener));
    }

    /**
     * Adds Credit/Debit card
     * <p>
     *
     * @param cardNumber     valid card number (without any spaces or dash(-)
     * @param cvc            valid cvc
     * @param cardHolderName card holder name
     * @param expiryDate     card expiry date
     * @param callback       result callback
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     * @throws IllegalArgumentException             if card number is not valid
     */
    public void addPaymentCard(@NonNull final String cardNumber, @NonNull final String cvc, @NonNull final String cardHolderName, @NonNull final Date expiryDate, final LiquidPayCallback<BaseCard> callback) {
        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkArgument(Validator.isValidNumber(cardNumber), "invalid cardNumber");
        PreConditions.checkNotNull(cvc, "cvc is null");

        PreConditions.checkNotNull(cardHolderName, "cardHolderName is null");
        PreConditions.checkNotNull(expiryDate, "expiryDate is null");
        PreConditions.checkNotNull(callback, "callback is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        //check if card is supported
        getCardBinDetails(cardNumber, new LiquidPayCallback<ResponseCardBinDetails>() {
            @Override
            public void onSuccess(ResponseCardBinDetails response) {
                addCardUseCase.execute(config, getAccessToken(), new AddCardUseCase.Params(cardNumber, cardHolderName, cvc, expiryDate, callback));
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {

                callback.onError(throwable, errorMessage);
            }
        });

    }

    /**
     * Deletes Credit/Debit card
     * <p>
     *
     * @param cardId   card id as returned in {@link ResponseListCards}
     * @param callback result callback
     * @throws IllegalStateException                if SDK not initialized
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws NullPointerException                 if arguments are null
     */
    public void deletePaymentCard(@NonNull final String cardId, final LiquidPayCallback<ResponseDeleteCard> callback) {
        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkNotNull(cardId, "cardId is null");
        PreConditions.checkNotNull(callback, "callback is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        deleteCardUseCase.execute(config, getAccessToken(), new DeleteCardUseCase.Params(cardId, callback));

    }

    /**
     * Gets current user's added credit/debit cards
     * <p>
     *
     * @param callback result callback{@link ResponseListCards}
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     */
    public void listPaymentCards(@NonNull final LiquidPayCallback<ResponseListCards> callback) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        PreConditions.checkNotNull(callback, "callback is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        listCardsUseCase.execute(config, getAccessToken(), callback);

    }


    /**
     * Validates email address
     * <p>
     *
     * @param email            User email Address
     * @param validateListener Result callback {@link LiquidPayCallback<ResponseValidateUserDetails>}
     * @throws IllegalStateException    if SDK not initialized
     * @throws NullPointerException     if arguments are null
     * @throws IllegalArgumentException if email is not valid
     */
    public void validateUserEmail(@NonNull String email, @NonNull final LiquidPayCallback<ResponseValidateUserDetails> validateListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.getInstance().init() first");

        PreConditions.checkArgument(Validator.isValidEmail(email), "invalid email address");

        validateUserDetailsUseCase.execute(config, new ValidateUserDetailsUseCase.Params("", "", email, "", "", "", validateListener));
    }

    /**
     * Validates mobile number
     * <p>
     *
     * @param mobile           User mobile number
     * @param validateListener Result callback {@link LiquidPayCallback<ResponseValidateUserDetails>}
     * @throws IllegalStateException    if SDK not initialized
     * @throws NullPointerException     if arguments are null
     * @throws IllegalArgumentException if mobile number is invalid
     */
    public void validateUserMobile(@NonNull String mobile, @NonNull final LiquidPayCallback<ResponseValidateUserDetails> validateListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.getInstance().init() first");

        PreConditions.checkArgument(Validator.isValidNumber(mobile), "invalid mobile number");

        validateUserDetailsUseCase.execute(config, new ValidateUserDetailsUseCase.Params("", "", "", "", mobile, "", validateListener));
    }

    /**
     * Validates promotion code
     * <p>
     *
     * @param promoCode        Merchant Promotion Code
     * @param validateListener Result callback {@link LiquidPayCallback<ResponseValidateUserDetails>}
     * @throws IllegalStateException if SDK not initialized
     * @throws NullPointerException  if arguments are null
     */
    public void validatePromoCode(@NonNull String promoCode, @NonNull final LiquidPayCallback<ResponseValidateUserDetails> validateListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.getInstance().init() first");

        PreConditions.checkNotNull(promoCode, "promoCode is null");

        validateUserDetailsUseCase.execute(config, new ValidateUserDetailsUseCase.Params("", "", "", "", "", promoCode, validateListener));
    }

    /**
     * Validates first name
     * <p>
     *
     * @param firstName        User first name
     * @param validateListener Result callback {@link LiquidPayCallback<ResponseValidateUserDetails>}
     * @throws IllegalStateException if SDK not initialized
     * @throws NullPointerException  if arguments are null
     */
    public void validateUserFirstName(@NonNull String firstName, @NonNull final LiquidPayCallback<ResponseValidateUserDetails> validateListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.getInstance().init() first");

        PreConditions.checkNotNull(firstName, "firstName is null");

        validateUserDetailsUseCase.execute(config, new ValidateUserDetailsUseCase.Params(firstName, "", "", "", "", "", validateListener));

    }

    /**
     * Validates last name
     * <p>
     *
     * @param lastName         User last name
     * @param validateListener Result callback {@link LiquidPayCallback<ResponseValidateUserDetails>}
     * @throws IllegalStateException if SDK not initialized
     * @throws NullPointerException  if arguments are null
     */
    public void validateUserLastName(@NonNull String lastName, @NonNull final LiquidPayCallback<ResponseValidateUserDetails> validateListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.getInstance().init() first");

        PreConditions.checkNotNull(lastName, "lastName is null");

        validateUserDetailsUseCase.execute(config, new ValidateUserDetailsUseCase.Params("", lastName, "", "", "", "", validateListener));
    }


    /**
     * Validates country dialing code
     * <p>
     *
     * @param dialingCode      User mobile Dialing Code
     * @param validateListener Result callback {@link LiquidPayCallback<ResponseValidateUserDetails>}
     * @throws IllegalStateException    if SDK not initialized
     * @throws NullPointerException     if arguments are null
     * @throws IllegalArgumentException for invalid dialing code
     */
    public void validateUserDialingCode(@NonNull String dialingCode, @NonNull final LiquidPayCallback<ResponseValidateUserDetails> validateListener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.getInstance().init() first");

        PreConditions.checkArgument(Validator.isValidNumber(dialingCode), "invalid dialingCode");

        validateUserDetailsUseCase.execute(config, new ValidateUserDetailsUseCase.Params("", "", "", dialingCode, "", "", validateListener));
    }

    /**
     * Checks Credit or Debit Card status and returns card details
     * <p>
     *
     * @param sourceId Credit or Debit Card source ID
     * @param listener Result callback {@link LiquidPayCallback<ResponseCheckCardStatus>}
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     */
    public void checkPaymentCardStatus(String sourceId, LiquidPayCallback<ResponseCheckCardStatus> listener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");

        PreConditions.checkNotNull(sourceId, "sourceId is null");
        PreConditions.checkNotNull(listener, "listener is null");

        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }
        checkCardStatusUseCase.execute(config, getAccessToken(), new CheckCardStatusUseCase.Params(sourceId, listener));
    }

    /**
     * Make payment using Credit or Debit Card
     * <p>
     *
     * @param paymentId Payment ID
     * @param source    Credit or Debit Card source ID
     * @param listener  Result callback {@link LiquidPayCallback<ResponsePayment>}
     * @throws LiquidPayInvalidAccessTokenException exception if session is expire
     * @throws IllegalStateException                if SDK not initialized
     * @throws NullPointerException                 if arguments are null
     */
    public void payUsingPaymentCard(String paymentId, String source, LiquidPayCallback<ResponsePayment> listener) {

        PreConditions.checkState(isInitialized, "SDK not initialized:please call LiquidPay.init() first");
        if (!isAccessTokenValid()) {
            throw new LiquidPayInvalidAccessTokenException("Access token expire, please re-loginWithPassword");
        }

        payUsingCardUseCase.execute(config, getAccessToken(), new PayUsingCreditOrDebitCardUseCase.Params(paymentId, source, listener));


    }


}
