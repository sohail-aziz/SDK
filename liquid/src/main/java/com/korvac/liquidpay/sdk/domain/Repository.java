package com.korvac.liquidpay.sdk.domain;


import android.support.annotation.NonNull;

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
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.annotation.Nonnegative;

/**
 * Repository Interface for all the API calls
 * <p>
 * Created by sohail on 5/5/2017.
 */

public interface Repository {
    /**
     * Get a list of payment cards to link virtual accounts
     *
     * @param config           LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken      Access Token (Authorization: Bearer)
     * @param virtualAccountId Id Source of Virtual Account
     * @param callback         Result callback {@link ResponseVirtualAccountPaymentCards}
     */
    void getPaymentCardsLinkToVirtualAccount(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull String virtualAccountId,
            @NonNull LiquidPayCallback<ResponseVirtualAccountPaymentCards> callback
    );

    /**
     * Collect voucher from Giftbox to wallet
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken Access Token (Authorization: Bearer)
     * @param id          voucher_ids
     * @param callback    Result callback {@link ResponseWalletSaveVoucher}
     */
    void saveVoucherFromGiftboxToWallet(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull String id,
            @NonNull LiquidPayCallback<ResponseWalletSaveVoucher> callback
    );

    /**
     * Get Listing of vouchers in wallet
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken Access Token (Authorization: Bearer)
     * @param callback    Result callback {@link ResponseWalletVoucherList}
     */
    void getVoucherListOfWallet(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull LiquidPayCallback<ResponseWalletVoucherList> callback
    );

    /**
     * Get Voucher GiftBox Listing
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken Access Token (Authorization: Bearer)
     * @param callback    Result callback {@link ResponseVoucherGiftBox}
     */
    void getVoucherGiftBoxListing(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull LiquidPayCallback<ResponseVoucherGiftBox> callback
    );

    /**
     * Add brand vouchers to wallet
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken Access Token (Authorization: Bearer)
     * @param id          voucher_ids
     * @param mobile      destination_mobile
     * @param callback    Result callback {@link ResponseVoucherSendToFriend}
     */
    void sendVoucherToFriend(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull String id,
            @NonNull String mobile,
            @NonNull LiquidPayCallback<ResponseVoucherSendToFriend> callback
    );

    /**
     * Add brand vouchers to wallet
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken Access Token (Authorization: Bearer)
     * @param id          voucher_ids
     * @param callback    Result callback {@link ResponseWalletAddBrandVoucher}
     */
    void addBrandVoucherToWallet(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull String id,
            @NonNull LiquidPayCallback<ResponseWalletAddBrandVoucher> callback
    );

    /**
     * Retrieve a list of eligible Voucher for Payment
     *
     * @param config       LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken  Access Token (Authorization: Bearer)
     * @param source       Credit or Debit Card source ID
     * @param payee        Payee
     * @param currencyCode Currency Code
     * @param amount       Payment amount
     * @param callback     Result callback {@link ResponsePaymentListEligibleVoucher}
     */
    void retrievePaymentListEligibleVoucher(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull String source,
            @NonNull String payee,
            @NonNull String currencyCode,
            @Nonnegative double amount,
            @NonNull LiquidPayCallback<ResponsePaymentListEligibleVoucher> callback
    );

    /**
     * Retrieve list of eligible membership card for payment
     *
     * @param config       LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken  Access Token (Authorization: Bearer)
     * @param source       Credit or Debit Card source ID
     * @param payee        Payee
     * @param currencyCode Currency Code
     * @param amount       Payment amount
     * @param offset
     * @param limit
     * @param callback     Result callback {@link ResponsePaymentListEligibleMembershipCard}
     */
    void retrievePaymentListEligibleMembershipCard(
            @NonNull LiquidPayConfig config,
            @NonNull String accessToken,
            @NonNull String source,
            @NonNull String payee,
            @NonNull String currencyCode,
            @Nonnegative double amount,
            @Nonnegative int offset,
            @Nonnegative int limit,
            @NonNull LiquidPayCallback<ResponsePaymentListEligibleMembershipCard> callback
    );

