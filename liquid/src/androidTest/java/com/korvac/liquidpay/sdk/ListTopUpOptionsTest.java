package com.korvac.liquidpay.sdk;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;

import com.korvac.liquidpay.sdk.constant.APIConfig;
import com.korvac.liquidpay.sdk.data.response.ResponseGetStoredValueCard;
import com.korvac.liquidpay.sdk.data.response.ResponseGetStoredValueCardTopupOptions;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.main.LiquidPay;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.enums.CardStatus;

import org.junit.Before;
import org.junit.Test;

import static com.korvac.liquidpay.sdk.TestData.EMAIL_FOR_CREATE_PAYMENT;
import static com.korvac.liquidpay.sdk.TestData.PASSWORD;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Thanh Chuong on 16/05/17.
 */

public class ListTopUpOptionsTest {

    final Object syncObject = new Object();
    static boolean isLoggedIn = false;
    static boolean gotRecipientID = false;
    static String recipientID;

    @Before
    public void setup(){
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().init(context, APIConfig.API_KEY,APIConfig.SECRET_KEY,null);
    }


    @Test
    public void listTopUpOptionsSuccessTest() throws InterruptedException {

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

        LiquidPay.getInstance().listUserStoreValueCards(new LiquidPayCallback<ResponseGetStoredValueCard>() {
            @Override
            public void onSuccess(ResponseGetStoredValueCard response) {
                recipientID = response.items().get(0).id();
                gotRecipientID = true;
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
            if(gotRecipientID){
                gotRecipientID=false;
                break;
            }
            SystemClock.sleep(1000);
        }

        LiquidPay.getInstance().getStoredValueCardTopUpOptions(recipientID, new LiquidPayCallback<ResponseGetStoredValueCardTopupOptions>() {
            @Override
            public void onSuccess(ResponseGetStoredValueCardTopupOptions response) {
                assertThat(response.items().listCard().get(0).status(),equalTo(CardStatus.ACTIVE));
                assertTrue(!response.items().listCard().get(0).id().isEmpty());
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

    @Test(expected = IllegalStateException.class)
    public void testListTopupOptionsWithoutInit() throws InterruptedException {
        LiquidPay.getInstance().isInitialized=false;
        LiquidPay.getInstance().getStoredValueCardTopUpOptions("", new LiquidPayCallback<ResponseGetStoredValueCardTopupOptions>() {
            @Override
            public void onSuccess(ResponseGetStoredValueCardTopupOptions response) {
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
    public void testListTopUpOptionsWithNullArguments() {
        Context context= InstrumentationRegistry.getContext();
        LiquidPay.getInstance().getStoredValueCardTopUpOptions(null,null);
    }
}
