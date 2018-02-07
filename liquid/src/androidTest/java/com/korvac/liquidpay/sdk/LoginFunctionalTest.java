package com.korvac.liquidpay.sdk;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.korvac.liquidpay.sdk.constant.APIConfig;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.main.LiquidPay;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by Thanh Chuong on 23/5/17.
 */


public class LoginFunctionalTest {

    final Object syncObject = new Object();

    @Before
    public void setup(){
        LiquidPay.getInstance().init(InstrumentationRegistry.getContext(), APIConfig.API_KEY,APIConfig.SECRET_KEY,null);
    }

    @Test
    public void testLoginSuccess() throws InterruptedException {
//        LiquidPay.getInstance().
        LiquidPay.getInstance().loginWithPassword("thanhmvp34@mailinator.com", "Pass12345", new LiquidPayCallback<ResponseLogin>() {
            @Override
            public void onSuccess(ResponseLogin response) {
                assertThat(response.accessToken(),notNullValue());
                assertThat(response.refreshToken(),notNullValue());
                assertThat(response.type(),equalTo("auth"));
                assertThat(response.subjectType(),equalTo("consumer"));
                assertThat(response.expiresIn(),equalTo(300000L));
                assertThat(response.tokenType(),equalTo("bearer"));
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
    public void testLoginFailure() throws InterruptedException{
        LiquidPay.getInstance().loginWithPassword("chuongtuthanh@yopmail.com", "wrongpass", new LiquidPayCallback<ResponseLogin>() {
            @Override
            public void onSuccess(ResponseLogin response) {
                fail();
                synchronized (syncObject){
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                assertThat(errorMessage, equalTo("We do not recognize your credentials, please re-enter." +
                        " Please contact contact@liquidpay.com if the problem persists."));
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
    public void testLoginWithoutInit() throws InterruptedException {
        final Object syncObject = new Object();
        LiquidPay.getInstance().isInitialized=false;

        LiquidPay.getInstance().loginWithPassword("username", "", new LiquidPayCallback<ResponseLogin>() {
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

    @Test(expected = NullPointerException.class)
    public void testLoginWithNullArguments() {

        Context context= InstrumentationRegistry.getContext();
        LiquidPay.getInstance().init(context, APIConfig.API_KEY,APIConfig.SECRET_KEY,null);

        LiquidPay.getInstance().loginWithPassword(null,null,null);
    }
}
