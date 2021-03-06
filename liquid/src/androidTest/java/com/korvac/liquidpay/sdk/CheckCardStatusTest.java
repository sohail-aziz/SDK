package com.korvac.liquidpay.sdk;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;

import com.korvac.liquidpay.sdk.constant.APIConfig;
import com.korvac.liquidpay.sdk.data.response.ResponseCheckCardStatus;
import com.korvac.liquidpay.sdk.data.response.ResponseGetPaymentOptions;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.data.response.ResponsePayment;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.main.LiquidPay;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.enums.CardStatus;

import org.junit.Before;
import org.junit.Test;

import static com.korvac.liquidpay.sdk.TestData.EMAIL_FOR_CREATE_PAYMENT;
import static com.korvac.liquidpay.sdk.TestData.MERCHANT_REF_NO;
import static com.korvac.liquidpay.sdk.TestData.PASSWORD;
import static com.korvac.liquidpay.sdk.TestData.PAYEE;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Thanh Chuong on 15/06/17.
 */

public class CheckCardStatusTest {

    final Object syncObject = new Object();

    static boolean isLoggedIn = false;
    static boolean gotPaymentID = false;
    static boolean gotCardID = false;

    static String paymentID;
    static String cardID;

    @Before
    public void setup(){
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().init(context, APIConfig.API_KEY,APIConfig.SECRET_KEY,null);
    }


    @Test
    public void checkCardStatusSuccessTest() throws InterruptedException {

        LiquidPay.getInstance().loginWithPassword(EMAIL_FOR_CREATE_PAYMENT, PASSWORD, new LiquidPayCallback<ResponseLogin>() {
            @Override
            public void onSuccess(ResponseLogin response) {
                isLoggedIn = true;
                synchronized (syncObject){
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                fail("Failed to loginWithPassword");
                synchronized (syncObject){
                    syncObject.notify();
                }
            }
        });

        while (true){
            if(isLoggedIn){
                isLoggedIn =false;
                break;
            }
            SystemClock.sleep(1000);
        }

        LiquidPay.getInstance().createPayment(PAYEE, "SP", MERCHANT_REF_NO, "1.00", new LiquidPayCallback<ResponsePayment>() {
            @Override
            public void onSuccess(ResponsePayment response) {
                gotPaymentID=true;
                paymentID=response.id();
                synchronized (syncObject){
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                synchronized (syncObject){
                    syncObject.notify();
                }
            }
        });

        while (true){
            if(gotPaymentID){
                gotPaymentID =false;
                break;
            }
            SystemClock.sleep(1000);
        }

        LiquidPay.getInstance().getPaymentOptions(paymentID, new LiquidPayCallback<ResponseGetPaymentOptions>() {
            @Override
            public void onSuccess(ResponseGetPaymentOptions response) {
                cardID = response.items().listCreditOrDebitCard().get(0).id();
                gotCardID = true;
                synchronized (syncObject){
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                LiquidPayException exception = (LiquidPayException)throwable;
                fail(exception.getErrorList().get(0).getMessage());
                synchronized (syncObject){
                    syncObject.notify();
                }
            }
        });

        while (true){
            if(gotCardID){
                gotCardID =false;
                break;
            }
            SystemClock.sleep(1000);
        }

        LiquidPay.getInstance().checkPaymentCardStatus(cardID, new LiquidPayCallback<ResponseCheckCardStatus>() {
            @Override
            public void onSuccess(ResponseCheckCardStatus response) {
                assertThat(response.status(), equalTo(CardStatus.ACTIVE));
                synchronized (syncObject){
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                LiquidPayException exception = (LiquidPayException)throwable;
                fail(exception.getErrorList().get(0).getMessage());
                synchronized (syncObject){
                    syncObject.notify();
                }
            }
        });


        synchronized (syncObject){
            syncObject.wait();
        }
    }

    @Test
    public void checkCardFailedTest() throws InterruptedException {
        String invalidCardId = "source_eyJzb3VyY2VfwZSI6ImNyZWRpdGNhcmQiLCJjYXJkX2d1aWQiOiJkMDEwNGY3My04NmFlLTQzYTktOTU3NC1hNGVmMjA1YWNiM2IiLCJjdXN0X25vIjoiMjIzMiJ9";
        LiquidPay.getInstance().loginWithPassword(EMAIL_FOR_CREATE_PAYMENT, PASSWORD, new LiquidPayCallback<ResponseLogin>() {
            @Override
            public void onSuccess(ResponseLogin response) {
                isLoggedIn = true;
                synchronized (syncObject){
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                fail("Failed to loginWithPassword");
                synchronized (syncObject){
                    syncObject.notify();
                }
            }
        });

        while (true){
            if(isLoggedIn){
                isLoggedIn =false;
                break;
            }
            SystemClock.sleep(1000);
        }

        LiquidPay.getInstance().checkPaymentCardStatus(invalidCardId, new LiquidPayCallback<ResponseCheckCardStatus>() {
            @Override
            public void onSuccess(ResponseCheckCardStatus response) {
                fail();
                synchronized (syncObject){
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                LiquidPayException exception = (LiquidPayException)throwable;
                assertTrue(!exception.getErrorList().get(0).getMessage().isEmpty());
                synchronized (syncObject){
                    syncObject.notify();
                }
            }
        });

        synchronized (syncObject){
            syncObject.wait();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testCheckCardWithoutInit() throws InterruptedException {
        LiquidPay.getInstance().isInitialized=false;
        LiquidPay.getInstance().checkPaymentCardStatus("c", new LiquidPayCallback<ResponseCheckCardStatus>() {
            @Override
            public void onSuccess(ResponseCheckCardStatus response) {
                synchronized (syncObject){
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                synchronized (syncObject){
                    syncObject.notify();
                }
            }
        });

        synchronized (syncObject){
            syncObject.wait();
        }
    }

    @Test(expected = NullPointerException.class)
    public void testSendOTPWithNullArguments() {
        Context context= InstrumentationRegistry.getContext();
        LiquidPay.getInstance().checkPaymentCardStatus(null,null);
    }
}
