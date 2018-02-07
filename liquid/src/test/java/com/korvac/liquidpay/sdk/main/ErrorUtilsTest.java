package com.korvac.liquidpay.sdk.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.korvac.liquidpay.sdk.data.ErrorUtils;
import com.korvac.liquidpay.sdk.data.response.Error;

import org.junit.Test;

import auto.parcelgson.gson.AutoParcelGsonTypeAdapterFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by sohail on 5/19/2017.
 */


public class ErrorUtilsTest {

    public static final String BASE_URL = "https://liquidpay-dev.apigee.net/openapi/";


    @Test
    public void testErrorParsing() {


        HttpLoggingInterceptor.Level LOG_LEVEL = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(LOG_LEVEL);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new AutoParcelGsonTypeAdapterFactory()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ErrorUtils errorUtils = new ErrorUtils(retrofit);

        String sampleErrorBody = "{\n" +
                "  \"type\": \"error\",\n" +
                "  \"errors\": [\n" +
                "    {\n" +
                "      \"code\": \"reuqest_failed\",\n" +
                "      \"message\": \"The request currently cannot be processed, please try again later\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        Response sampleResponse = Response.error(400, ResponseBody.create(MediaType.parse("application/json"), sampleErrorBody));

//        test
        Error error = errorUtils.parseError(sampleResponse);

//        verify
      //  assertThat(error.message(), is("The request currently cannot be processed, please try again later"));


    }
}