    void getAvailableStoredValueCard(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                     @Nonnegative int offset, @Nonnegative int limit,
                                     @NonNull LiquidPayCallback<ResponseAvailableStoredValueCard> callback);

    /**
     *
     * @param config
     * @param accessToken
     * @param offset
     * @param limit
     * @param callback
     */
    void getAddedMembershipCard(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                @Nonnegative int offset, @Nonnegative int limit,
                                @NonNull LiquidPayCallback<ResponseGetAddedMembershipCard> callback);

    /**
     * @param config
     * @param accessToken
     * @param membershipCardId
     * @param gender
     * @param dateOfBirth
     * @param callback
     */
    void addMembershipCard(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                           @NonNull String membershipCardId, @NonNull String gender,
                           @NonNull String dateOfBirth, @NonNull LiquidPayCallback<ResponseAddMembershipCard> callback);

    /**
     * @param config
     * @param accessToken
     * @param membershipCardId
     */
    void retrieveMembershipCardRequiredInfo(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                            @NonNull String membershipCardId,
                                            @NonNull LiquidPayCallback<ResponseRetrieveMembershipCardRequiredInfo> callback);

    /**
     * @param config
     * @param accessToken
     * @param offset
     * @param limit
     * @param callback
     */
    void getAvailableMembershipCard(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                    @Nonnegative int offset, @Nonnegative int limit,
                                    @NonNull LiquidPayCallback<ResponseAvailableMembershipCard> callback);

    /**
     * @param config
     * @param accessToken
     * @param paymentId
     * @param paymentCardId
     * @param callback
     */
    void payUsingVirtualAccount(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                @NonNull String paymentId, @NonNull String paymentCardId,
                                @NonNull LiquidPayCallback<ResponsePayment> callback);

    /**
     * @param config
     * @param accessToken
     * @param virtualAccountId
     * @param paymentCardId
     * @param callback
     */
    void unLinkCardFromVirtualAccount(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                      @NonNull String virtualAccountId, @NonNull String paymentCardId,
                                      @NonNull LiquidPayCallback<ResponseUnLinkCardFromVirtualAccount> callback);

    /**
     * @param config
     * @param accessToken
     * @param virtualAccountId
     * @param paymentCardId
     * @param callback
     */
    void linkCardToVirtualAccount(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                  @NonNull String virtualAccountId, @NonNull String paymentCardId,
                                  @NonNull LiquidPayCallback<ResponseLinkCardToVirtualAccount> callback);

    /**
     * @param config
     * @param accessToken
     * @param virtualAccountId
     * @param callback
     */
    void deleteVirtualAccount(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                              @NonNull String virtualAccountId, @NonNull LiquidPayCallback<ResponseDeleteVirtualAccount> callback);

    /**
     * @param config
     * @param accessToken
     * @param callback
     */
    void getAddedVirtualAccount(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                @NonNull LiquidPayCallback<ResponseGetAddedVirtualAccount> callback);

    /**
     * @param config
     * @param accessToken
     * @param virtualAccountId
     * @param gender
     * @param dateOfBirth
     * @param callback
     */
    void addVirtualAccount(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                           @NonNull String virtualAccountId, @NonNull String gender, @NonNull String dateOfBirth,
                           @NonNull LiquidPayCallback<ResponseAddVirtualAccount> callback);

    /**
     * @param config
     * @param accessToken
     * @param offset
     * @param limit
     * @param callback
     */
    void getAvailableVirtualAccount(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                    @Nonnegative int offset, @Nonnegative int limit,
                                    @NonNull LiquidPayCallback<ResponseAvailableVirtualAccount> callback);

    /**
     * @param config
     * @param accessToken
     * @param virtualAccountId
     * @param callback
     */
    void retrieveVirtualAccountRequiredInfo(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                            @NonNull String virtualAccountId, @NonNull LiquidPayCallback<ResponseRetrieveVirtualAccountRequiredInfo> callback);

