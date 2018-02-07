package com.korvac.liquidpay.sdk.data;

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

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface defining all the APIs using Retrofit
 * Created by sohail on 5/5/2017.
 * Updated at 6/19/2017
 */

public interface LiquidPayAPI {
    /**
     * Get a list of payment cards to link virtual accounts
     *
     * @param apiKey           Unique application API key.
     * @param accessToken      Access Token
     * @param virtualAccountId Id Source of Virtual Account
     * @return response
     */
    @GET("v1/consumer/wallet/virtualaccounts/{virtualAccountSourceId}/eligible")
    Call<ResponseVirtualAccountPaymentCards> getPaymentCardsLinkToVirtualAccount(
            @Header("Liquid-Api-Key") String apiKey,
            @Header("Authorization") String accessToken,
            @Path("virtualAccountSourceId") String virtualAccountId
    );

    /**
     * Collect voucher from Giftbox to wallet
     *
     * @param apiKey      Unique application API key.
     *                    See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken Access Token
     * @param id          voucher_ids
     * @return response
     */
    @FormUrlEncoded @POST("v1/consumer/vouchers/save/giftbox")
    Call<ResponseWalletSaveVoucher> saveVoucherFromGiftboxToWallet(
            @Header("Liquid-Api-Key") String apiKey,
            @Header("Authorization") String accessToken,
            @Field("nonce") String nonce,
            @Field("sign") String sign,
            @Field("voucher_ids") String id
    );

    /**
     * Get Listing of vouchers in wallet
     *
     * @param apiKey      Unique application API key.
     *                    See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken Access Token
     * @return response
     */
    @GET("v1/consumer/vouchers/wallet")
    Call<ResponseWalletVoucherList> getVoucherListOfWallet(
            @Header("Liquid-Api-Key") String apiKey,
            @Header("Authorization") String accessToken
    );

    /**
     * Get Voucher GiftBox Listing
     *
     * @param apiKey      Unique application API key.
     *                    See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken Access Token
     * @return response
     */
    @GET("v1/consumer/vouchers/giftbox")
    Call<ResponseVoucherGiftBox> getVoucherGiftBoxListing(
            @Header("Liquid-Api-Key") String apiKey,
            @Header("Authorization") String accessToken
    );

    /**
     * Send Voucher To Friend
     *
     * @param apiKey      Unique application API key.
     *                    See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken Access Token
     * @param id          voucher_ids
     * @param mobile      destination mobile number
     * @return response
     */
    @FormUrlEncoded
    @POST("v1/consumer/vouchers/send")
    Call<ResponseVoucherSendToFriend> sendVoucherToFriend(
            @Header("Liquid-Api-Key") String apiKey,
            @Header("Authorization") String accessToken,
            @Field("nonce") String nonce,
            @Field("sign") String sign,
            @Field("voucher_ids") String id,
            @Field("destination_mobile") String mobile
    );

    /**
     * Add brand vouchers to wallet
     *
     * @param apiKey      Unique application API key.
     *                    See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken Access Token
     * @param id          voucher_ids
     * @return response
     */
    @FormUrlEncoded
    @POST("v1/consumer/vouchers/save/merchant")
    Call<ResponseWalletAddBrandVoucher> addBrandVoucherToWallet(
            @Header("Liquid-Api-Key") String apiKey,
            @Header("Authorization") String accessToken,
            @Field("nonce") String nonce,
            @Field("sign") String sign,
            @Field("voucher_ids") String id
    );

    /**
     * Retrieve a list of eligible Voucher for Payment
     *
     * @param apiKey       Unique application API key.
     *                     See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken  Access Token
     * @param source       Credit or Debit Card source ID
     * @param payee        Payee
     * @param currencyCode Currency Code
     * @param amount       Payment amount
     * @return response
     */
    @GET("v1/consumer/vouchers/wallet/eligible?")
    Call<ResponsePaymentListEligibleVoucher> retrievePaymentListEligibleVoucher(
            @Header("Liquid-Api-Key") String apiKey,
            @Header("Authorization") String accessToken,
            @Query("source") String source,
            @Query("payee") String payee,
            @Query("currencycode") String currencyCode,
            @Query("amount") double amount
    );

