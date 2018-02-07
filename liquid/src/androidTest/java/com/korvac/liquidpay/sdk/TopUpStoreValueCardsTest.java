package com.korvac.liquidpay.sdk;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;

import com.korvac.liquidpay.sdk.constant.APIConfig;
import com.korvac.liquidpay.sdk.data.response.ResponseGetStoredValueCard;
import com.korvac.liquidpay.sdk.data.response.ResponseGetStoredValueCardTopupOptions;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.data.response.ResponseTopUpStoredValueCard;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.main.LiquidPay;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.enums.TopupStatus;

import org.junit.Before;
import org.junit.Test;

import static com.korvac.liquidpay.sdk.TestData.EMAIL_FOR_CREATE_PAYMENT;
import static com.korvac.liquidpay.sdk.TestData.PASSWORD;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Thanh Chuong on 15/06/17.
 */

public class TopUpStoreValueCardsTest {

    final Object syncObject = new Object();

    static boolean isLoggedIn = false;
    static boolean gotRecipientID = false;
    static boolean gotOriginatorID = false;
    static String recipientID;
    static String originatorID;

    @Before
    public void setup() {
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().init(context, APIConfig.API_KEY, APIConfig.SECRET_KEY, null);
    }


    @Test
    public void topUpStoreValueCardSuccessTest() throws InterruptedException {

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

        LiquidPay.getInstance().listUserStoreValueCards(new LiquidPayCallback<ResponseGetStoredValueCard>() {
            @Override
            public void onSuccess(ResponseGetStoredValueCard response) {
                recipientID = response.items().get(0).id();
                gotRecipientID = true;
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

        while (true) {
            if (gotRecipientID) {
                gotRecipientID = false;
                break;
            }
            SystemClock.sleep(1000);
        }

        LiquidPay.getInstance().getStoredValueCardTopUpOptions(recipientID, new LiquidPayCallback<ResponseGetStoredValueCardTopupOptions>() {
            @Override
            public void onSuccess(ResponseGetStoredValueCardTopupOptions response) {

                synchronized (syncObject) {
                    originatorID = response.items().listCard().get(0).id();
                    gotOriginatorID = true;
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                fail();
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }
        });

        while (true) {
            if (gotOriginatorID) {
                gotOriginatorID = false;
                break;
            }
            SystemClock.sleep(1000);
        }


        LiquidPay.getInstance().topUpStoreValueCard(recipientID, originatorID, "10.00", new LiquidPayCallback<ResponseTopUpStoredValueCard>() {

            @Override
            public void onSuccess(ResponseTopUpStoredValueCard response) {
                synchronized (syncObject) {
                    assertThat(response.amount(), equalTo(10.0f));
                    assertThat(response.topupStatus(), equalTo(TopupStatus.APPROVED));
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
    public void topUpStoreValueCardFailedTest() throws InterruptedException {
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

        LiquidPay.getInstance().listUserStoreValueCards(new LiquidPayCallback<ResponseGetStoredValueCard>() {
            @Override
            public void onSuccess(ResponseGetStoredValueCard response) {
                recipientID = response.items().get(0).id();
                gotRecipientID = true;
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

        while (true) {
            if (gotRecipientID) {
                gotRecipientID = false;
                break;
            }
            SystemClock.sleep(1000);
        }

        LiquidPay.getInstance().getStoredValueCardTopUpOptions(recipientID, new LiquidPayCallback<ResponseGetStoredValueCardTopupOptions>() {
            @Override
            public void onSuccess(ResponseGetStoredValueCardTopupOptions response) {

                synchronized (syncObject) {
                    originatorID = response.items().listCard().get(0).id();
                    gotOriginatorID = true;
                    syncObject.notify();
                }
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                fail();
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }
        });

        while (true) {
            if (gotOriginatorID) {
                gotOriginatorID = false;
                break;
            }
            SystemClock.sleep(1000);
        }


        LiquidPay.getInstance().topUpStoreValueCard(recipientID, originatorID, "0.00", new LiquidPayCallback<ResponseTopUpStoredValueCard>() {

                    @Override
                    public void onSuccess(ResponseTopUpStoredValueCard response) {
                        synchronized (syncObject) {
                            fail();
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
                }

        );

        synchronized (syncObject)

        {
            syncObject.wait();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testTopUpStoreValueCardWithoutInit() throws InterruptedException {
        LiquidPay.getInstance().isInitialized = false;
        LiquidPay.getInstance().topUpStoreValueCard("", "", "", new LiquidPayCallback<ResponseTopUpStoredValueCard>() {
            @Override
            public void onSuccess(ResponseTopUpStoredValueCard response) {
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
    public void testTopUpStoreValueCardWithNullArguments() {
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().topUpStoreValueCard(null, null, null, null);
    }
}
