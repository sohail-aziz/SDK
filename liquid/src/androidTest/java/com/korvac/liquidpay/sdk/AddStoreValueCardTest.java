package com.korvac.liquidpay.sdk;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;

import com.korvac.liquidpay.sdk.constant.APIConfig;
import com.korvac.liquidpay.sdk.data.response.ResponseAddStoredValueCard;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.data.response.ResponsePayment;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.main.LiquidPay;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.enums.CardStatus;

import org.junit.Before;
import org.junit.Test;

import static com.korvac.liquidpay.sdk.TestData.EMAIL_FOR_ADD_SVC;
import static com.korvac.liquidpay.sdk.TestData.EMAIL_FOR_CREATE_PAYMENT;
import static com.korvac.liquidpay.sdk.TestData.GETJUICE_SVC_ID;
import static com.korvac.liquidpay.sdk.TestData.MERCHANT_REF_NO;
import static com.korvac.liquidpay.sdk.TestData.PASSWORD;
import static com.korvac.liquidpay.sdk.TestData.PAYEE;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Thanh Chuong on 15/06/17.
 */

public class AddStoreValueCardTest {

    final Object syncObject = new Object();

    static boolean isLoggedIn = false;

    @Before
    public void setup() {
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().init(context, APIConfig.API_KEY, APIConfig.SECRET_KEY, null);
    }

    @Test
    public void addStoreValueCardSuccessTest() throws InterruptedException {

        LiquidPay.getInstance().loginWithPassword(EMAIL_FOR_ADD_SVC, "Pass12345", new LiquidPayCallback<ResponseLogin>() {
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

        LiquidPay.getInstance().addStoreValueCard(GETJUICE_SVC_ID, "S3510986B", "SG", new LiquidPayCallback<ResponseAddStoredValueCard>() {
            @Override
            public void onSuccess(ResponseAddStoredValueCard response) {
                assertFalse(response.autoTopup());
                assertThat(response.status(), equalTo(CardStatus.ACTIVE));
                assertTrue(response.balance().equals(0.0f));
                assertTrue(!response.createdAt().isEmpty());
                assertTrue(!response.updatedAt().isEmpty());
                assertTrue(!response.id().isEmpty());
                assertTrue(!response.name().isEmpty());
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                LiquidPayException exception = (LiquidPayException) throwable;
                fail(exception.getErrorList().get(0).getMessage());
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }
        });

        synchronized (syncObject) {
            syncObject.wait();
        }
    }

    @Test
    public void addStoreValueCardFailedTest() throws InterruptedException {

        LiquidPay.getInstance().loginWithPassword(EMAIL_FOR_CREATE_PAYMENT, PASSWORD, new LiquidPayCallback<ResponseLogin>() {
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

        LiquidPay.getInstance().addStoreValueCard(GETJUICE_SVC_ID, "S0884457G", "SG", new LiquidPayCallback<ResponseAddStoredValueCard>() {
            @Override
            public void onSuccess(ResponseAddStoredValueCard response) {
                fail();
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                LiquidPayException exception = (LiquidPayException) throwable;
                assertTrue(!exception.getErrorList().get(0).getMessage().isEmpty());
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }
        });

        synchronized (syncObject) {
            syncObject.wait();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testCreatePaymentWithoutInit() throws InterruptedException {
        LiquidPay.getInstance().isInitialized = false;
        LiquidPay.getInstance().createPayment("", "", "", "", new LiquidPayCallback<ResponsePayment>() {
            @Override
            public void onSuccess(ResponsePayment response) {
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }
        });

        synchronized (syncObject) {
            syncObject.wait();
        }
    }

    @Test(expected = NullPointerException.class)
    public void testSendOTPWithNullArguments() {
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().createPayment(null, null, null, null, null);
    }
}