    /**
     * Retrieve list of eligible membership card for payment
     *
     * @param apiKey       Unique application API key. See
     *                     See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken  Access Token
     * @param source       Credit or Debit Card source ID
     * @param payee        Payee
     * @param currencyCode Currency Code
     * @param amount       Payment amount
     * @param offset
     * @param limit
     * @return response.
     */
    @GET("v1/consumer/wallet/membershipcards/eligible?")
    Call<ResponsePaymentListEligibleMembershipCard> retrievePaymentListEligibleMembershipCard(
            @Header("Liquid-Api-Key") String apiKey,
            @Header("Authorization") String accessToken,
            @Query("source") String source,
            @Query("payee") String payee,
            @Query("currencycode") String currencyCode,
            @Query("amount") double amount,
            @Query("offset") int offset,
            @Query("limit") int limit
    );

    /**
     * @param apiKey
     * @param accessToken
     * @param offset
     * @param limit
     * @return
     */
    @GET("v1/consumer/wallet/storevaluecards/new?")
    Call<ResponseAvailableStoredValueCard> getAvailableStoredValueCard(@Header("Liquid-Api-Key") String apiKey,
                                                                       @Header("Authorization") String accessToken,
                                                                       @Query("offset") int offset, @Query("limit") int limit);

    /**
     * @param apiKey
     * @param accessToken
     * @param offset
     * @param limit
     * @return
     */
    @GET("v1/consumer/wallet/membershipcards?")
    Call<ResponseGetAddedMembershipCard> getAddedMembershipCard(@Header("Liquid-Api-Key") String apiKey,
                                                                @Header("Authorization") String accessToken,
                                                                @Query("offset") int offset, @Query("limit") int limit);

    /**
     * @param apiKey
     * @param accessToken
     * @param membershipCardId
     * @param gender
     * @param dob
     * @param nonce
     * @param sign
     * @return
     */
    @FormUrlEncoded
    @POST("v1/consumer/wallet/membershipcards")
    Call<ResponseAddMembershipCard> addMembershipCard(@Header("Liquid-Api-Key") String apiKey,
                                                      @Header("Authorization") String accessToken,
                                                      @Field("id") String membershipCardId, @Field("gender") String gender,
                                                      @Field("dob") String dob, @Field("nonce") String nonce, @Field("sign") String sign);

    /**
     * @param apiKey
     * @param accessToken
     * @param membershipCardId
     * @return
     */
    @GET("v1/consumer/wallet/membershipcards/new/{membership_card_id}/fields")
    Call<ResponseRetrieveMembershipCardRequiredInfo> retrieveMembershipCardRequiredInfo(@Header("Liquid-Api-Key") String apiKey,
                                                                                        @Header("Authorization") String accessToken,
                                                                                        @Path("membership_card_id") String membershipCardId);

    /**
     * @param apiKey
     * @param accessToken
     * @param offset
     * @param limit
     * @return
     */
    @GET("v1/consumer/wallet/membershipcards/new?")
    Call<ResponseAvailableMembershipCard> getAvailableMembershipcard(@Header("Liquid-Api-Key") String apiKey,
                                                                     @Header("Authorization") String accessToken,
                                                                     @Query("OFFSET") int offset,
                                                                     @Query("LIMIT") int limit);

    /**
     * @param apiKey
     * @param accessToken
     * @param paymentId
     * @param paymentCardId
     * @param nonce
     * @param sign
     * @return
     */
    @FormUrlEncoded
    @POST("v1/consumer/payments/{payment_id}/pay")
    Call<ResponsePayment> payUsingVirtualAccount(@Header("Liquid-Api-Key") String apiKey,
                                                 @Header("Authorization") String accessToken,
                                                 @Path("payment_id") String paymentId,
                                                 @Field("source") String paymentCardId,
                                                 @Field("nonce") String nonce,
                                                 @Field("sign") String sign);



    /**
     * @param apiKey
     * @param accessToken
     * @param virtualAccountId
     * @param paymentCardId
     * @return
     */
    @FormUrlEncoded
    @POST("v1/consumer/wallet/virtualaccounts/{virtual_account_id}/unlink")
//    @DELETE("v1/consumer/wallet/virtualaccounts/{virtual_account_id}/unlink")
    Call<ResponseUnLinkCardFromVirtualAccount> unLinkCardFromVirtualAccount(@Header("Liquid-Api-Key") String apiKey,
                                                                            @Header("Authorization") String accessToken,
                                                                            @Path("virtual_account_id") String virtualAccountId,
                                                                            @Field("payment_card") String paymentCardId,
                                                                            @Field("nonce") String nonce,
                                                                            @Field("sign") String sign);

