package com.domain.sohail.samplelibraryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.korvac.liquidpay.sdk.data.response.AvailableMembershipCard;
import com.korvac.liquidpay.sdk.data.response.BaseCard;
import com.korvac.liquidpay.sdk.data.response.Card3D;
import com.korvac.liquidpay.sdk.data.response.CardNon3D;
import com.korvac.liquidpay.sdk.data.response.Error;
import com.korvac.liquidpay.sdk.data.response.MembershipCard;
import com.korvac.liquidpay.sdk.data.response.PaymentOptionsVirtualAccounts;
import com.korvac.liquidpay.sdk.data.response.RequiredField;
import com.korvac.liquidpay.sdk.data.response.ResponseAddMembershipCard;
import com.korvac.liquidpay.sdk.data.response.ResponseAddStoredValueCard;
import com.korvac.liquidpay.sdk.data.response.ResponseAddVirtualAccount;
import com.korvac.liquidpay.sdk.data.response.ResponseAvailableMembershipCard;
import com.korvac.liquidpay.sdk.data.response.ResponseAvailableStoredValueCard;
import com.korvac.liquidpay.sdk.data.response.ResponseAvailableVirtualAccount;
import com.korvac.liquidpay.sdk.data.response.ResponseCancelPayment;
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
import com.korvac.liquidpay.sdk.data.response.VirtualAccount;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.main.LiquidPay;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayScope;
import com.korvac.liquidpay.sdk.main.enums.ServiceType;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    private static final String GCM_ID = "0df738df25c57c8a1d39bd8112e332375b4a402aa4cfcb3ea3e8198ade2173e9";
    private static final String API_KEY = "vFmKEtsmYBnhqmFhgfdert56HzX";
    public static final String API_SECRET = "c3b26bbdd446af82";

    public static final String SESSION_KEY = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE2MzIiLCJzZXNzaW9uX2tleSI6Ijg5Y2I5Y2M2LTliMzUtNGE0Yy1hMTgzLWYzMzQwNzFmMTA3OCIsInR5cGUiOiJ0eXAiLCJpYXQiOjE0OTQyMzU4NzMsImV4cCI6MTQ5NDIzNjc3MywiandpIjoiYjI4MGE3ZDMtNDc0ZS00ODYzLWJmMjEtNWEyYzhhOGI5YjJjIn0.JfvphJnc4S8m0TZWDWNmvMhdMN5sJo7AEG4LxgdaDsm_ToaWU3UiVpgbg0EKjgRlpLK2ZHHgDji2w0uO4L85xQ";
    public static final String USER_ID = "1632";
    private static final String TAG = "MainActivity";

    private String CARD_ID = "source_eyJzb3VyY2VfdHlwZSI6ImNyZWRpdGNhcmQiLCJjYXJkX2d1aWQiOiJkZmE5YzJlMS00NmMzLTQ2OWUtODhjNi00MjNmZDg0NWM3ZWYiLCJjdXN0X25vIjoiMjIzMiJ9";

    public String paymentId = "";
    public String payee = "";
    public String paymentOptionVirtualAccId = "";
    public String paymentOptionCard = "";

    public String vaId = "";

    private String requestedDOB = "";
    private String requestedGender = "";

    private String membershipId = "";


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //setTitleColor();
        // LiquidPay.getInstance().init(this, API_KEY, API_SECRET, "");
        LiquidPay.getInstance().init(this, API_KEY, API_SECRET, "", "abc", "code", "https://www.google.com.sg");


        //LOGIN
        findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(getApplicationContext(), LauncherActivity.class));
//                testLogin();
                // String test_url = "https://uat.liquidpay.com/openweb/development/sso/v1/auth/consumer/authorize?response_type=code&client_id=vFmKEtsmYBnhqmFli8CmGdu3kWYtAHzX&redirect_uri=https://www.google.com.sg&scope=consumer_account+consumer_wallet&state=abc";
