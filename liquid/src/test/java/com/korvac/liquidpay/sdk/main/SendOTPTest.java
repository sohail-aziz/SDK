package com.korvac.liquidpay.sdk.main;

import android.content.SharedPreferences;

import com.korvac.liquidpay.sdk.data.ErrorUtils;
import com.korvac.liquidpay.sdk.data.response.Error;
import com.korvac.liquidpay.sdk.data.response.ResponseSendOTP;
import com.korvac.liquidpay.sdk.domain.Repository;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.utils.Logger;

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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by sohail on 5/25/2017.
 */

public class SendOTPTest {

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


    @Test
    public void testSendOTPSuccess() {

        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);
        liquidPayManager.setConfig(mockConfig);

        LiquidPayCallback mockSendOTPListener = mock(LiquidPayCallback.class);


        //answer callback for success case

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {

                Callback callback = (Callback) invocation.getArguments()[3];
                //success response
                final ResponseSendOTP successResponse = ResponseSendOTP.create("type", 300d, 600d, true);
                Response response = Response.success(successResponse);
                callback.onResponse(null, response);
                return null;
            }
        }).when(mockRepository).sendOTP(any(LiquidPayConfig.class), eq("valid_dialing_code"), eq("valid_mobile_number"), any(LiquidPayCallback.class));


        //test
        liquidPayManager.sendOneTimePassword("valid_dialing_code", "valid_mobile_number", mockSendOTPListener);

        //verify
        final ResponseSendOTP expectedResponse = ResponseSendOTP.create("type", 300d, 600d, true);
        verify(mockSendOTPListener).onSuccess(eq(expectedResponse));
        verifyNoMoreInteractions(mockSendOTPListener);

    }


    @Test
    public void testSendOTPFailureNetwork() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);
        liquidPayManager.setConfig(mockConfig);

        LiquidPayCallback mockSendOTPListener = mock(LiquidPayCallback.class);

        //answer callback for network error case
        doAnswer(new Answer() {
            public Void answer(InvocationOnMock invocation) {
                Callback callback = (Callback) invocation.getArguments()[3];
                callback.onFailure(null, new Throwable("io exception"));
                return null;
            }
        }).when(mockRepository).sendOTP(any(LiquidPayConfig.class), anyString(), anyString(), any(LiquidPayCallback.class));

        //test
        liquidPayManager.sendOneTimePassword("dialing_code", "mobile_number", mockSendOTPListener);

        //verify
        verify(mockSendOTPListener).onError(any(Throwable.class), anyString());
        verifyNoMoreInteractions(mockSendOTPListener);

    }


    @Test
    public void testSendOTPFailureServerError() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);
        liquidPayManager.setConfig(mockConfig);

        LiquidPayCallback mockSendOTPListener = mock(LiquidPayCallback.class);

        //mock api error
        when(mockErrorUtils.parseError(any(Response.class))).
//                thenReturn(Error.create("error", "server error"));
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
        }).when(mockRepository).sendOTP(any(LiquidPayConfig.class), anyString(), anyString(), any(LiquidPayCallback.class));

        //test
        liquidPayManager.sendOneTimePassword("invalid_dialing_code", "invalid_phone_number", mockSendOTPListener);

        //verify
        verify(mockSendOTPListener).onError(any(LiquidPayException.class), eq("server error"));
        verifyNoMoreInteractions(mockSendOTPListener);

    }
}