    /**
     * @param apiKey
     * @param accessToken
     * @param virtualAccountId
     * @param paymentCardId
     * @return
     */
    @FormUrlEncoded
    @POST("v1/consumer/wallet/virtualaccounts/{virtual_account_id}/link")
    Call<ResponseLinkCardToVirtualAccount> linkCardToVirtualAccount(@Header("Liquid-Api-Key") String apiKey,
                                                                    @Header("Authorization") String accessToken,
                                                                    @Path("virtual_account_id") String virtualAccountId,
                                                                    @Field("payment_card") String paymentCardId,
                                                                    @Field("nonce") String nonce,
                                                                    @Field("sign") String sign);

    /**
     * @param apiKey
     * @param accessToken
     * @param virtualAccountId
     * @return
     */
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "v1/consumer/wallet/virtualaccounts/{id}", hasBody = true)
    Call<ResponseDeleteVirtualAccount> deleteVirtualAccount(@Header("Liquid-Api-Key") String apiKey,
                                                            @Header("Authorization") String accessToken,
                                                            @Path("id") String virtualAccountId,
                                                            @Field("id") String id,
                                                            @Field("nonce") String nonce,
                                                            @Field("sign") String sign);

    /**
     * @param apiKey
     * @param accessToken
     * @return
     */
    @GET("v1/consumer/wallet/virtualaccounts")
    Call<ResponseGetAddedVirtualAccount> getAddedVirtualAccount(@Header("Liquid-Api-Key") String apiKey,
                                                                @Header("Authorization") String accessToken);

    /**
     * @param apiKey
     * @param accessToken
     * @param virtualAccountId
     * @param gender
     * @param dob
     * @param nonce
     * @param sign
     * @return
     */
    @FormUrlEncoded
    @POST("v1/consumer/wallet/virtualaccounts")
    Call<ResponseAddVirtualAccount> addVirtualAccount(@Header("Liquid-Api-Key") String apiKey,
                                                      @Header("Authorization") String accessToken,
                                                      @Field("id") String virtualAccountId,
                                                      @Field("gender") String gender,
                                                      @Field("dob") String dob,
                                                      @Field("nonce") String nonce,
                                                      @Field("sign") String sign);

    /**
     * @param apiKey
     * @param accessToken
     * @param offset
     * @param limit
     * @return
     */
    @GET("v1/consumer/wallet/virtualaccounts/new?")
    Call<ResponseAvailableVirtualAccount> getAvailableVirtualAccount(@Header("Liquid-Api-Key") String apiKey,
                                                                     @Header("Authorization") String accessToken,
                                                                     @Query("OFFSET") int offset,
                                                                     @Query("LIMIT") int limit);

    /**
     * Retrieve Virtual Account Required Info
     *
     * @param apiKey
     * @param accessToken
     * @param virtualAccountId
     * @return
     */
    @GET("v1/consumer/wallet/virtualaccounts/new/{virtual_account_id}/fields")
    Call<ResponseRetrieveVirtualAccountRequiredInfo> retrieveVirtualAccountRequiredInfo(@Header("Liquid-Api-Key") String apiKey,
                                                                                        @Header("Authorization") String accessToken,
                                                                                        @Path("virtual_account_id") String virtualAccountId);

    /**
     * Cancel Payment
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken User access token
     * @param paymentId   Payment id
     */
    @FormUrlEncoded
    @POST("v1/consumer/payments/{payment_id}/cancel")
    Call<ResponseCancelPayment> cancelPayment(@Header("Liquid-Api-Key") String apiKey,
                                              @Header("Authorization") String accessToken,
                                              @Field("payment_id") String paymentId,
                                              @Path("payment_id") String pathPaymentId);

    /**
     * Forgot Password
     *
     * @param apiKey Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param nonce  Request Time create
     * @param sign   Request Signature
     * @param email  User email address
     */
    @FormUrlEncoded
    @POST("v1/auth/consumer/passwordforget")
    Call<ResponseForgotPassword> forgotPassword(@Header("Liquid-Api-Key") String apiKey,
                                                @Field("nonce") String nonce,
                                                @Field("sign") String sign,
                                                @Field("email") String email);

    /**
     * Cancel Payment
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken User access token
     * @param nonce       Request Time create
     * @param sign        Request Signature
     * @param oldPassword User old password
     * @param newPassword User new password
     */
    @FormUrlEncoded
    @POST("v1/auth/consumer/passwordchange")
    Call<ResponseChangePassword> changePassword(@Header("Liquid-Api-Key") String apiKey,
                                                @Header("Authorization") String accessToken,
                                                @Field("nonce") String nonce,
                                                @Field("sign") String sign,
                                                @Field("password_old") String oldPassword,
                                                @Field("password_new") String newPassword);

    /**
     * Lists Payment cards
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken User access token
     * @return
     */
    @GET("v1/consumer/wallet/paymentcards")
    Call<ResponseListCards> listCards(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken);


    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "v1/consumer/wallet/paymentcards/{source}", hasBody = true)
