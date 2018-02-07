package com.korvac.liquidpay.sdk;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;

import com.korvac.liquidpay.sdk.constant.APIConfig;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.data.response.ResponseSendOTP;
import com.korvac.liquidpay.sdk.data.response.ResponseSendVerifyEmail;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.main.LiquidPay;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.util.TestUtil;

import org.junit.Before;
import org.junit.Test;

import static com.korvac.liquidpay.sdk.TestData.EMAIL_FOR_CREATE_PAYMENT;
import static com.korvac.liquidpay.sdk.TestData.PASSWORD;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Thanh Chuong on 25/5/17.
 */

public class SendEmailVerificationTest {

    final Object syncObject = new Object();
    static boolean isLoggedIn = false;

    @Before
    public void setup(){
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().init(context, APIConfig.API_KEY,APIConfig.SECRET_KEY,null);
    }


    @Test
    public void sendEmailVerificationSuccessTest() throws InterruptedException {

        String email = "android_test_1498029424751@mailinator.com";
        LiquidPay.getInstance().loginWithPassword(email, PASSWORD, new LiquidPayCallback<ResponseLogin>() {
            @Override
            public void onSuccess(ResponseLogin response) {
                isLoggedIn = true;
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                fail("Failed to loginWithPassword");
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }
        });

        while (true) {
            if (isLoggedIn) {
                isLoggedIn = false;
                break;
            }
            SystemClock.sleep(1000);
        }

        LiquidPay.getInstance().resendEmailVerification(email, false, new LiquidPayCallback<ResponseSendVerifyEmail>() {
            @Override
            public void onSuccess(ResponseSendVerifyEmail response) {
                assertTrue(response.acceptRequest());
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
    public void sendEmailVerificationFailedTest() throws InterruptedException {
        String email = "thanhmvp32@mailinator.com";
        LiquidPay.getInstance().loginWithPassword(email, PASSWORD, new LiquidPayCallback<ResponseLogin>() {
            @Override
            public void onSuccess(ResponseLogin response) {
                isLoggedIn = true;
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                fail("Failed to loginWithPassword");
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }
        });

        while (true) {
            if (isLoggedIn) {
                isLoggedIn = false;
                break;
            }
            SystemClock.sleep(1000);
        }

        LiquidPay.getInstance().resendEmailVerification(email, true, new LiquidPayCallback<ResponseSendVerifyEmail>() {
            @Override
            public void onSuccess(ResponseSendVerifyEmail response) {
                fail();
                synchronized (syncObject){
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                LiquidPayException exception = (LiquidPayException) throwable;
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
    public void testSendEmailVerificationWithoutInit() throws InterruptedException {
        LiquidPay.getInstance().isInitialized=false;
        LiquidPay.getInstance().resendEmailVerification("abc@gmail.com", true, new LiquidPayCallback<ResponseSendVerifyEmail>() {
            @Override
            public void onSuccess(ResponseSendVerifyEmail response) {
                fail();
                synchronized (syncObject){
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                fail();
                synchronized (syncObject){
                    syncObject.notify();
                }
            }
        });

        synchronized (syncObject){
            syncObject.wait();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSendEmailVerificationWithNullArguments() {
        Context context= InstrumentationRegistry.getContext();
        LiquidPay.getInstance().resendEmailVerification(null,true,null);
    }
}
