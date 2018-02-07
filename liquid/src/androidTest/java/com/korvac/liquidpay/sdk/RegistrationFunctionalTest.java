package com.korvac.liquidpay.sdk;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.korvac.liquidpay.sdk.constant.APIConfig;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.data.response.ResponseSendOTP;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.main.LiquidPay;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.util.TestUtil;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Thanh Chuong on 31/5/17.
 */

public class RegistrationFunctionalTest {

    final Object syncObject = new Object();

    @Before
    public void setup(){
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().init(context, APIConfig.API_KEY,APIConfig.SECRET_KEY,null);
    }

    @Test()
    public void testRegistrationWithValidInfo() throws InterruptedException {
        String email = "android_test_"+ System.currentTimeMillis()+"@mailinator.com";
//        String email = "thanhmvp40@mailinator.com";
        String dialingCode = "65";
        String mobile = TestUtil.generateRandomNumber();

        LiquidPay.getInstance().getOTPForRegistration(dialingCode, mobile, new LiquidPayCallback<ResponseSendOTP>() {
            @Override
            public void onSuccess(ResponseSendOTP response) {
                assertThat(response.allowResend(),equalTo(true));
                assertThat(response.expiry(),equalTo(180.0));
                assertThat(response.resendInterval(),equalTo(30.0));
                assertThat(response.type(),equalTo("otp"));
                synchronized (syncObject){
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                fail(errorMessage);
                synchronized (syncObject){
                    syncObject.notify();
                }
            }
        });

        Thread.sleep(3000);

        LiquidPay.getInstance().submitRegistrationDetails(
                email,dialingCode,mobile,"000000","android","test","pass1234","",null,new LiquidPayCallback<ResponseLogin>() {
                    @Override
                    public void onSuccess(ResponseLogin response) {
                        assertThat(response.accessToken(),notNullValue());
                        assertThat(response.refreshToken(),notNullValue());
                        assertThat(response.type(),equalTo("consumer"));
                        assertThat(response.subjectType(),equalTo("consumer"));
                        assertThat(response.expiresIn(),equalTo(900L));
                        assertThat(response.tokenType(),equalTo("bearer"));
                        synchronized (syncObject){
                            syncObject.notify();
                        }
                    }

                    @Override
                    public void onError(Throwable throwable, String errorMessage) {
                        fail(errorMessage);
                        synchronized (syncObject){
                            assertThat(errorMessage, equalTo("Invalid email format"));
                            syncObject.notify();
                        }
                    }
                });

        synchronized (syncObject){
            syncObject.wait();
        }
    }

    @Test()
    public void testRegistrationWithInvalidEmail() throws InterruptedException {
        LiquidPay.getInstance().submitRegistrationDetails(
                "abc@liquidpay.com", "65","87876765","","first name","last name","pass",
                "","promo code",new LiquidPayCallback<ResponseLogin>() {
                    @Override
                    public void onSuccess(ResponseLogin response) {
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
    public void testRegistrationWithoutInit() throws InterruptedException {
        LiquidPay.getInstance().isInitialized=false;
        LiquidPay.getInstance().submitRegistrationDetails(
                "abc@liquidpay.com", "","65","87876765","first name","last name","pass",
                "source","promo code",new LiquidPayCallback<ResponseLogin>() {
            @Override
            public void onSuccess(ResponseLogin response) {
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
    public void testRefreshTokenWithNullArguments() {

        Context context= InstrumentationRegistry.getContext();
        LiquidPay.getInstance().submitRegistrationDetails(
                "abc@liquidpay.com", "anyString","65","87876765","first name","last name","pass",
                "source","promo code",null);
    }


}
