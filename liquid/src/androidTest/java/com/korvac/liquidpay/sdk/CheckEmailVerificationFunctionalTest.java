package com.korvac.liquidpay.sdk;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.korvac.liquidpay.sdk.constant.APIConfig;
import com.korvac.liquidpay.sdk.data.response.ResponseCheckEmailVerification;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.main.LiquidPay;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Thanh Chuong on 25/5/17.
 */

public class CheckEmailVerificationFunctionalTest {

    final Object syncObject = new Object();
    static String refreshToken = null;

    @Before
    public void setup(){
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().init(context, APIConfig.API_KEY,APIConfig.SECRET_KEY,null);
    }


    @Test
    public void checkEmailVerificationSuccessTest() throws InterruptedException {
        String email = "peter.at.liquid@yopmail.com";
        LiquidPay.getInstance().loginWithPassword(email, "Pass12345", new LiquidPayCallback<ResponseLogin>() {
            @Override
            public void onSuccess(ResponseLogin response) {
                refreshToken = response.refreshToken();
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

        LiquidPay.getInstance().checkEmailVerificationStatus(new LiquidPayCallback<ResponseCheckEmailVerification>() {
            @Override
            public void onSuccess(ResponseCheckEmailVerification response) {

                assertThat(response.IsEmailVerified(),equalTo(true));
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

    @Test(expected = IllegalStateException.class)
    public void testCheckEmailVerificationWithoutInit() throws InterruptedException {
        LiquidPay.getInstance().isInitialized=false;
        LiquidPay.getInstance().checkEmailVerificationStatus(new LiquidPayCallback<ResponseCheckEmailVerification>() {
            @Override
            public void onSuccess(ResponseCheckEmailVerification response) {
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
    public void testCheckEmailVerificationWithNullArguments() {
        Context context= InstrumentationRegistry.getContext();
        LiquidPay.getInstance().checkEmailVerificationStatus(null);
    }
}