//    @DELETE("v1/consumer/wallet/paymentcards/{source_id}")
    Call<ResponseDeleteCard> deleteCard(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken, @Path("source") String cardId,
                                        @Field("id") String id,
                                        @Field("nonce") String nonce, @Field("sign") String sign);

    /**
     * Create Payment
     *
     * @param apiKey        Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken   User access token
     * @param payee         Payee
     * @param serviceType   Payment service type
     * @param merchantRefNo Merchant reference number
     * @param amount        Payment amount
     * @param nonce         Request time created
     * @param sign          Request signature
     * @return
     */
    @FormUrlEncoded
    @POST("v1/consumer/payments")
    Call<ResponsePayment> createPayment(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken,
                                        @Field("payee") String payee, @Field("service_type") String serviceType,
                                        @Field("merchant_ref_no") String merchantRefNo, @Field("amount") String amount,
                                        @Field("nonce") String nonce, @Field("sign") String sign);

    /**
     * Get payment status
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken User access token
     * @param paymentId   Payment ID
     * @return
     */

    @GET("v1/consumer/payments/{payment_id}")
    Call<ResponsePayment> getPaymentStatus(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken,
                                           @Path("payment_id") String paymentId);

    /**
     * Get Payment Options
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken User access token
     * @param paymentId   Payment ID
     * @return
     */
    @GET("v1/consumer/payments/{payment_id}/pay")
    Call<ResponseGetPaymentOptions> getPaymentOptions(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken,
                                                      @Path("payment_id") String paymentId);

    /**
     * Making Payment Using Stored Value Card for better discounts
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken User access token
     * @param paymentId   Payment ID
     * @param source      Stored Value Card Id
     * @return
     */

    @FormUrlEncoded
    @POST("v1/consumer/payments/{payment_id}/pay")
    Call<ResponsePayment> payUsingStoredValueCard(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken,
                                                  @Path("payment_id") String paymentId, @Field("source") String source, @Field("nonce") String nonce, @Field("sign") String sign);

    /**
     * Making Payment Using Stored Value Card for better discounts
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken User access token
     * @param paymentId   Payment ID
     * @param source      Credit or Debit Card source ID
     * @return
     */
    @FormUrlEncoded
    @POST("v1/consumer/payments/{payment_id}/pay")
    Call<ResponsePayment> payUsingCreditOrDebitCard(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken,
                                                    @Path("payment_id") String paymentId, @Field("source") String source, @Field("nonce") String nonce, @Field("sign") String sign);


    /**
     * Add Stored Value Card
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken User acccess token
     * @param nationality User Nationality
     * @param id          Stored Value Card ID
     * @param identifier  User identifier
     * @param nonce       Request time created
     * @param sign        Request Signature
     * @return
     */
    @FormUrlEncoded
    @POST("v1/consumer/wallet/storevaluecards")
    Call<ResponseAddStoredValueCard> addStoredValueCard(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken,
                                                        @Field("nationality") String nationality, @Field("id") String id, @Field("identifier") String identifier,
                                                        @Field("nonce") String nonce, @Field("sign") String sign);

    /**
     * Topup Stored Value Card
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken User acccess token
     * @param recipient   Top-up recipient ID
     * @param originator  Top-up originator ID
     * @param topupAmount Top-up Amount
     * @return
     */
    @FormUrlEncoded
    @POST("v1/consumer/wallet/storevaluecards/{recipient}/topup")
    Call<ResponseTopUpStoredValueCard> topUpStoredValueCard(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken,
                                                            @Path("recipient") String recipient, @Field("originator") String originator, @Field("amount") String topupAmount,
                                                            @Field("nonce") String nonce, @Field("sign") String sign);

    /**
     * Get User added list of Stored Value Cards
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken User acccess token
     * @return
     */
    @GET("v1/consumer/wallet/storevaluecards")
    Call<ResponseGetStoredValueCard> getAddedStoredValueCard(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken);

    /**
     * Get User Stored Value Card Details
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken User acccess token
     * @param id          Stored Value Card ID
     * @return
     */
    @GET("v1/consumer/wallet/new/{id}/fields")
    Call<ResponseGetStoredValueCardRequiredDetails> getStoredValueCardRequiredDetails(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken,
                                                                                      @Path("id") String id);

    /**
     * Get topup options
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken User acccess token
     * @param recipient   Stored Value Card ID
     * @return
     */
    @GET("v1/consumer/wallet/storevaluecards/{recipient}/topup")
    Call<ResponseGetStoredValueCardTopupOptions> getStoredValueCardTopUpOptions(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken,
                                                                                @Path("recipient") String recipient);

    /**
     * Get Card Bin Details
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken User access token
     * @param cardNumber  Credit or Debit card number
     * @return
     */
    @GET("v1/consumer/wallet/paymentcards/bininfo/{card_number}")
    Call<ResponseCardBinDetails> getCardBinDetails(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken,
                                                   @Path("card_number") String cardNumber);


    /**
     * Adds credit/debit card
     *
     * @param apiKey
     * @param accessToken
     * @param cardNumber
     * @param cardHolderName
     * @param cvv
     * @param nonce
     * @param sign
     * @return
     */
    @FormUrlEncoded
    @POST("v1/consumer/wallet/paymentcards")
    Call<String> addCard(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken,
                         @Field("pan") String cardNumber, @Field("card_holder") String cardHolderName,
                         @Field("expiry_year") String expiryYear, @Field("expiry_month") String expiryMonth,
                         @Field("cvv") String cvv, @Field("nonce") String nonce, @Field("sign") String sign);

    /**
     * Check Credit or Debit Card Status
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken User access token
     * @param sourceId    Credit or Debit Card source ID
     * @return
     */
    @GET("v1/consumer/wallet/paymentcards/{source_id}")
    Call<ResponseCheckCardStatus> checkCardStatus(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken,
                                                  @Path("source_id") String sourceId);


    /**
     *  "email_address": "shulin.yang@liquidpay.com",
     "force_update": true,
     "nonce": "1496630900",
     "sign": "d8b4e60184c9547285c65dbd1277a1946cf7f508e0e32acee92e4408966837d77e62be01"
     */

    /**
     * Resends verification email
     * *
     *
     * @param apiKey       Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param accessToken  user access token
     * @param emailAddress email address to send email on
     * @return
     */
    @FormUrlEncoded
    @POST("v1/consumer/account/emailverification")
    Call<ResponseSendVerifyEmail> sendVerifyEmail(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization") String accessToken,
                                                  @Field("email_address") String emailAddress, @Field("force_update") boolean forceUpdate,
                                                  @Field("nonce") String nonce, @Field("sign") String sign);

    /**
     * Checks whether user has verified this email or not
     *
     * @param apiKey      valid api key
     * @param accessToken user access token
     * @return
     */

    @GET("v1/consumer/account/emailverification")
    Call<ResponseCheckEmailVerification> checkEmailVerificationStatus(@Header("Liquid-Api-Key") String apiKey, @Header("Authorization")
            String accessToken);

    /**
     * @return
     */
    @FormUrlEncoded
    @POST("v1/consumer/account/registration")
    Call<ResponseLogin> submitRegistrationDetails(@Header("Liquid-Api-Key") String apiKey, @Field("email_address") String emailAddress,
                                                  @Field("dialing_code") String dialingCode, @Field("mobile_number") String mobileNumber,
                                                  @Field("otp") String otp, @Field("first_name") String firstName,
                                                  @Field("last_name") String lastName, @Field("password") String password,
                                                  @Field("device_id") String deviceId, @Field("device_uid") String deviceUid,
                                                  @Field("device_model") String deviceModel, @Field("device_os") String deviceOs,
                                                  @Field("device_type") String deviceType, @Field("registered_source") String registeredSource,
                                                  @Field("promo_code") String promoCode, @Field("nonce") String nonce, @Field("sign") String sign

    );

    /**
     * Sends One Time Password on provided mobile number
     *
     * @param apiKey       Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param dialingCode  Country code
     * @param mobileNumber valid mobile phone number
     * @return
     */
    @FormUrlEncoded
    @POST("v1/consumer/account/otp")
    Call<ResponseSendOTP> sendOTP(@Header("Liquid-Api-Key") String apiKey, @Field("dialing_code") String dialingCode, @Field("mobile_number") String mobileNumber,
                                  @Field("nonce") String nonce, @Field("sign") String sign);

    /**
     * Refreshes User access token using refresh token
     *
     * @param apiKey       Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param refreshToken refresh token as received in {@link ResponseLogin}
     * @return {@link ResponseLogin }
     */
    @FormUrlEncoded
    @POST("v1/auth/consumer/refresh")
    Call<ResponseLogin> refreshToken(@Header("Liquid-Api-Key") String apiKey, @Field("refresh_token") String refreshToken,
                                     @Field("nonce") String nonce, @Field("sign") String sign);

    /**
     * Validates User details for Registration
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param mobile      user mobile phone number
     * @param email       user email address
     * @param promoCode   valid promo code
     * @param firstName   first name for registration
     * @param lastName    last name for registration
     * @param dialingCode mobile country code
     * @return {@link ResponseValidateUserDetails}
     */
    @FormUrlEncoded
    @POST("v1/consumer/account/validation")
    Call<ResponseValidateUserDetails> validateUserDetails(@Header("Liquid-Api-Key") String apiKey, @Field("mobile_number") String mobile,
                                                          @Field("email_address") String email, @Field("promo_code") String promoCode,
                                                          @Field("first_name") String firstName, @Field("last_name") String lastName,
                                                          @Field("dialing_code") String dialingCode,
                                                          @Field("nonce") String nonce, @Field("sign") String sign);


    /**
     * Logs In using email and password
     *
     * @param apiKey      Unique application API key. See <a href="https://developer.liquidpay.com/node/82">Getting Started</a>
     * @param id          Username e.g email address
     * @param password    password
     * @param deviceType  A = Android
     * @param gcmId       Unique Id for push notifications (GCM id)
     * @param deviceUID   Unique device Id, See {@link android.provider.Settings.Secure#ANDROID_ID}
     * @param deviceModel Device Model  {@link android.os.Build#MODEL}
     * @param deviceOS    OS release {@link android.os.Build.VERSION#RELEASE}
     * @return {@link Call<ResponseLogin> }
     */
    @FormUrlEncoded
    @POST("v1/auth/consumer/login")
