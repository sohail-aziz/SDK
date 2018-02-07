package com.korvac.liquidpay.sdk;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;

import com.korvac.liquidpay.sdk.constant.APIConfig;
import com.korvac.liquidpay.sdk.data.response.ResponseGetPaymentOptions;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.data.response.ResponsePayment;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.main.LiquidPay;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;

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

public class PayUsingStoreValueCardTest {

    final Object syncObject = new Object();

    static boolean isLoggedIn = false;
    static boolean gotPaymentID = false;
    static boolean gotSVCID = false;

    static String paymentID;
    static String svcID;

    @Before
    public void setup(){
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().init(context, APIConfig.API_KEY,APIConfig.SECRET_KEY,null);
    }


    @Test
    public void payUsingSVCSuccessTest() throws InterruptedException {
        String amount = "1.00";

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

        LiquidPay.getInstance().createPayment(PAYEE, "SP", MERCHANT_REF_NO, amount, new LiquidPayCallback<ResponsePayment>() {
            @Override
            public void onSuccess(ResponsePayment response) {
                paymentID = response.id();
                gotPaymentID = true;
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
                gotPaymentID=false;
                break;
            }
            SystemClock.sleep(1000);
        }

        LiquidPay.getInstance().getPaymentOptions(paymentID, new LiquidPayCallback<ResponseGetPaymentOptions>() {
            @Override
            public void onSuccess(ResponseGetPaymentOptions response) {
                svcID = response.items().listStoredValueCard().get(0).id();
                gotSVCID = true;
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
            if(gotSVCID){
                gotSVCID =false;
                break;
            }
            SystemClock.sleep(1000);
        }

        LiquidPay.getInstance().payUsingStoreValueCard(paymentID, svcID, new LiquidPayCallback<ResponsePayment>() {
            @Override
            public void onSuccess(ResponsePayment response) {
                assertThat(response.originalAmount(),equalTo(1.0f));
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
    public void createPaymentFailedTest() throws InterruptedException {
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

        LiquidPay.getInstance().payUsingStoreValueCard("d", "d", new LiquidPayCallback<ResponsePayment>() {
            @Override
            public void onSuccess(ResponsePayment response) {
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
    public void testPayUsingSVCWithoutInit() throws InterruptedException {
        LiquidPay.getInstance().isInitialized=false;
        LiquidPay.getInstance().payUsingStoreValueCard("", "", new LiquidPayCallback<ResponsePayment>() {
            @Override
            public void onSuccess(ResponsePayment response) {
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
    public void testPayUsingSVCWithNullArguments() {
        Context context= InstrumentationRegistry.getContext();
        LiquidPay.getInstance().payUsingStoreValueCard(null,null,null);
    }
}
