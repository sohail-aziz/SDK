package com.korvac.liquidpay.sdk.main;

import android.content.SharedPreferences;

import com.korvac.liquidpay.sdk.data.ErrorUtils;
import com.korvac.liquidpay.sdk.data.response.Error;
import com.korvac.liquidpay.sdk.domain.Repository;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.utils.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


/**
 * Created by sohail on 5/16/2017.
 */


public class LoginTest {

    private static final String API_KEY = "K16ZaL8GIaJ6IejyTYGaFZbohgb3si1J";


    @Mock
    Repository mockRepository;
    @Mock
    SharedPreferences mockPreferences;
    @Mock
    Logger mockLogger;
    @Mock
    ErrorUtils mockErrorUtils;


    LiquidPayManager liquidPayManager;

    @Before
    public void setup() {


        MockitoAnnotations.initMocks(this);
        liquidPayManager = new LiquidPayManager(mockRepository, mockPreferences, mockErrorUtils, mockLogger);

    }

    @After
    public void tearDown() {

    }


//    @Test(expected = NullPointerException.class)
//    public void testLoginWillNullArgumentsShouldThrowNullPointerException() {
//
//        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);
//        liquidPayManager.setConfig(mockConfig);
//        liquidPayManager.loginWithPassword(null, anyString(), any(LiquidPayCallback.class));
//    }


    @Test(expected = IllegalStateException.class)
    public void testLoginWithoutConfig() {

        //liquidPayManager.loginWithPassword(null, null, null);
    }


    @Test
    public void testLoginSuccess() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);
        liquidPayManager.setConfig(mockConfig);

        LiquidPayCallback mockLoginListener = mock(LiquidPayCallback.class);


        //answer callback for success case
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {

                Callback callback = (Callback) invocation.getArguments()[3];
                //success response
                Response successResponse = Response.success(any());
                callback.onResponse(null, successResponse);
                return null;
            }
        }).when(mockRepository).login(any(LiquidPayConfig.class), eq("valid_username"), eq("valid_password"), any(LiquidPayCallback.class));

        //test
        liquidPayManager.login("valid_username", "valid_password", mockLoginListener);

        //verify
        verify(mockLoginListener).onSuccess(any());
        verifyNoMoreInteractions(mockLoginListener);

    }


    @Test
    public void testLoginFailureNetwork() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);
        liquidPayManager.setConfig(mockConfig);

        LiquidPayCallback mockLoginListener = mock(LiquidPayCallback.class);

        //answer callback for network error case
        doAnswer(new Answer() {
            public Void answer(InvocationOnMock invocation) {
                Callback callback = (Callback) invocation.getArguments()[3];
                callback.onFailure(null, new Throwable("io exception"));
                return null;
            }
        }).when(mockRepository).login(any(LiquidPayConfig.class), anyString(), anyString(), any(LiquidPayCallback.class));

        //test
        liquidPayManager.login("valid_username", "valid_password", mockLoginListener);

        //verify
        verify(mockLoginListener).onError(any(Throwable.class), anyString());
        verifyNoMoreInteractions(mockLoginListener);

    }


    @Test
    public void testLoginFailureServerError() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);
        liquidPayManager.setConfig(mockConfig);

        LiquidPayCallback mockLoginListener = mock(LiquidPayCallback.class);

        //mock api error
        when(mockErrorUtils.parseError(any(Response.class))).
             //   thenReturn(Error.create("error", "server error"));
                     thenReturn(new Error());

        //answer callback for server error (invalid credentials)
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Callback callback = (Callback) invocation.getArguments()[3];
                //failure response

                Response failureResponse = Response.error(400, mock(ResponseBody.class));
                callback.onResponse(null, failureResponse);
                return null;
            }
        }).when(mockRepository).login(any(LiquidPayConfig.class), anyString(), anyString(), any(LiquidPayCallback.class));

        //test
        liquidPayManager.login("invalid_username", "invalid_password", mockLoginListener);

        //verify
        verify(mockLoginListener).onError(any(LiquidPayException.class), eq("server error"));
        verifyNoMoreInteractions(mockLoginListener);

    }

}
