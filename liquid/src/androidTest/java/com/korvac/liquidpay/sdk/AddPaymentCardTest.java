package com.korvac.liquidpay.sdk;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.korvac.liquidpay.sdk.constant.APIConfig;
import com.korvac.liquidpay.sdk.data.response.BaseCard;
import com.korvac.liquidpay.sdk.data.response.Card;
import com.korvac.liquidpay.sdk.data.response.Card3D;
import com.korvac.liquidpay.sdk.data.response.CardNon3D;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.data.response.ResponsePayment;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.main.LiquidPay;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.enums.CardStatus;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static com.korvac.liquidpay.sdk.TestData.EMAIL_FOR_CREATE_PAYMENT;
import static com.korvac.liquidpay.sdk.TestData.MERCHANT_REF_NO;
import static com.korvac.liquidpay.sdk.TestData.PASSWORD;
import static com.korvac.liquidpay.sdk.TestData.PAYEE;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Thanh Chuong on 15/06/17.
 */

public class AddPaymentCardTest {

    final Object syncObject = new Object();

    static boolean isLoggedIn = false;

    @Before
    public void setup() {
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().init(context, APIConfig.API_KEY, APIConfig.SECRET_KEY, null);
    }


    @Test
    public void addMasterCardSuccessTest() throws InterruptedException, ParseException {

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

        DateTime expiry = new DateTime().withYear(2020).withMonthOfYear(12);

        LiquidPay.getInstance().addPaymentCard("5520925402750796", "219", "Test Card", expiry.toDate(), new LiquidPayCallback<BaseCard>() {
            @Override
            public void onSuccess(BaseCard response) {
                Card3D card = (Card3D)response;
                assertTrue(!card.getAcs_url().isEmpty());
                assertTrue(!card.getMessage().isEmpty());
                assertTrue(!card.getCallback_url().isEmpty());
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
    public void addVisaCardSuccessTest() throws InterruptedException, ParseException {

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

        DateTime expiry = new DateTime().withYear(2020).withMonthOfYear(12);

        LiquidPay.getInstance().addPaymentCard("4147460827641772", "219", "Test Card", expiry.toDate(), new LiquidPayCallback<BaseCard>() {
            @Override
            public void onSuccess(BaseCard response) {
                CardNon3D card = (CardNon3D)response;
                assertTrue(!card.getBin().isEmpty());
                assertTrue(!card.getCard_brand().isEmpty());
                assertTrue(!card.getCard_type().isEmpty());
                assertTrue(!card.getCardholder().isEmpty());
                assertTrue(!card.getCurrency().isEmpty());
                assertTrue(!card.getExpiry_month().isEmpty());
                assertTrue(!card.getId().isEmpty());
                assertTrue(!card.getIssuer().isEmpty());
                assertTrue(!card.getLast_four().isEmpty());
                assertThat(card.getStatus(),equalTo(CardStatus.ACTIVE));
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
    public void addMasterCardFailTest() throws InterruptedException, ParseException {

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

        DateTime expiry = new DateTime().withYear(2020).withMonthOfYear(12);

        LiquidPay.getInstance().addPaymentCard("5405712875689259", "219", "Test Card", expiry.toDate(), new LiquidPayCallback<BaseCard>() {
            @Override
            public void onSuccess(BaseCard response) {
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

    @Test(expected = IllegalStateException.class)
    public void testAddCardWithoutInit() throws InterruptedException {
        LiquidPay.getInstance().isInitialized = false;
        DateTime expiry = new DateTime().withYear(2020).withMonthOfYear(12);
        LiquidPay.getInstance().addPaymentCard("5405712875689259", "219", "Test Card", expiry.toDate(), new LiquidPayCallback<BaseCard>() {
            @Override
            public void onSuccess(BaseCard response) {
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

    @Test(expected = IllegalArgumentException.class)
    public void testAddCardWithNullArguments() {
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().addPaymentCard(null, null, null, null, null);
    }
}