//                testOAuthLogin(new String[]{LiquidPayScope.SCOPE_CONSUMER_ACCOUNT, LiquidPayScope.SCOPE_CONSUMER_WALLET});
                testOAuthLogin(LiquidPayScope.SCOPE_ALL);
            }
        });

        //FORGOT PASSWORD
        findViewById(R.id.button_forgot_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testForgotPassword();
            }
        });

        //CHANGE PASSWORD
        findViewById(R.id.button_change_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testChangePassword();
            }
        });

        //**************************
        //REGISTRATION
        //*************************
        //validate
        findViewById(R.id.button_validate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testValidation();
            }
        });
        //send otp
        findViewById(R.id.button_send_otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testSendOTP();
            }
        });
        //submit registration
        findViewById(R.id.button_submit_registration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testSubmitRegistration();
            }
        });

        //send email verification
        findViewById(R.id.button_resend_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testResendEmailVerification();

            }
        });

        //check email verification
        findViewById(R.id.button_check_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testCheckEmailVerification();

            }
        });

        //**************************
        //CREDIT/DEBIT CARDS
        //*************************

        //get bin details
        findViewById(R.id.button_get_card_bin_details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testGetCardBinDetails();
            }
        });
        //add card
        findViewById(R.id.button_add_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testAddCARD();

            }
        });
        //check card status
        findViewById(R.id.button_check_card_status).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testCheckCardStatus();
            }
        });
        findViewById(R.id.button_delete_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testDeleteCard();
            }
        });
        findViewById(R.id.button_list_cards).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testListCards();
            }
        });


        //**************************
        //STORE VALUE CARDS
        //*************************
        //get svc details
        findViewById(R.id.button_getsvcDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testGetSVCDetails(2);
            }
        });

        //add svc
        findViewById(R.id.button_add_svc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testAddSVC();
            }
        });

        //get added svc
        findViewById(R.id.button_get_added_svc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testGetSVC(2);
            }
        });

        //get available Stored value card
        findViewById(R.id.button_get_available_stored_value_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testGetAvailableStoredValueCard();
            }
        });

        //**************************
        //TOPUP
        //*************************

        //get svc topup option
        findViewById(R.id.button_getsvcTopupOptions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testGetTopUpOptions(2);
            }
        });

        //topup svc
        findViewById(R.id.button_topup_svc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testTopUpSVC();
            }
        });

        //**************************
        //PAYMENTS
        //*************************

        //create payment
        findViewById(R.id.button_create_payment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testCreatePayment();
            }
        });

        //cancel payment
        findViewById(R.id.button_cancel_payment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testCancelPayment();
            }
        });

        //get payment options
        findViewById(R.id.button_get_payment_options).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testGetPaymentOptions();
            }
        });
        //pay by svc
        findViewById(R.id.button_pay_using_svc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testPayUsingSVC();
            }
        });
        //pay by card
        findViewById(R.id.button_pay_using_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testPayUsingCard();
            }
        });
        //get payment status
        findViewById(R.id.button_get_payment_status).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!paymentId.equalsIgnoreCase("")) {
                    testGetPaymentStatus();
                } else {
                    Toast.makeText(getApplicationContext(), "Create Payment 1st", LENGTH_SHORT).show();
                }
            }
        });

        //Retrieve eligible vouchers for payment
        findViewById(R.id.button_retrieve_payment_list_eligible_voucher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrievePaymentListEligibleVoucher();
            }
        });

        //************
        //VIRTUAL ACCOUNT
        //************
        //get available virtual account
        findViewById(R.id.button_get_available_virtual_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testGetAvailableVirtualAccount();
            }
        });
        //get required field to add virtual account
        findViewById(R.id.button_get_required_field_virtual_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testGetAddVirtualAccountRequiredInfo();
            }
        });
        //add virtual account
        findViewById(R.id.button_add_virtual_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testAddVirtualAccount();
            }
        });
        //get added virtual account
        findViewById(R.id.button_get_added_va).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testGetAddedVirtualAccount();
            }
        });

        //delete virtual account
        findViewById(R.id.button_delete_va).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testDeleteVirtualAccount();
            }
        });

        //link virtual account
        findViewById(R.id.button_link_credit_or_debit_card_to_virtual_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testLinkVirtualAccount();
            }
        });

        //unlink virtual account
        findViewById(R.id.button_un_link_credit_or_debit_card_from_virtual_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testUnLinkVirtualAccount();
            }
        });

        //pay using virtual account
        findViewById(R.id.button_pay_using_va).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testPayUsingVirtualAccount();
            }
        });

        //Get a list of payment cards to link virtual accounts
        findViewById(R.id.button_get_list_payment_card_link_to_virtual_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPaymentCardsLinkToVirtualAccount();
            }
        });

        /*######################### MEMBERSHIP CARD ###################*/
        findViewById(R.id.button_retrieve_payment_list_eligible_membership_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrievePaymentListEligibleMembershipCard();
            }
        });

        findViewById(R.id.button_get_available_membership_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testGetAvailableMembershipCard();
            }
        });

        //retrieve required info for add membershipcard
        findViewById(R.id.button_get_required_field_membership_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testGetMembershipcardRequiredInfo();
            }
        });

        //add membership card
        findViewById(R.id.button_add_membership_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testAddMembershipCard();
            }
        });

        //get added membership card
        findViewById(R.id.button_get_added_membership_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testGetAddedMembershipCard();
            }
        });

        //Add brand vouchers to wallet
        findViewById(R.id.button_add_brand_vouchers_to_wallet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBrandVoucherToWallet();
            }
        });

        //Listing of vouchers in wallet
        findViewById(R.id.button_get_voucher_list_of_wallet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getVoucherListOfWallet();
            }
        });

        // Collect voucher from Giftbox to wallet
        findViewById(R.id.button_save_voucher_from_gift_box_to_wallet).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                saveVoucherFromGiftboxToWallet();
            }
        });

        // Send Voucher to friend
        findViewById(R.id.button_send_voucher_to_friend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVoucherToFriend();
            }
        });

        //Get Voucher GiftBox Listing
        findViewById(R.id.button_get_voucher_gift_box_listing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getVoucherGiftBoxListing();
            }
        });

    }

    private void testListCards() {
        LiquidPay.getInstance().listPaymentCards(new LiquidPayCallback<ResponseListCards>() {
            @Override
            public void onSuccess(ResponseListCards response) {

                Log.d(MainActivity.class.getSimpleName(), "testListCards=" + response.toString());
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.e(MainActivity.class.getSimpleName(), "testListCards= errorMessage=" + errorMessage);
                Toast.makeText(MainActivity.this, throwable.getMessage(), LENGTH_SHORT).show();

            }
        });
    }

    private void testDeleteCard() {


        LiquidPay.getInstance().deletePaymentCard(CARD_ID, new LiquidPayCallback<ResponseDeleteCard>() {
            @Override
            public void onSuccess(ResponseDeleteCard response) {
                Log.d(MainActivity.class.getSimpleName(), "testDeleteCard=" + response.toString());
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.e(MainActivity.class.getSimpleName(), "testDeleteCard= errorMessage=" + errorMessage);
                Toast.makeText(MainActivity.this, throwable.getMessage(), LENGTH_SHORT).show();
            }
        });


    }

    private void testPayUsingCard() {
        LiquidPay.getInstance().payUsingPaymentCard(paymentId, "source_eyJzb3VyY2VfdHlwZSI6ImNyZWRpdGNhcmQiLCJjYXJkX2d1aWQiOm51bGwsImN1c3Rfbm8iOiIyMTgzIn0=", new LiquidPayCallback<ResponsePayment>() {
            @Override
            public void onSuccess(ResponsePayment response) {
                Log.d(MainActivity.class.getSimpleName(), "testPayUsingCard: onSuccess: response=" + response.toString());
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.e(MainActivity.class.getSimpleName(), "testPayUsingCard: onError: errorMessage=" + errorMessage);
                Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();
            }
        });
    }

    private void testGetPaymentOptions() {
        LiquidPay.getInstance().getPaymentOptions(paymentId, new LiquidPayCallback<ResponseGetPaymentOptions>() {
            @Override
            public void onSuccess(ResponseGetPaymentOptions response) {
                Log.d(MainActivity.class.getSimpleName(), "testGetPaymentOptions: onSuccess: response=" + response.toString());
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();

                List<PaymentOptionsVirtualAccounts> listVirtualAccounts = response.items().listVirtualAccounts();
                if (0 < listVirtualAccounts.size())
                    paymentOptionVirtualAccId = listVirtualAccounts.get(0).id();

                paymentOptionCard = response.items().listCreditOrDebitCard().get(0).id();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.e(MainActivity.class.getSimpleName(), "testGetPaymentOptions: onError: errorMessage=" + errorMessage);
                Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();
            }
        });
    }

    private void testCheckCardStatus() {


        LiquidPay.getInstance().checkPaymentCardStatus("source_eyJzb3VyY2VfdHlwZSI6ImNyZWRpdGNhcmQiLCJjYXJkX2d1aWQiOiJhZDg3ZWEyNC04ZTBkLTQyN2YtOWQzMi0xNjZjMDZkMjUwMTMiLCJjdXN0X25vIjoiMTYzMiJ9", new LiquidPayCallback<ResponseCheckCardStatus>() {
            @Override
            public void onSuccess(ResponseCheckCardStatus response) {
                Log.d(MainActivity.class.getSimpleName(), "testCheckCardStatus: onSuccess: response=" + response.toString());
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.e(MainActivity.class.getSimpleName(), "testCheckCardStatus: onError: errorMessage=" + errorMessage);
                Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();
            }
        });

    }

    private void testAddCARDNon3D() {

        DateTime expiry = new DateTime().withMonthOfYear(12).withYear(2020);
        LiquidPay.getInstance().addPaymentCard("5520920772465299", "189", "Visa card", expiry.toDate(), new LiquidPayCallback<BaseCard>() {
            @Override
            public void onSuccess(BaseCard response) {
                Log.d(MainActivity.class.getSimpleName(), "testAddCard: onSuccess: response=" + response.toString());
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();

                if (response instanceof Card3D) {
                    Card3D card3D = (Card3D) response;
                    Log.d(MainActivity.class.getSimpleName(), "card3d=" + card3D.toString());
                } else if (response instanceof CardNon3D) {
                    CardNon3D cardNon3D = (CardNon3D) response;
                    Log.d(MainActivity.class.getSimpleName(), "cardNon3d=" + cardNon3D.toString());

                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.e(MainActivity.class.getSimpleName(), "testAddCard: onError: errorMessage=" + errorMessage);
                Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();

            }
        });
    }

    private void testAddCARD() {


        DateTime expiry = new DateTime().withMonthOfYear(5).withYear(2018);
        LiquidPay.getInstance().addPaymentCard("5289133312739337", "419", "master", expiry.toDate(), new LiquidPayCallback<BaseCard>() {
            @Override
            public void onSuccess(BaseCard response) {
                Log.d(MainActivity.class.getSimpleName(), "testAddCard: onSuccess: response=" + response.toString());
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();

                if (response instanceof Card3D) {
                    Card3D card3D = (Card3D) response;
                    Log.d(MainActivity.class.getSimpleName(), "card3d=" + card3D.toString());
                } else if (response instanceof CardNon3D) {
                    CardNon3D cardNon3D = (CardNon3D) response;
                    Log.d(MainActivity.class.getSimpleName(), "cardNon3d=" + cardNon3D.toString());

                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.e(MainActivity.class.getSimpleName(), "testAddCard: onError: errorMessage=" + errorMessage);

                if (throwable instanceof LiquidPayException) {
                    LiquidPayException exception = (LiquidPayException) throwable;
                    Log.e(MainActivity.class.getSimpleName(), "testAddCard: onError: exception:message=" + exception.getMessage());
                    Log.e(MainActivity.class.getSimpleName(), "testAddCard: onError: exception:errorList=" + exception.getErrorList().toString());
                }
                //Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void testGetCardBinDetails() {

        //5400-1227-7509-2932
        //5400122775092932

//        LiquidPay.getInstance().getCardBinDetailsV2("5400122775092932", new LiquidPayCallback<ResponseCardBinDetails>() {
//            @Override
//            public void onSuccess(ResponseCardBinDetails response) {
//                Log.d(MainActivity.class.getSimpleName(), "testGetCardBinDetails: onSuccess: response=" + response.toString());
//                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable throwable, String errorMessage) {
//                Log.e(MainActivity.class.getSimpleName(), "testGetCardBinDetails: onError: errorMessage=" + errorMessage);
//                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    private void testAPIs() {


//        LiquidPay.getInstance().testSecurity();


//        testLogin();
//        testValidation();
        // testSubmitRegistration();
//        testSendOTP();

    }

    private void testResendEmailVerification() {
        Log.d("MainActivity", "testResendEmailVerification");

        LiquidPay.getInstance().resendEmailVerification("aldirengunawan@gmail.com", false, new LiquidPayCallback<ResponseSendVerifyEmail>() {
            @Override
            public void onSuccess(ResponseSendVerifyEmail response) {
                Log.d("resendVerification", "onSuccess=" + response.toString());
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.e("resendVerification", "onError=" + errorMessage);
                Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();
            }
        });


    }

    private void testCheckEmailVerification() {
        Log.d("MainActivity", "testCheckEmailVerification");
        LiquidPay.getInstance().checkEmailVerificationStatus(new LiquidPayCallback<ResponseCheckEmailVerification>() {
            @Override
            public void onSuccess(ResponseCheckEmailVerification response) {
                Log.d(MainActivity.class.getSimpleName(), "checkEmailVerificationv2: onSuccess=" + response.toString());
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.d(MainActivity.class.getSimpleName(), "checkEmailVerificationv2: onError=" + throwable.toString());
                Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();
            }
        });
    }

    private void testSubmitRegistration() {
        Log.d("MainActivity", "testSubmitRegistration");

        LiquidPay.getInstance().submitRegistrationDetails("sohail123@mailinator.com", "65", "84123999", "000000",
                "Sohail", "Aziz", "pass1234", null, null, new LiquidPayCallback<ResponseLogin>() {
                    @Override
                    public void onSuccess(ResponseLogin response) {
                        Log.d("testSubmitRegistration", "onSuccess=" + response.toString());
                        Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable throwable, String errorMessage) {
                        Log.d("testSubmitRegistration", "onFailure=" + errorMessage);
                        Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();

                    }
                });
    }

    private void testSendOTP() {

        Log.d("MainActivity", "testSendOTP");
        LiquidPay.getInstance().getOTPForRegistration("65", "84123999", new LiquidPayCallback<ResponseSendOTP>() {
            @Override
            public void onSuccess(ResponseSendOTP response) {
                Log.d("testSendOTP", "onSuccess:response=" + response.toString());
                Log.d("testSendOTP", "calling submit registration");
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();

                //testSubmitRegistration();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.e("testSendOTP", "onError:error=" + errorMessage);

                Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();


            }
        });

    }

    private void testOAuthLogin(String[] scopes) {
        LiquidPay.getInstance().startOAuthLogin(this, 1234, scopes);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1234) {
            if (resultCode == RESULT_OK) {
                ResponseLogin responseLogin = data.getParcelableExtra("response");
                Log.d(TAG, "onActivityResult:RESULT_OK: response=" + responseLogin.toString());

            } else if (resultCode == RESULT_CANCELED) {

                String errorCode = data.getStringExtra("code");
                String errorMessage = data.getStringExtra("error");

                Log.e(TAG, "onActivityResult:RESULT_CANCELLED: errorCode=" + errorCode);
                Log.e(TAG, "onActivityResult:RESULT_CANCELLED: errorMessage=" + errorMessage);
            }
        }
    }

    private void testLogin() {

        Log.d("MainActivity", "testLogin");

        LiquidPay.getInstance().loginWithPassword("thanhmvp32@mailinator.com", "pass1234", new LiquidPayCallback<ResponseLogin>() {
            //        LiquidPay.getInstance().loginWithPassword("thanhmvp40@mailinator.com", "pass1234", new LiquidPayCallback<ResponseLogin>() {
            @Override
            public void onSuccess(ResponseLogin response) {

                Log.d("testLogin", "onSuccess:accessToken=" + response.toString());
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e, String errorMessage) {
                Log.d("testLogin", "onError=" + errorMessage);
                Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();

                if (e instanceof LiquidPayException) {
                    LiquidPayException liquidPayException = (LiquidPayException) e;
                    Log.d("testLogin", "onError: LiquidPayException: errorList=" + liquidPayException.getErrorList().toString());
                }

                if (e instanceof IOException) {
                    Log.d("testLogin", "onError: network error after instanceof");
                }
            }
        });
    }

    private void testForgotPassword() {

        LiquidPay.getInstance().forgotPassword("aldirenaldigunawa@gmail.com", new LiquidPayCallback<ResponseForgotPassword>() {
            @Override
            public void onSuccess(ResponseForgotPassword response) {
                Toast.makeText(getApplicationContext(), "" + response.toString(), LENGTH_SHORT).show();
                Log.d(TAG, "forgotPassword: success");
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.e(TAG, "forgotPassword: error" + errorMessage);

                toastError(throwable);
            }
        });
    }

    private void testChangePassword() {

        LiquidPay.getInstance().changePassword("pass0000", "pass1234", new LiquidPayCallback<ResponseChangePassword>() {
            @Override
            public void onSuccess(ResponseChangePassword response) {
                Toast.makeText(getApplicationContext(), "" + response.toString(), LENGTH_SHORT).show();
                Log.d(TAG, "testChangePassword : success, response : " + response.toString());
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.e(TAG, "testChangePassword: error" + errorMessage);

                toastError(throwable);
            }
        });

    }

    private void testCancelPayment() {
        LiquidPay.getInstance().cancelPayment(paymentId, new LiquidPayCallback<ResponseCancelPayment>() {
            @Override
            public void onSuccess(ResponseCancelPayment response) {
                Toast.makeText(getApplicationContext(), "" + response.toString(), LENGTH_SHORT).show();
                Log.d(TAG, "testCancelPayment: success, response : " + response.toString());
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.e(TAG, "testCancelPayment: error" + errorMessage);
                toastError(throwable);
            }
        });
    }


    private void testValidation() {


        //validate first name
        LiquidPay.getInstance().validateUserFirstName("sohail", new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();
                Log.d("mainActivity:onSuccess", response.toString());

            }

            @Override
            public void onError(Throwable e, String errorMessage) {

                Log.e("mainActivity:onError", errorMessage);
                Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();

                if (e instanceof IOException) {
                    Log.e("mainActivity:onError", "this is a network error ");
                }
            }
        });

        //vlaidate last name
        LiquidPay.getInstance().validateUserLastName("aziz", new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();

                Log.d("mainActivity:onSuccess", response.toString());

            }

            @Override
            public void onError(Throwable e, String errorMessage) {

                Log.e("mainActivity:onError", errorMessage);
                Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();

                if (e instanceof IOException) {
                    Log.e("mainActivity:onError", "this is a network error ");
                }
            }
        });

        //validate email
        LiquidPay.getInstance().validateUserEmail("sohail@aziz.com", new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {

                Log.d("mainActivity:onSuccess", response.toString());

            }

            @Override
            public void onError(Throwable e, String errorMessage) {

                Log.e("mainActivity:onError", errorMessage);

                if (e instanceof IOException) {
                    Log.e("mainActivity:onError", "this is a network error");
                }
            }
        });

        //validate phone number
        LiquidPay.getInstance().validateUserMobile("124698563", new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {

                Log.d("mainActivity:onSuccess", response.toString());

            }

            @Override
            public void onError(Throwable e, String errorMessage) {

                Log.e("mainActivity:onError", errorMessage);

                if (e instanceof IOException) {
                    Log.e("mainActivity:onError", "this is a network error");
                }
            }
        });
        //validate promo code
        LiquidPay.getInstance().validatePromoCode("12321", new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {

                Log.d("mainActivity:onSuccess", response.toString());

            }

            @Override
            public void onError(Throwable e, String errorMessage) {

                Log.e("mainActivity:onError", errorMessage);

                if (e instanceof IOException) {
                    Log.e("mainActivity:onError", "this is a network error");
                }
            }
        });


        LiquidPay.getInstance().validateUserDialingCode("62", new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                Log.d(this.getClass().getSimpleName(), "validateUserDialingCode:onSuccess" + response.toString());
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.d(this.getClass().getSimpleName(), "validateUserDialingCode:onError" + throwable.toString());
            }
        });


        LiquidPay.getInstance().validateRegistrationDetails("123455", "sohail@email.com", "234354", "s0h@1l", "@z!z", "65", new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                Log.d("MainActivity:onSuccess", response.toString());
            }

            @Override
            public void onError(Throwable exception, String errorMessage) {

                Log.e("MainActivity:onError", exception.getMessage());
            }
        });


    }

    private void testCreatePayment() {
        LiquidPay.getInstance().createPayment("merchant_eyJvdXRsZXRfaWQiOiJnYWxheGlzMW5vcnRoIiwibG9jX25vIjoiIiwibWVyY2hhbnRfaWQiOiJzcGluZWxsaWNvZmZlZSJ9", ServiceType.SCAN_PAY, "e5b43f7a9507d651", "2.50", new LiquidPayCallback<ResponsePayment>() {
            @Override
            public void onSuccess(ResponsePayment response) {
                Log.d(MainActivity.class.getSimpleName(), "createPayment:onSuccess : " + response.toString());
                paymentId = response.id();
                payee = response.payee();
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();
                Log.d(MainActivity.class.getSimpleName(), "createPayment:onError : " + throwable.toString());
            }
        });
    }

    private void testGetPaymentStatus() {
        LiquidPay.getInstance().checkPaymentStatus(paymentId, new LiquidPayCallback<ResponsePayment>() {
            @Override
            public void onSuccess(ResponsePayment response) {
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();
                Log.d(MainActivity.class.getSimpleName(), "checkPaymentStatus:onSuccess : " + response.toString());
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();
                Log.d(MainActivity.class.getSimpleName(), "checkPaymentStatus:onError : " + throwable.toString());
            }
        });
    }

    private void testRetrievePaymentListEligibleVoucher() {
        String source = "source_eyJzb3VyY2VfdHlwZSI6InN0b3JldmFsdWVjYXJkIiwibWVyY2hhbnRfaWQiOiJMSVFVSUQiLCJzdG9yZV92YWx1ZV90eXBlIjoiTFFEU1YiLCJjdXN0X25vIjoiMjMyMiJ9";
        String payee = "merchant_eyJtZXJjaGFudF9pZCI6ImNoaWxsYXNnYWxheGlzIiwib3V0bGV0X2lkIjoiY2hpbGxhc2dhbGF4aXMiLCJsb2Nfbm8iOiItIn0K";
        String currencyCode = "SGD";
        double amount = 10;

        LiquidPay.getInstance().retrievePaymentListEligibleVoucher(
                source,
                payee,
                currencyCode,
                amount,
                new LiquidPayCallback<ResponsePaymentListEligibleVoucher>() {
                    @Override public void onSuccess(ResponsePaymentListEligibleVoucher response) {
                        Log.d(TAG, "retrievePaymentListEligibleVoucher : type " + response.type());
                        Log.d(TAG, "retrievePaymentListEligibleVoucher : url " + response.url());
                        Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();
                    }

                    @Override public void onError(Throwable throwable, String errorMessage) {
                        toastError(throwable);
                    }
                }
        );
    }

    private void testPayUsingSVC() {
        LiquidPay.getInstance().payUsingStoreValueCard(paymentId, "source_eyJzb3VyY2VfdHlwZSI6InN0b3JldmFsdWVjYXJkIiwibWVyY2hhbnRfaWQiOiJMSVFVSUQiLCJzdG9yZV92YWx1ZV90eXBlIjoiTFFEU1YiLCJjdXN0X25vIjoiMjE4MyJ9", new LiquidPayCallback<ResponsePayment>() {
            @Override
            public void onSuccess(ResponsePayment response) {
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();
                Log.d(MainActivity.class.getSimpleName(), "payUsingStoreValueCard:onSuccess : " + response.toString());
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();
                Log.d(MainActivity.class.getSimpleName(), "payUsingStoreValueCard:onError : " + throwable.toString());
            }
        });
    }

    private void testAddSVC() {
        LiquidPay.getInstance().addStoreValueCard("item_eyJ0eXBlIjoiaXRlbSIsIm1lcmNoYW50X2lkIjoiR2V0SnVpY2VkTWVyIiwic3RvcmVfdmFsdWVfdHlwZSI6Ik1TVkMifQ==", "S2206263H", "SG", new LiquidPayCallback<ResponseAddStoredValueCard>() {
            @Override
            public void onSuccess(ResponseAddStoredValueCard response) {
                Log.d(MainActivity.class.getSimpleName(), "addStoreValueCard:onSuccess : " + response.toString());
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.d(MainActivity.class.getSimpleName(), "addStoreValueCard:onError : " + throwable.toString());
                Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();
            }
        });
    }

    private void testTopUpSVC() {
        LiquidPay.getInstance().topUpStoreValueCard("source_eyJzb3VyY2VfdHlwZSI6InN0b3JldmFsdWVjYXJkIiwibWVyY2hhbnRfaWQiOiJMUURTViIsInN0b3JlX3ZhbHVlX3R5cGUiOiJMSVFVSUQiLCJjdXN0X25vIjoiMjE4MyJ9", "source_eyJzb3VyY2VfdHlwZSI6ImNyZWRpdGNhcmQiLCJjYXJkX2d1aWQiOiI3ZDhkNDk2Mi00MjkzLTQ5MjUtOTMzYy0wYjY5M2I4NTYxYTYiLCJjdXN0X25vIjoiMjE4MyJ9", "30", new LiquidPayCallback<ResponseTopUpStoredValueCard>() {
            @Override
            public void onSuccess(ResponseTopUpStoredValueCard response) {
                Log.d(MainActivity.class.getSimpleName(), "topUpStoreValueCard:onSuccess : " + response.toString());
                Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.d(MainActivity.class.getSimpleName(), "topUpStoreValueCard:onError : " + throwable.toString());
                Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();
            }
        });
    }

    private void testGetSVC(int version) {
        {
            LiquidPay.getInstance().listUserStoreValueCards(new LiquidPayCallback<ResponseGetStoredValueCard>() {
                @Override
                public void onSuccess(ResponseGetStoredValueCard response) {
                    Log.d(MainActivity.class.getSimpleName(), "listUserStoreValueCards:onSuccess : " + response.toString());
                    Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable throwable, String errorMessage) {
                    Log.d(MainActivity.class.getSimpleName(), "listUserStoreValueCards:onError : " + throwable.toString());
                    Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();
                }
            });
        }
    }

    private void testGetSVCDetails(int version) {
        {
            LiquidPay.getInstance().getStoreValueCardRequiredDetails("source_eyJzb3VyY2VfdHlwZSI6InN0b3JldmFsdWVjYXJkIiwibWVyY2hhbnRfaWQiOiJMUURTViIsInN0b3JlX3ZhbHVlX3R5cGUiOiJMSVFVSUQiLCJjdXN0X25vIjoiMjE4MyJ9", new LiquidPayCallback<ResponseGetStoredValueCardRequiredDetails>() {
                @Override
                public void onSuccess(ResponseGetStoredValueCardRequiredDetails response) {
                    Log.d(MainActivity.class.getSimpleName(), "getStoreValueCardRequiredDetails:onSuccess : " + response.toString());
                    Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable throwable, String errorMessage) {
                    Log.d(MainActivity.class.getSimpleName(), "getStoreValueCardRequiredDetails: onError=" + throwable.toString());
                    Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();
                }
            });
        }
    }

    private void testGetAvailableStoredValueCard() {

        int offset = 0;
        int limit = 3;
        LiquidPay.getInstance().getAvailableStoredValueCard(offset, limit, new LiquidPayCallback<ResponseAvailableStoredValueCard>() {
            @Override
            public void onSuccess(ResponseAvailableStoredValueCard response) {
                Log.d(TAG, "testGetAvailableStoredValueCard # getAvailableStoredValueCard : available count : " + response.totalCount());
                Toast.makeText(getApplicationContext(), "Response : " + response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });

    }

    private void testGetTopUpOptions(int version) {
        {
            LiquidPay.getInstance().getStoredValueCardTopUpOptions("source_eyJzb3VyY2VfdHlwZSI6InN0b3JldmFsdWVjYXJkIiwibWVyY2hhbnRfaWQiOiJMUURTViIsInN0b3JlX3ZhbHVlX3R5cGUiOiJMSVFVSUQiLCJjdXN0X25vIjoiMjE4MyJ9", new LiquidPayCallback<ResponseGetStoredValueCardTopupOptions>() {
                @Override
                public void onSuccess(ResponseGetStoredValueCardTopupOptions response) {
                    Log.d(MainActivity.class.getSimpleName(), "getStoredValueCardTopUpOptions:onSuccess : " + response.toString());
                    Toast.makeText(MainActivity.this, response.toString(), LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable throwable, String errorMessage) {
                    Log.d(MainActivity.class.getSimpleName(), "getStoredValueCardTopUpOptions: onError=" + throwable.toString());
                    Toast.makeText(MainActivity.this, errorMessage, LENGTH_SHORT).show();
                }
            });
        }
    }

    private void parseJson() {
        Log.d("MainActivity", "parseJson");

        String responseBody = "[{" +
                " \"type\":\"topup\", " +
                " \"transaction_id\":\"12343435\"," +
                " \"topup_amount\":332.22" +
                " }," +
                " {" +
                "  \"type\":\"payment\"," +
                "  \"payment_src\":\"sohail\"," +
                "  \"payment_amount\":23.33" +
                " }" +
                "]";

        Log.d("MainActivity", "parseJson: responseBody=" + responseBody);
        String responseJson = new String(responseBody); // from the service endpoint

        // which format has the response of the server
        final TypeToken<List<BaseTransaction>> requestListTypeToken = new TypeToken<List<BaseTransaction>>() {
        };

        // adding all different container classes with their flag
        final RuntimeTypeAdapterFactory<BaseTransaction> typeFactory = RuntimeTypeAdapterFactory
                .of(BaseTransaction.class, "type") // Here you specify which is the parent class and what field particularizes the child class.
                .registerSubtype(PaymentTransaction.class, "payment") // if the flag equals the class name, you can skip the second parameter. This is only necessary, when the "type" field does not equal the class name.
                .registerSubtype(TopupTransaction.class, "topup");

        // add the polymorphic specialization
        final Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeFactory).create();

        // do the mapping
        final List<BaseTransaction> deserializedRequestList = gson.fromJson(responseJson, requestListTypeToken.getType());

        for (BaseTransaction bt : deserializedRequestList) {

            Log.d("MainActivity", "bt=" + bt.toString());

        }
    }


    // ################################## VIRTUAL ACCOUNT ############################

    private void testGetAvailableVirtualAccount() {

        int offset = 0;
        int limit = 1;
        LiquidPay.getInstance().getAvailableVirtualAccount(offset, limit, new LiquidPayCallback<ResponseAvailableVirtualAccount>() {
            @Override
            public void onSuccess(ResponseAvailableVirtualAccount response) {
                Log.d(TAG, "testGetAvailableVirtualAccount # onSuccess : available VA : " + response.totalCount());
                Log.d(TAG, "testGetAvailableVirtualAccount # onSuccess : VA's : " + response.items().toString());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();

                if (response.items().size() > 0) {
                    VirtualAccount virtualAccountItem = response.items().get(0);
                    vaId = virtualAccountItem.id();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.e(TAG, "testGetAvailableVirtualAccount#onError :" + errorMessage);
                toastError(throwable);
            }
        });

    }

    private void testGetAddVirtualAccountRequiredInfo() {

        LiquidPay.getInstance().getVirtualAccountAddToWalletRequiredInfo(vaId, new LiquidPayCallback<ResponseRetrieveVirtualAccountRequiredInfo>() {
            @Override
            public void onSuccess(ResponseRetrieveVirtualAccountRequiredInfo response) {
                Log.d(TAG, "testGetAvailableVirtualAccount # onSuccess : required field: " + response.toString());
                Log.d(TAG, "testGetAvailableVirtualAccount # onSuccess : fields : " + response.items().toString());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();

                for (RequiredField info : response.items().fields()) {

                    if ("dob".equalsIgnoreCase(info.fieldName())) {
                        requestedDOB = info.fieldValue();
                    } else if ("gender".equalsIgnoreCase(info.fieldName())) {
                        requestedGender = info.fieldValue();
                    }

                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });
    }

    private void testAddVirtualAccount() {

        //add VA with requested field
        if (requestedGender.isEmpty() && requestedDOB.isEmpty()) {
            Toast.makeText(this, "Need to Get Add Virtual Account Required info first", Toast.LENGTH_SHORT).show();
            return;
        }

        LiquidPay.getInstance().addVirtualAccount(vaId, requestedGender, requestedDOB, new LiquidPayCallback<ResponseAddVirtualAccount>() {
            @Override
            public void onSuccess(ResponseAddVirtualAccount response) {
                Log.d(TAG, "testAddVirtualAccount # onSuccess : addVirtualAccount " + response.succeed());
                Log.d(TAG, "testAddVirtualAccount # onSuccess : virtual account id: " + response.id());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });

    }

    private void testGetAddedVirtualAccount() {

        LiquidPay.getInstance().getAddedVirtualAccount(new LiquidPayCallback<ResponseGetAddedVirtualAccount>() {
            @Override
            public void onSuccess(ResponseGetAddedVirtualAccount response) {
                Log.d(TAG, "testGetAddedVirtualAccount # onSuccess : getAddedVirtualAccount " + response.toString());
                Log.d(TAG, "testGetAddedVirtualAccount # onSuccess : va items: " + response.items().toString());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();

                if (response.items().size() > 0) {
                    vaId = response.items().get(0).id();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });
    }

    private void testDeleteVirtualAccount() {

        LiquidPay.getInstance().deleteVirtualAccount(vaId, new LiquidPayCallback<ResponseDeleteVirtualAccount>() {
            @Override
            public void onSuccess(ResponseDeleteVirtualAccount response) {
                Log.d(TAG, "testDeleteVirtualAccount # onSuccess : deleteVirtualAccount " + response.succeed());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });

    }

    private void testLinkVirtualAccount() {

        String virtualAccountId = "source_eyJzb3VyY2VfdHlwZSI6InZpcnR1YWxhY2NvdW50IiwiaXNzdWVyIjoiTUFZQkFOSyIsInZhX3R5cGUiOiJWQSIsImN1c3Rfbm8iOiIxOTY3In0=";
        String paymentCardId = "source_eyJzb3VyY2VfdHlwZSI6ImNyZWRpdGNhcmQiLCJjYXJkX2d1aWQiOiJlZWU3ZTUwZi1mNzMyLTQzYWEtYTVjZS05ZTQ0ZTE5NGExOWQiLCJjdXN0X25vIjoiMTk2NyJ9";

        LiquidPay.getInstance().linkCreditOrDebitCardToVirtualAccount(virtualAccountId, paymentCardId, new LiquidPayCallback<ResponseLinkCardToVirtualAccount>() {
            @Override
            public void onSuccess(ResponseLinkCardToVirtualAccount response) {
                Log.d(TAG, "testLinkVirtualAccount # onSuccess : linkCreditOrDebitCardToVirtualAccount " + response.succeed());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });
    }

    private void testUnLinkVirtualAccount() {

        String virtualAccountId = "source_eyJzb3VyY2VfdHlwZSI6InZpcnR1YWxhY2NvdW50IiwiaXNzdWVyIjoiTUFZQkFOSyIsInZhX3R5cGUiOiJWQSIsImN1c3Rfbm8iOiIxOTY3In0=";
        String paymentCardId = "source_eyJzb3VyY2VfdHlwZSI6ImNyZWRpdGNhcmQiLCJjYXJkX2d1aWQiOiJlZWU3ZTUwZi1mNzMyLTQzYWEtYTVjZS05ZTQ0ZTE5NGExOWQiLCJjdXN0X25vIjoiMTk2NyJ9";

        LiquidPay.getInstance().unLinkCreditOrDebitCardFromVirtualAccount(virtualAccountId, paymentCardId, new LiquidPayCallback<ResponseUnLinkCardFromVirtualAccount>() {
            @Override
            public void onSuccess(ResponseUnLinkCardFromVirtualAccount response) {
                Log.d(TAG, "testUnLinkVirtualAccount # onSuccess : unLinkCreditOrDebitCardFromVirtualAccount " + response.succeed());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });
    }

    private void testPayUsingVirtualAccount() {

        LiquidPay.getInstance().payUsingVirtualAccount(paymentId, paymentOptionVirtualAccId, new LiquidPayCallback<ResponsePayment>() {
            @Override
            public void onSuccess(ResponsePayment response) {
                Log.d(TAG, "testPayUsingVirtualAccount # onSuccess : payUsingVirtualAccount " + response.paymentStatus());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.e(TAG, "testPayUsingVirtualAccount # " + errorMessage);
                toastError(throwable);
            }
        });
    }

    private void getPaymentCardsLinkToVirtualAccount(){
        LiquidPay.getInstance().getPaymentCardsLinkToVirtualAccount(vaId, new LiquidPayCallback<ResponseVirtualAccountPaymentCards>() {
            @Override
            public void onSuccess(ResponseVirtualAccountPaymentCards response) {
                Log.d(TAG, "getPaymentCardsLinkToVirtualAccount # type " + response.type());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });
    }

    /******************************* MEMBERSHIP CARD **********************************/

    private void retrievePaymentListEligibleMembershipCard() {
        String currencyCode = "SGD";
        double amount = 2.5;
        int offset = 0;
        int limit = 1;
        payee = "merchant_eyJvdXRsZXRfaWQiOiJnYWxheGlzMW5vcnRoIiwibG9jX25vIjoiIiwibWVyY2hhbnRfaWQiOiJzcGluZWxsaWNvZmZlZSJ9";

        LiquidPay.getInstance().retrievePaymentListEligibleMembershipCard(
                paymentId,
                payee,
                currencyCode,
                amount,
                offset,
                limit,
                new LiquidPayCallback<ResponsePaymentListEligibleMembershipCard>() {
                    @Override
                    public void onSuccess(ResponsePaymentListEligibleMembershipCard response) {
                        Log.d(TAG, "retrievePaymentListEligibleMembershipCard : url " + response.url());
                        Log.d(TAG, "retrievePaymentListEligibleMembershipCard : hasMore " + response.hasMore());
                        Log.d(TAG, "retrievePaymentListEligibleMembershipCard : total " + response.items().size());
                        Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable throwable, String errorMessage) {
                        toastError(throwable);
                    }
                }
        );
    }

    private void testGetAvailableMembershipCard() {

        int offset = 0;
        int limit = 3;

        LiquidPay.getInstance().getAvailableMembershipCard(offset, limit, new LiquidPayCallback<ResponseAvailableMembershipCard>() {
            @Override
            public void onSuccess(ResponseAvailableMembershipCard response) {
                Log.d(TAG, "testGetAvailableMembershipCard # getAvailableMembershipCard : url " + response.url());
                Log.d(TAG, "testGetAvailableMembershipCard # getAvailableMembershipCard : hasMore" + response.hasMore());
                Log.d(TAG, "testGetAvailableMembershipCard # getAvailableMembershipCard : total" + response.items().size());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();

                if (response.items().size() > 0) {
                    AvailableMembershipCard membershipCard = response.items().get(0);
                    membershipId = membershipCard.id();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });

    }

    private void testGetMembershipcardRequiredInfo() {

        LiquidPay.getInstance().getMembershipCardRequiredInfo(membershipId, new LiquidPayCallback<ResponseRetrieveMembershipCardRequiredInfo>() {
            @Override
            public void onSuccess(ResponseRetrieveMembershipCardRequiredInfo response) {
                Log.d(TAG, "testGetMembershipcardRequiredInfo # getMembershipCardRequiredInfo :" + response.toString());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();

                for (RequiredField info : response.items().fields()) {

                    if ("dob".equalsIgnoreCase(info.fieldName())) {
                        requestedDOB = info.fieldValue();
                    } else if ("gender".equalsIgnoreCase(info.fieldName())) {
                        requestedGender = info.fieldValue();
                    }

                }

            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });

    }

    private void testAddMembershipCard() {

        LiquidPay.getInstance().addMembershipCard(membershipId, requestedGender, requestedDOB, new LiquidPayCallback<ResponseAddMembershipCard>() {
            @Override
            public void onSuccess(ResponseAddMembershipCard response) {
                Log.d(TAG, "testAddMembershipCard # addMembershipCard :" + response.toString());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });
    }

    private void testGetAddedMembershipCard() {

        int offset = 0;
        int limit = 5;

        LiquidPay.getInstance().getAddedMembershipCard(offset, limit, new LiquidPayCallback<ResponseGetAddedMembershipCard>() {
            @Override
            public void onSuccess(ResponseGetAddedMembershipCard response) {
                Log.d(TAG, "testAddMembershipCard # addMembershipCard :" + response.toString());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();

                for (MembershipCard membershipCard : response.items()) {
                    Log.d(TAG, "addedMembershipCard# id : " + membershipCard.id());
                    Log.d(TAG, "addedMembershipCard# Name : " + membershipCard.name());
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });

    }

    private void addBrandVoucherToWallet(){
        String id = "item_eyJpdGVtX3R5cGUiOiJ2b3VjaGVyIiwiZGlzY291bnRfaWQiOjM5OSwibWVyY2hhbnRfZ3JvdXBfaWQiOjU0MiwibWVyY2hhbnRfaWQiOiJzcGluZWxsaWNvZmZlZSIsIm91dGxldF9pZCI6InNwaW5vbmVyYWZmbGVzIn0=,item_eyJpdGVtX3R5cGUiOiJ2b3VjaGVyIiwiZGlzY291bnRfaWQiOjQ1NCwibWVyY2hhbnRfZ3JvdXBfaWQiOjU5MywibWVyY2hhbnRfaWQiOiJtZXJjaGFudHRoYW5oIiwib3V0bGV0X2lkIjoiLSJ9";
        LiquidPay.getInstance().addBrandVoucherToWallet(id, new LiquidPayCallback<ResponseWalletAddBrandVoucher>() {
            @Override public void onSuccess(ResponseWalletAddBrandVoucher response) {
                Log.d(TAG, "addBrandVoucherToWallet # type: " + response.type());
                Log.d(TAG, "addBrandVoucherToWallet # id: " + response.id());
                Log.d(TAG, "addBrandVoucherToWallet # succeed: " + response.succeed());
                Log.d(TAG, "addBrandVoucherToWallet: " + response.toString());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();
            }

            @Override public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });
    }

    private void getVoucherListOfWallet() {
        LiquidPay.getInstance().getVoucherListOfWallet(new LiquidPayCallback<ResponseWalletVoucherList>() {
            @Override public void onSuccess(ResponseWalletVoucherList response) {
                Log.d(TAG, "getVoucherListOfWallet # type: " + response.type());
                Log.d(TAG, "getVoucherListOfWallet # url: " + response.url());
                Log.d(TAG, "getVoucherListOfWallet: " + response.toString());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();
            }

            @Override public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });
    }

    private void saveVoucherFromGiftboxToWallet() {
        String id = "discount_eyJkaXNjb3VudF90eXBlIjoidm91Y2hlciIsInZvdWNoZXJfaWQiOiIwODEyNjIyM2M5MGQ0NTNiODllYWEzMTEwNDE5NDFhNCJ9";
        LiquidPay.getInstance().saveVoucherFromGiftboxToWallet(id, new LiquidPayCallback<ResponseWalletSaveVoucher>() {
                    @Override public void onSuccess(ResponseWalletSaveVoucher response) {
                        Log.d(TAG, "saveVoucherFromGiftboxToWallet # type: " + response.type());
                        Log.d(TAG, "saveVoucherFromGiftboxToWallet # id: " + response.id());
                        Log.d(TAG, "saveVoucherFromGiftboxToWallet # succeed: " + response.succeed());
                        Log.d(TAG, "saveVoucherFromGiftboxToWallet: " + response.toString());
                        Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();
                    }

                    @Override public void onError(Throwable throwable, String errorMessage) {
                        toastError(throwable);
                    }
                });
    }

    private void sendVoucherToFriend() {
        String id = "discount_eyJkaXNjb3VudF90eXBlIjoidm91Y2hlciIsInZvdWNoZXJfaWQiOiIwODEyNjIyM2M5MGQ0NTNiODllYWEzMTEwNDE5NDFhNCJ9";
        String mobile = "+6583038705";

        LiquidPay.getInstance().sendVoucherToFriend(id, mobile, new LiquidPayCallback<ResponseVoucherSendToFriend>() {
            @Override public void onSuccess(ResponseVoucherSendToFriend response) {
                Log.d(TAG, "sendVoucherToFriend # type: " + response.type());
                Log.d(TAG, "sendVoucherToFriend # id: " + response.id());
                Log.d(TAG, "sendVoucherToFriend # succeed: " + response.succeed());
                Log.d(TAG, "sendVoucherToFriend: " + response.toString());
                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();
            }

            @Override public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });
    }

    private void getVoucherGiftBoxListing() {
        LiquidPay.getInstance().getVoucherGiftBoxListing(new LiquidPayCallback<ResponseVoucherGiftBox>() {
            @Override public void onSuccess(ResponseVoucherGiftBox response) {
                Log.d(TAG, "getVoucherGiftBoxListing # response: " + response.toString());
                Log.d(TAG, "getVoucherGiftBoxListing # type: " + response.type());
                Log.d(TAG, "getVoucherGiftBoxListing # url: " + response.url());

                Toast.makeText(getApplicationContext(), response.toString(), LENGTH_SHORT).show();
            }

            @Override public void onError(Throwable throwable, String errorMessage) {
                toastError(throwable);
            }
        });
    }


    private void toastError(Throwable t) {

        if (t instanceof LiquidPayException) {
            LiquidPayException e = (LiquidPayException) t;
            StringBuilder sb = new StringBuilder();
            for (Error err : e.getErrorList()) {
                sb.append(err.toString());
                sb.append("\n");
            }
            Toast.makeText(getApplicationContext(), "" + sb, LENGTH_SHORT).show();
        }

    }
}
