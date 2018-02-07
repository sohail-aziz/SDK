package com.korvac.liquidpay.sdk.main;

import android.content.SharedPreferences;

import com.korvac.liquidpay.sdk.data.ErrorUtils;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.domain.Repository;
import com.korvac.liquidpay.sdk.data.response.Error;
import com.korvac.liquidpay.sdk.data.response.ResponseSendOTP;
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

public class SubmitRegistrationDetailsTest {

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
    public void testSubmitRegistrationDetailsSuccess() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);
        liquidPayManager.setConfig(mockConfig);

        LiquidPayCallback mockSubmitRegistrationListener = mock(LiquidPayCallback.class);


        //answer callback for success case
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {

                Callback callback = (Callback) invocation.getArguments()[10];
                //success response
                final ResponseSendOTP successResponse = ResponseSendOTP.create("type", 300d, 600d, true);
                Response response = Response.success(successResponse);
                callback.onResponse(null, response);
                return null;
            }
        }).when(mockRepository).submitRegistrationDetails(any(LiquidPayConfig.class), anyString(), anyString(), anyString(), anyString(), anyString(),
                anyString(), anyString(), anyString(), anyString(), any(LiquidPayCallback.class));

        //test
        liquidPayManager.submitRegistrationDetails("email_address", "dialing_code", "mobile_number", "otp", "firstName", "lastName", "password", "", "", mockSubmitRegistrationListener);


        //verify
        ResponseSendOTP expectedResponse = ResponseSendOTP.create("type", 300d, 600d, false);
        verify(mockSubmitRegistrationListener).onSuccess(eq(expectedResponse));
        verifyNoMoreInteractions(mockSubmitRegistrationListener);

    }


    @Test
    public void testSubmitRegistrationFailureNetwork() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);
        liquidPayManager.setConfig(mockConfig);

        LiquidPayCallback mockSubmitRegistrationListener = mock(LiquidPayCallback.class);

        //answer callback for network error case
        doAnswer(new Answer() {
            public Void answer(InvocationOnMock invocation) {
                Callback callback = (Callback) invocation.getArguments()[10];
                callback.onFailure(null, new Throwable("io exception"));
                return null;
            }
        }).when(mockRepository).submitRegistrationDetails(any(LiquidPayConfig.class), anyString(), anyString(), anyString(), anyString(),
                anyString(), anyString(), anyString(), anyString(), anyString(), any(LiquidPayCallback.class));

        //test
        liquidPayManager.submitRegistrationDetails("email_address", "dialing_code", "mobile_number", "otp", "firstName", "lastName", "password", "", "", mockSubmitRegistrationListener);

        //verify
        verify(mockSubmitRegistrationListener).onError(any(Throwable.class), anyString());
        verifyNoMoreInteractions(mockSubmitRegistrationListener);

    }


    public void testSubmitRegistrationDetailsFailureServerError() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);
        liquidPayManager.setConfig(mockConfig);

        LiquidPayCallback mockSubmitRegistrationListener = mock(LiquidPayCallback.class);

        //mock api error
        when(mockErrorUtils.parseError(any(Response.class))).
//                thenReturn(Error.create("error", "server error"));
        thenReturn(new Error());

        //answer callback for server error (invalid credentials)
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Callback callback = (Callback) invocation.getArguments()[10];
                //failure response

                Response failureResponse = Response.error(400, mock(ResponseBody.class));
                callback.onResponse(null, failureResponse);
                return null;
            }
        }).when(mockRepository).submitRegistrationDetails(any(LiquidPayConfig.class), anyString(), anyString(), anyString(), anyString(),
                anyString(), anyString(), anyString(), anyString(), anyString(), any(LiquidPayCallback.class));

        //test
        liquidPayManager.submitRegistrationDetails("email_address", "dialing_code", "mobile_number", "otp", "firstName", "lastName", "password", "", "", mockSubmitRegistrationListener);

        //verify
        verify(mockSubmitRegistrationListener).onError(any(LiquidPayException.class), eq("server error"));
        verifyNoMoreInteractions(mockSubmitRegistrationListener);

    }
}
