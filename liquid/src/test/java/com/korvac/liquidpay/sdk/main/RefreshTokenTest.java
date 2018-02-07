package com.korvac.liquidpay.sdk.main;

import android.content.SharedPreferences;

import com.korvac.liquidpay.sdk.data.ErrorUtils;
import com.korvac.liquidpay.sdk.domain.Repository;
import com.korvac.liquidpay.sdk.exception.LiquidPayException;
import com.korvac.liquidpay.sdk.data.response.Error;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
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
 * Created by sohail on 5/24/2017.
 */

public class RefreshTokenTest {

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
    public void testRefreshTokenSuccess() {

        LiquidPayCallback mockRefreshListener = mock(LiquidPayCallback.class);

        //given

            doAnswer(new Answer() {
                @Override
                public Object answer(InvocationOnMock invocation) throws Throwable {
                    Callback callback = (Callback) invocation.getArguments()[2];

                    ResponseLogin successResponse = ResponseLogin.create("", 0L, "", "", "", "");

                    callback.onResponse(null, Response.success(successResponse));
                    return null;
                }
            }).when(mockRepository).refreshToken(any(LiquidPayConfig.class), eq("valid_refresh_token"), any(LiquidPayCallback.class));



        //test
        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);

        liquidPayManager.setConfig(mockConfig);
        liquidPayManager.refreshToken("valid_refresh_token", mockRefreshListener);


        //verify
        verify(mockRefreshListener).onSuccess(any(ResponseLogin.class));
        verifyNoMoreInteractions(mockRefreshListener);

    }

    @Test
    public void testRefreshTokenServerFailure() {

        LiquidPayCallback mockRefreshListener = mock(LiquidPayCallback.class);

        //given
        //mock api error
        when(mockErrorUtils.parseError(any(Response.class))).
//                thenReturn(Error.create("error", "server error"));
        thenReturn(new Error());
        //given

            doAnswer(new Answer() {
                @Override
                public Object answer(InvocationOnMock invocation) throws Throwable {
                    Callback callback = (Callback) invocation.getArguments()[2];

                    Response failureResponse = Response.error(400, mock(ResponseBody.class));
                    callback.onResponse(null, failureResponse);
                    return null;
                }
            }).when(mockRepository).refreshToken(any(LiquidPayConfig.class), eq("invalid_refresh_token"), any(LiquidPayCallback.class));



        //test
        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);

        liquidPayManager.setConfig(mockConfig);
        liquidPayManager.refreshToken("invalid_refresh_token", mockRefreshListener);


        //verify
        verify(mockRefreshListener).onError(any(LiquidPayException.class), eq("server error"));
        verifyNoMoreInteractions(mockRefreshListener);

    }

    @Test
    public void testRefreshTokenFailureNetwork() {

        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);
        liquidPayManager.setConfig(mockConfig);

        LiquidPayCallback mockRefreshListener = mock(LiquidPayCallback.class);

        //answer callback for network error case

            doAnswer(new Answer() {
                public Void answer(InvocationOnMock invocation) {
                    Callback callback = (Callback) invocation.getArguments()[2];
                    callback.onFailure(null, new Throwable("io exception"));
                    return null;
                }
            }).when(mockRepository).refreshToken(any(LiquidPayConfig.class), eq("valid_refresh_token"), any(LiquidPayCallback.class));


        //test
        liquidPayManager.refreshToken("valid_refresh_token", mockRefreshListener);

        //verify
        verify(mockRefreshListener).onError(any(Throwable.class), anyString());
        verifyNoMoreInteractions(mockRefreshListener);

    }

}