    /**
     * Cancel Payment
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken User access token
     * @param paymentId   Payment id
     * @param callback    Result callback {@link ResponseCancelPayment}
     * @throws UnsupportedEncodingException any Character Encoding is not supported
     * @throws NoSuchAlgorithmException     any particular cryptographic algorithm is requested but is not available in the environment
     */
    void cancelPayment(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String paymentId,
                       @NonNull LiquidPayCallback<ResponseCancelPayment> callback);

    /**
     * Change Password
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken User access token
     * @param oldPassword User old password
     * @param newPassword User new password
     * @param callback    Result callback {@link ResponseChangePassword}
     * @throws UnsupportedEncodingException any Character Encoding is not supported
     * @throws NoSuchAlgorithmException     any particular cryptographic algorithm is requested but is not available in the environment
     */
    void changePassword(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String oldPassword,
                        @NonNull String newPassword, @NonNull LiquidPayCallback<ResponseChangePassword> callback);


    /**
     * Forgot password
     *
     * @param config   LiquidPay Config {@link LiquidPayConfig}
     * @param email    User email address
     * @param callback Result callback{@link ResponseForgotPassword}
     */
    void forgotPassword(@NonNull LiquidPayConfig config, @NonNull String email,
                        @NonNull LiquidPayCallback<ResponseForgotPassword> callback);

    /**
     * Lists user cards (credit/debit)
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken User access token
     * @param callback    Result callback{@link ResponseListCards}
     */
    void listCards(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull LiquidPayCallback<ResponseListCards> callback);

    /**
     * Deletes credit/debit card by id
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken User access token
     * @param cardId      credit/debit card to be deleted
     * @param callback    Result callback{@link ResponseDeleteCard}
     */
    void deleteCard(@NonNull LiquidPayConfig config, @NonNull String accessToken, String cardId, @NonNull LiquidPayCallback<ResponseDeleteCard> callback);

    /**
     * Create Payment
     *
     * @param config        LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken   User access token
     * @param payee         Payee
     * @param serviceType   Payment service type
     * @param merchantRefNo Merchant reference number
     * @param amount        Payment amount
     * @param callback      Result callback{@link ResponsePayment}
     * @throws UnsupportedEncodingException any Character Encoding is not supported
     * @throws NoSuchAlgorithmException     any particular cryptographic algorithm is requested but is not available in the environment
     */
    void createPayment(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String payee, @NonNull String serviceType,
                       @NonNull String merchantRefNo, @NonNull String amount, @NonNull LiquidPayCallback<ResponsePayment> callback);

    /**
     * Get payment status
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken User access token
     * @param paymentId   payment id
     * @param callback    Result callback{@link ResponsePayment}
     * @throws UnsupportedEncodingException any Character Encoding is not supported
     * @throws NoSuchAlgorithmException     any particular cryptographic algorithm is requested but is not available in the environment
     */
    void getPaymentStatus(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                          @NonNull String paymentId, @NonNull LiquidPayCallback<ResponsePayment> callback);

    /**
     * Get Payment Options
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken User access token
     * @param paymentId   Payment ID
     * @param callback    Result callback{@link ResponseGetPaymentOptions}
     */
    void getPaymentOptions(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                           @NonNull String paymentId, @NonNull LiquidPayCallback<ResponseGetPaymentOptions> callback);


    /**
     * Making Payment using Stored Value Card for better discounts
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken User access token
     * @param paymentId   Payment ID
     * @param source      Stored Value Card Id
     * @param callback    Result callback{@link ResponsePayment}
     * @throws UnsupportedEncodingException any Character Encoding is not supported
     * @throws NoSuchAlgorithmException     any particular cryptographic algorithm is requested but is not available in the environment
     */
    void payUsingStoredValueCard(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String paymentId,
                                 @NonNull String source, @NonNull LiquidPayCallback<ResponsePayment> callback);

