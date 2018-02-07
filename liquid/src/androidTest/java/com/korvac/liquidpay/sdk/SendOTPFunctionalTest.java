package com.korvac.liquidpay.sdk;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.korvac.liquidpay.sdk.constant.APIConfig;
import com.korvac.liquidpay.sdk.data.response.ResponseSendOTP;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.main.LiquidPay;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.util.TestUtil;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Thanh Chuong on 25/5/17.
 */

public class SendOTPFunctionalTest {

    final Object syncObject = new Object();
    static String refreshToken = null;

    @Before
    public void setup(){
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().init(context, APIConfig.API_KEY,APIConfig.SECRET_KEY,null);
    }


    @Test
    public void sendOTPSuccessTest() throws InterruptedException {

        String number = TestUtil.generateRandomNumber();
        LiquidPay.getInstance().getOTPForRegistration("65", number, new LiquidPayCallback<ResponseSendOTP>() {
            @Override
            public void onSuccess(ResponseSendOTP response) {
                assertThat(response.allowResend(),equalTo(true));
                assertThat(response.type(),equalTo("otp"));
                assertThat(response.resendInterval(),equalTo(30.0));
                assertThat(response.expiry(),equalTo(180.0));
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

    @Test
    public void sendRequestOTPToRegisteredNumberTest() throws InterruptedException {
        String number = "82813156";
        LiquidPay.getInstance().getOTPForRegistration("65", number, new LiquidPayCallback<ResponseSendOTP>() {
            @Override
            public void onSuccess(ResponseSendOTP response) {
                fail();
                synchronized (syncObject){
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                LiquidPayException exception = (LiquidPayException)throwable;
                assertThat(exception.getErrorList().get(0).getMessage()
                        ,equalTo("You might have registered this mobile number in another Liquid account. Please enter a different mobile number."));
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
    public void testSendOTPWithoutInit() throws InterruptedException {
        LiquidPay.getInstance().isInitialized=false;
        LiquidPay.getInstance().getOTPForRegistration("65", "82813156", new LiquidPayCallback<ResponseSendOTP>() {
            @Override
            public void onSuccess(ResponseSendOTP response) {
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

    @Test(expected = IllegalArgumentException.class)
    public void testSendOTPWithNullArguments() {
        Context context= InstrumentationRegistry.getContext();
        LiquidPay.getInstance().getOTPForRegistration(null,null,null);
    }
}
