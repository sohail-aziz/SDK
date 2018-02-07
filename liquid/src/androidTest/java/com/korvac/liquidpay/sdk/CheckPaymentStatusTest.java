package com.korvac.liquidpay.sdk;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;

import com.korvac.liquidpay.sdk.constant.APIConfig;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.data.response.ResponsePayment;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.main.LiquidPay;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.enums.PaymentStatus;

import org.junit.Before;
import org.junit.Test;

import static com.korvac.liquidpay.sdk.TestData.EMAIL_FOR_CREATE_PAYMENT;
import static com.korvac.liquidpay.sdk.TestData.MERCHANT_REF_NO;
import static com.korvac.liquidpay.sdk.TestData.PASSWORD;
import static com.korvac.liquidpay.sdk.TestData.PAYEE;
import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Thanh Chuong on 15/06/17.
 */

public class CheckPaymentStatusTest {

    final Object syncObject = new Object();

    static boolean isLoggedIn = false;
    static boolean gotPaymentID = false;

    static String paymentID;

    @Before
    public void setup() {
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().init(context, APIConfig.API_KEY, APIConfig.SECRET_KEY, null);
    }


    @Test
    public void checkPaymentStatusSuccessTest() throws InterruptedException {
        final String amount = "1.00";

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
                fail("Failed to login");
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

        LiquidPay.getInstance().createPayment(PAYEE, "SP", MERCHANT_REF_NO, amount, new LiquidPayCallback<ResponsePayment>() {
            @Override
            public void onSuccess(ResponsePayment response) {
                paymentID = response.id();
                gotPaymentID = true;
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                fail("Fail to create payment");
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }
        });

        while (true) {
            if (gotPaymentID) {
                gotPaymentID = false;
                break;
            }
            SystemClock.sleep(1000);
        }


        LiquidPay.getInstance().checkPaymentStatus(paymentID, new LiquidPayCallback<ResponsePayment>() {
            @Override
            public void onSuccess(ResponsePayment response) {
                assertThat(response.paymentStatus(), equalTo(PaymentStatus.WAITING));
                assertThat(response.originalAmount(), equalTo(Float.parseFloat(amount)));
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
    public void checkPaymentFailedTest() throws InterruptedException {
        String invalidPaymentId = "payment_eyJwYXltZW50X2lkIjoiMjAxNzA2MfsdfDUxMzMwMTUwOTc3ODQ0OCJ9";
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
                fail("Failed to login");
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


        LiquidPay.getInstance().checkPaymentStatus(invalidPaymentId, new LiquidPayCallback<ResponsePayment>() {
            @Override
            public void onSuccess(ResponsePayment response) {
                fail();
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                LiquidPayException exception = (LiquidPayException) throwable;
                assertThat(exception.getErrorList().get(0).getMessage(), equalTo("Payment ID is not valid"));
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
    public void testCheckPaymentStatusWithoutInit() throws InterruptedException {
        LiquidPay.getInstance().isInitialized = false;
        LiquidPay.getInstance().checkPaymentStatus("", new LiquidPayCallback<ResponsePayment>() {
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
    public void testCheckPaymentStatusWithNullArguments() {
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().checkPaymentStatus(null, null);
    }
}
