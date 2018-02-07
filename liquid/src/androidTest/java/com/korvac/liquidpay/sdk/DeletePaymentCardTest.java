package com.korvac.liquidpay.sdk;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;

import com.korvac.liquidpay.sdk.constant.APIConfig;
import com.korvac.liquidpay.sdk.data.response.BaseCard;
import com.korvac.liquidpay.sdk.data.response.Card3D;
import com.korvac.liquidpay.sdk.data.response.CardNon3D;
import com.korvac.liquidpay.sdk.data.response.ResponseDeleteCard;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.main.LiquidPay;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.enums.CardStatus;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static com.korvac.liquidpay.sdk.TestData.EMAIL_FOR_CREATE_PAYMENT;
import static com.korvac.liquidpay.sdk.TestData.PASSWORD;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Thanh Chuong on 12/09/17.
 */

public class DeletePaymentCardTest {

    final Object syncObject = new Object();

    static boolean isLoggedIn = false;

    @Before
    public void setup() {
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().init(context, APIConfig.API_KEY, APIConfig.SECRET_KEY, null);
    }


    @Test
    public void deletePaymentCardSuccessTest() throws InterruptedException, ParseException {

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

        String cardId = "source_eyJzb3VyY2VfdHlwZSI6ImNyZWRpdGNhcmQiLCJjYXJkX2d1aWQiOiIxN2U1MTA5My0zMThjLTRhODEtOWFmYS0xZjVkZjhjZjE4NjEiLCJjdXN0X25vIjoiMjIzMiJ9";
        LiquidPay.getInstance().deletePaymentCard(cardId, new LiquidPayCallback<ResponseDeleteCard>() {
            @Override
            public void onSuccess(ResponseDeleteCard response) {
                assertFalse(response.id().isEmpty());
                assertTrue(response.isSucceeded());
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                LiquidPayException exception = (LiquidPayException)throwable;
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
    public void deletePaymentCardFailTest() throws InterruptedException, ParseException {

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

        String cardId = "source_eyJzb3VyY2VfdHlwZSI6ImNyZWRpdGNhcmQiLCJjYXJkX2d1aWQiOiIxN2U1MTA5My0zMThjLTRhODEtOWFmYS0xZjVkZjhjZjE4NjEiLCJjdXN0X25vIjoiMjIzMiJ9";
        LiquidPay.getInstance().deletePaymentCard(cardId, new LiquidPayCallback<ResponseDeleteCard>() {
            @Override
            public void onSuccess(ResponseDeleteCard response) {
                fail();
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                LiquidPayException exception = (LiquidPayException)throwable;
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


    @Test(expected = NullPointerException.class)
    public void testDeleteCardWithNullArguments() {
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().deletePaymentCard(null, null);
    }
}