//    @Headers({
//            "Content-Type: application/json",
//
//    })
    Call<ResponseLogin> loginWithPassword(@Header("Liquid-Api-Key") String apiKey, @Field("username") String id,
                                          @Field("password") String password, @Field("device_type") String deviceType,
                                          @Field("device_id") String gcmId, @Field("device_uid") String deviceUID,
                                          @Field("device_model") String deviceModel, @Field("device_os") String deviceOS,
                                          @Field("nonce") String nonce, @Field("sign") String sign);

    /**
     * Gets token using authorization code SSO
     *
     * @param apiKey
     * @param authorizationCode
     * @param redirectUri
     * @param deviceType
     * @param gcmId
     * @param deviceUID
     * @param deviceModel
     * @param deviceOS
     * @param nonce
     * @param sign
     * @return
     */
    @FormUrlEncoded
    @POST("v1/auth/consumer/token")
    Call<ResponseLogin> getToken(@Header("Liquid-Api-Key") String apiKey,
                                 @Field("authorization_code") String authorizationCode,
                                 @Field("redirect_uri") String redirectUri,
                                 @Field("device_type") String deviceType,
                                 @Field("device_id") String gcmId, @Field("device_uid") String deviceUID,
                                 @Field("device_model") String deviceModel, @Field("device_os") String deviceOS,
                                 @Field("nonce") String nonce, @Field("sign") String sign);


}