    /**
     * Making Payment using Stored Value Card for better discounts
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken User access token
     * @param paymentId   Payment ID
     * @param source      Credit or Debit Card source ID
     * @param callback    Result callback{@link ResponsePayment}
     * @throws UnsupportedEncodingException any Character Encoding is not supported
     * @throws NoSuchAlgorithmException     any particular cryptographic algorithm is requested but is not available in the environment
     */
    void payUsingCreditOrDebitCard(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String paymentId,
                                   @NonNull String source, @NonNull LiquidPayCallback<ResponsePayment> callback);

    /**
     * Add Stored Value Card
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken User access token
     * @param nationality User nationality
     * @param id          Stored Value Card ID
     * @param identifier  User identifier
     * @param callback    Result callback{@link ResponsePayment}
     * @throws UnsupportedEncodingException any Character Encoding is not supported
     * @throws NoSuchAlgorithmException     any particular cryptographic algorithm is requested but is not available in the environment
     */
    void addStoredValueCard(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String nationality,
                            @NonNull String id, @NonNull String identifier, @NonNull LiquidPayCallback<ResponseAddStoredValueCard> callback);

    /**
     * TopUp Stored Value Card
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken User access token
     * @param originator  Top-up originator ID
     * @param recipient   Top-up recipient ID
     * @param topupAmount Top-up Amount
     * @param callback    Result callback{@link ResponsePayment}
     * @throws UnsupportedEncodingException any Character Encoding is not supported
     * @throws NoSuchAlgorithmException     any particular cryptographic algorithm is requested but is not available in the environment
     */
    void topUpStoredValueCard(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                              @NonNull String recipient, @NonNull String originator,
                              @NonNull String topupAmount, @NonNull LiquidPayCallback<ResponseTopUpStoredValueCard> callback);

    /**
     * Get User added list of Stored Value Cards
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken User access token
     * @param callback    Result callback{@link ResponseGetStoredValueCard}
     * @throws UnsupportedEncodingException any Character Encoding is not supported
     * @throws NoSuchAlgorithmException     any particular cryptographic algorithm is requested but is not available in the environment
     */
    void getAddedStoredValueCard(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull LiquidPayCallback<ResponseGetStoredValueCard> callback);

    /**
     * Get Stored Value Card Details
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken User access token
     * @param id          Stored Value Card ID
     * @param callback    Result callback{@link ResponseGetStoredValueCardRequiredDetails}
     * @throws UnsupportedEncodingException any Character Encoding is not supported
     * @throws NoSuchAlgorithmException     any particular cryptographic algorithm is requested but is not available in the environment
     */
    void getStoredValueCardRequiredDetails(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                           @NonNull String id, @NonNull LiquidPayCallback<ResponseGetStoredValueCardRequiredDetails> callback);

    /**
     * Add Stored Value Card
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken User access token
     * @param recipient   Stored Value Card ID
     * @param callback    Result callback{@link ResponseGetStoredValueCardTopupOptions}
     * @throws UnsupportedEncodingException any Character Encoding is not supported
     * @throws NoSuchAlgorithmException     any particular cryptographic algorithm is requested but is not available in the environment
     */
    void getStoredValueCardTopUpOptions(@NonNull LiquidPayConfig config, @NonNull String accessToken,
                                        @NonNull String recipient, @NonNull LiquidPayCallback<ResponseGetStoredValueCardTopupOptions> callback);

    /**
     * Get Credit or Debit card bin details
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken User access token
     * @param cardNumber  Credit or Debit card number
     * @param callback    Result callback{@link ResponseCardBinDetails}
     */
    void getCardBinDetails(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String cardNumber,
                           @NonNull LiquidPayCallback<ResponseCardBinDetails> callback);


    void addCard(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String cardNumber,
                 @NonNull String cardHolderName, @NonNull Date expiry, @NonNull String cvv, @NonNull LiquidPayCallback<BaseCard> callback);

    void addCardLoop(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String cardNumber,
                     @NonNull String cardHolderName, @NonNull Date expiry, @NonNull String cvv, @NonNull LiquidPayCallback<BaseCard> callback);

