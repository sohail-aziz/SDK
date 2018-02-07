package com.korvac.liquidpay.sdk.main;

import android.content.SharedPreferences;

import com.korvac.liquidpay.sdk.data.ErrorUtils;
import com.korvac.liquidpay.sdk.data.response.Error;
import com.korvac.liquidpay.sdk.data.response.ResponseValidateUserDetails;
import com.korvac.liquidpay.sdk.domain.Repository;
import com.korvac.liquidpay.sdk.utils.Logger;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import okhttp3.ResponseBody;
import retrofit2.Call;
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

public class ValidateDetailsTest {

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
    public void testValidateSuccess() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);
        liquidPayManager.setConfig(mockConfig);

        LiquidPayCallback<ResponseValidateUserDetails> mockCallback = mock(LiquidPayCallback.class);

        //given
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback callback = (Callback) invocation.getArguments()[8];
                //success response
                ResponseValidateUserDetails successResponse = ResponseValidateUserDetails.create(Collections.EMPTY_LIST, "type");
                Response response = Response.success(successResponse);

                callback.onResponse(null, response);
                return null;
            }
        }).when(mockRepository).validateUserDetails(any(LiquidPayConfig.class), eq("23423443"), anyString(), anyString(), anyString(),
                anyString(), anyString(), any(LiquidPayCallback.class));


        //test
        liquidPayManager.validateUserDetails("23423443", "", "", "", "", "", "", mockCallback);


        //verify
        verify(mockCallback).onSuccess(any(ResponseValidateUserDetails.class));
        verifyNoMoreInteractions(mockCallback);
    }

    @Test
    public void testValidateFailure() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);
        liquidPayManager.setConfig(mockConfig);

        LiquidPayCallback<ResponseValidateUserDetails> mockCallback = mock(LiquidPayCallback.class);

        //given
        //server error
        when(mockErrorUtils.parseError(any(Response.class))).
//                thenReturn(Error.create("type", "server error"));
        thenReturn(new Error());

        //given
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback callback = (Callback) invocation.getArguments()[8];
                //success response
                Response failureResponse = Response.error(400, mock(ResponseBody.class));
                callback.onResponse(null, failureResponse);
                return null;
            }
        }).when(mockRepository).validateUserDetails(any(LiquidPayConfig.class), eq("23423443"), anyString(), anyString(),
                anyString(), anyString(), anyString(), any(LiquidPayCallback.class));


        //test
        liquidPayManager.validateUserDetails("23423443", "", "", "", "", "", "", mockCallback);

        //verify
        verify(mockCallback).onError(any(Throwable.class), eq("server error"));
        verifyNoMoreInteractions(mockCallback);
    }


    @Test
    public void testValidateNetworkError() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        LiquidPayConfig mockConfig = mock(LiquidPayConfig.class);
        liquidPayManager.setConfig(mockConfig);

        LiquidPayCallback<ResponseValidateUserDetails> mockCallback = mock(LiquidPayCallback.class);

        //throw io exception
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback callback = (Callback) invocation.getArguments()[8];

                callback.onFailure(mock(Call.class), new Throwable("io error"));
                return null;
            }
        }).when(mockRepository).validateUserDetails(any(LiquidPayConfig.class), eq("23423443"), anyString(), anyString(), anyString(),
                anyString(), anyString(), any(LiquidPayCallback.class));


        //test
        liquidPayManager.validateUserDetails("23423443", "", "", "", "", "", "", mockCallback);

        //verify
        verify(mockCallback).onError(any(Throwable.class), eq("io error"));
        verifyNoMoreInteractions(mockCallback);


    }
}
