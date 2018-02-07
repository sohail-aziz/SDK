package com.korvac.liquidpay.sdk;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.korvac.liquidpay.sdk.constant.APIConfig;
import com.korvac.liquidpay.sdk.data.response.ResponseValidateUserDetails;
import com.korvac.liquidpay.sdk.main.LiquidPay;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Thanh Chuong on 24/5/17.
 */

public class ValidationTest {

    final Object syncObject = new Object();

    @Before
    public void setup(){
        Context context = InstrumentationRegistry.getContext();
        LiquidPay.getInstance().init(context, APIConfig.API_KEY,APIConfig.SECRET_KEY,null);
    }

    @Test
    public void validateValidEmailTest() throws InterruptedException {
        String validEmail = "chuongtuthanh@mailinator.com";
        LiquidPay.getInstance().validateUserEmail(validEmail, new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                assertTrue(response.list().get(0).validateStatus());
                assertThat(response.list().get(0).description(), equalTo("This email is available to register."));
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
    public void validateValidFirstNameTest() throws InterruptedException {
        String validFirstName = "Abc";
        LiquidPay.getInstance().validateUserFirstName(validFirstName, new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                assertTrue(response.list().get(0).validateStatus());
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
    public void validateValidLastNameTest() throws InterruptedException {
        String validLastName = "Abc";
        LiquidPay.getInstance().validateUserLastName(validLastName, new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                assertTrue(response.list().get(0).validateStatus());
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
    public void validateValidPromoCodeTest() throws InterruptedException {
        String validPromoCode = "NE9GE9";
        LiquidPay.getInstance().validatePromoCode(validPromoCode, new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                assertTrue(response.list().get(0).validateStatus());
                assertThat(response.list().get(0).description(),equalTo("This promo code is available to register."));
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
    public void validateValidMobileTest() throws InterruptedException {
        String validMobile = "82913156";
        LiquidPay.getInstance().validateUserMobile(validMobile, new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                assertTrue(response.list().get(0).validateStatus());
                assertThat(response.list().get(0).description(),equalTo("This mobile number is available to register."));
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

    @Test(expected = IllegalArgumentException.class)
    public void validateInvalidEmailTest() throws InterruptedException {
        String invalidEmail = "chuongtuthanh@com";
        LiquidPay.getInstance().validateUserEmail(invalidEmail, new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                assertTrue(!response.list().get(0).validateStatus());
                assertThat(response.list().get(0).description(), equalTo("Invalid email format"));
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

    @Test(expected = IllegalArgumentException.class)
    public void validateNullEmailTest() throws InterruptedException {
        LiquidPay.getInstance().validateUserEmail(null, new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
            }
        });
    }

    @Test
    public void validateInvalidFirstNameTest() throws InterruptedException {
        String invalidFirstName = "abc+";
        LiquidPay.getInstance().validateUserFirstName(invalidFirstName, new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                assertTrue(!response.list().get(0).validateStatus());
                assertThat(response.list().get(0).description(), equalTo("The first name does not allow special characters to be input."));
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
    public void validateInvalidLastNameTest() throws InterruptedException {
        String invalidLastName = "abc+";
        LiquidPay.getInstance().validateUserLastName(invalidLastName, new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                assertTrue(!response.list().get(0).validateStatus());
                assertThat(response.list().get(0).description(), equalTo("The first name does not allow special characters to be input."));
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
    public void validateInvalidMobileTest() throws InterruptedException {
        String invalidMobile = "99999999999";
        LiquidPay.getInstance().validateUserMobile(invalidMobile, new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                assertTrue(!response.list().get(0).validateStatus());
                assertThat(response.list().get(0).description(), equalTo("Mobile number is invalid"));
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

    @Test(expected = IllegalArgumentException.class)
    public void validateNullMobileTest() throws InterruptedException {
        LiquidPay.getInstance().validateUserMobile(null, new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
            }
        });
    }

    @Test
    public void validateInvalidMobileWhichAlreadyRegisteredTest() throws InterruptedException {
        String registeredMobile = "82813156";
        LiquidPay.getInstance().validateUserMobile(registeredMobile, new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                assertTrue(!response.list().get(0).validateStatus());
                assertThat(response.list().get(0).description(),
                        equalTo("You might have registered this mobile number in another Liquid account. Please enter a different mobile number."));
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
    public void validateInvalidPromoCodeWhichDoesNotExistTest() throws InterruptedException {
        String invalidPromoCode = "GN8NG9";
        LiquidPay.getInstance().validatePromoCode(invalidPromoCode, new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                assertTrue(!response.list().get(0).validateStatus());
                assertThat(response.list().get(0).description(),
                        equalTo("The promo code cannot be found, please enter a valid promo code."));
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
    public void validateInvalidPromoCodeWhichAlreadyExpired() throws InterruptedException {
        String expiredPromoCode = "2QAPB2";
        LiquidPay.getInstance().validatePromoCode(expiredPromoCode, new LiquidPayCallback<ResponseValidateUserDetails>() {
            @Override
            public void onSuccess(ResponseValidateUserDetails response) {
                assertTrue(!response.list().get(0).validateStatus());
                assertThat(response.list().get(0).description(),
                        equalTo("The promo code cannot be found, please enter a valid promo code."));
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
    public void validateValidUserDetailsTest() throws InterruptedException {
        String validPromoCode = "NE9GE9";
        String validEmail = "chuongtuthanh@mailinator.com";
        String validDialingCode = "65";
        String validMobile = "82738475";
        String validFirstName = "Thanh";
        String validLastName = "Chuong";
        LiquidPay.getInstance().validateRegistrationDetails(
                validMobile, validEmail, validPromoCode, validFirstName, validLastName, validDialingCode,new LiquidPayCallback<ResponseValidateUserDetails>() {
                    @Override
                    public void onSuccess(ResponseValidateUserDetails response) {
                        assertThat(response.list().size(),equalTo(6));
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
}