    /**
     * Check card status
     *
     * @param config      LiquidPay Config {@link LiquidPayConfig}
     * @param accessToken User access token
     * @param sourceId    Credit or Debit Card source ID
     * @param callback    Result callback{@link ResponseCheckCardStatus}
     */
    void checkCardStatus(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String sourceId,
                         @NonNull LiquidPayCallback<ResponseCheckCardStatus> callback);

    /**
     * Sends verification email
     *
     * @param config
     * @param accessToken
     * @param email
     * @param forceUpdate
     * @param callback
     */
    public void sendVerifyEmail(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String email,
                                boolean forceUpdate, final @NonNull LiquidPayCallback<ResponseSendVerifyEmail> callback);
//    void resendEmailVerification(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull String email, boolean forceUpdate, @NonNull Callback<ResponseSendVerifyEmail> callback);

    /**
     * @param config
     * @param accessToken
     * @param callback
     */
    public void checkEmailVerification(@NonNull LiquidPayConfig config, @NonNull String accessToken, @NonNull LiquidPayCallback<ResponseCheckEmailVerification> callback);


    /**
     * @param config
     * @param emailAddress
     * @param dialingCode
     * @param mobileNumber
     * @param otp
     * @param firstName
     * @param lastName
     * @param password
     * @param registeredSource
     * @param promoCode
     * @param callback
     */
    void submitRegistrationDetails(@NonNull LiquidPayConfig config, @NonNull String emailAddress,
                                   @NonNull String dialingCode, @NonNull String mobileNumber,
                                   @NonNull String otp, @NonNull String firstName,
                                   @NonNull String lastName, @NonNull String password,
                                   @NonNull String registeredSource, @NonNull String promoCode, @NonNull LiquidPayCallback<ResponseLogin> callback);

    /**
     * Asynchronously Sends OTP on the provided mobile number
     *
     * @param config       LiquidPay Config {@link LiquidPayConfig}
     * @param dialingCode  Country dialing code
     * @param mobileNumber mobile phone number
     * @param callback
     */
    void sendOTP(@NonNull LiquidPayConfig config, @NonNull String dialingCode, @NonNull String mobileNumber, @NonNull LiquidPayCallback<ResponseSendOTP> callback);

    /**
     * Asynchronously refresh user token using refresh token
     *
     * @param config       LiquidPay Config {@link LiquidPayConfig}
     * @param refreshToken refresh token
     * @param callback
     */
    void refreshToken(@NonNull LiquidPayConfig config, @NonNull String refreshToken, @NonNull LiquidPayCallback<ResponseLogin> callback);

    /**
     * Asynchronously Logs In user using username and password.
     *
     * @param config   LiquidPay Config {@link LiquidPayConfig}
     * @param username username e.g email
     * @param password alphanumeric password
     */
    void login(LiquidPayConfig config, String username, String password, @NonNull final LiquidPayCallback<ResponseLogin> loginListener);

    /**
     * Asynchronously gets token using authorization code
     *
     * @param config
     * @param authorizationCode
     * @param tokenListener
     */
    void getToken(LiquidPayConfig config, String authorizationCode, String redirectUri, @NonNull final LiquidPayCallback<ResponseLogin> tokenListener);

    /**
     * Asynchronously validates user details for registration and return result in callback.
     *
     * @param config    LiquidPay Config {@link LiquidPayConfig}
     * @param mobile    user mobile
     * @param email     user email
     * @param promoCode promotional code (if any)
     * @param firstName user first name
     * @param lastName  user last name
     * @param callback  validation result as Callback({@link ResponseValidateUserDetails})
     */
    void validateUserDetails(@NonNull LiquidPayConfig config, @NonNull String mobile, @NonNull String email, @NonNull String promoCode,
                             @NonNull String firstName, @NonNull String lastName,
                             @NonNull String dialingCode, @NonNull LiquidPayCallback<ResponseValidateUserDetails> callback);


}
