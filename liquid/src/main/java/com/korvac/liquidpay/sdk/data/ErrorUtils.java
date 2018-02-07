package com.korvac.liquidpay.sdk.data;


import android.util.Log;

import com.korvac.liquidpay.sdk.data.response.Error;
import com.korvac.liquidpay.sdk.data.response.ResponseError;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Transforms API erros in to Error list

 * Created by sohail on 5/16/2017.
 */
@Singleton
public final class ErrorUtils {


    private Retrofit retrofit;

    @Inject
    public ErrorUtils(Retrofit retrofit) {

        this.retrofit = retrofit;
    }

    public Error parseError(Response<?> response) {
        Converter<ResponseBody, ResponseError> converter =
                retrofit.responseBodyConverter(ResponseError.class, ResponseError.class.getAnnotations());

        ResponseError errors;

        try {

            errors = converter.convert(response.errorBody());
            Log.d("ErrorUtils", errors.toString());
        } catch (IOException e) {
            errors = ResponseError.create(Collections.EMPTY_LIST, "cannot parse");
        }

        if (errors.errors().size() > 0) {
            Log.d("ErrorUtils", errors.errors().get(0).toString());
            return errors.errors().get(0);
        } else {
            //return Error.create("parsing error", "local_error");
            return new Error();
        }
    }

    public List<Error> parseErrorList(Response<?> response) {
        Converter<ResponseBody, ResponseError> converter =
                retrofit.responseBodyConverter(ResponseError.class, ResponseError.class.getAnnotations());

        ResponseError errors;

        try {

            errors = converter.convert(response.errorBody());
            Log.d("ErrorUtils", errors.toString());
        } catch (IOException e) {
            errors = ResponseError.create(Collections.EMPTY_LIST, "cannot parse");
        }

        if (errors.errors().size() > 0) {
                return errors.errors();
        } else {
            return Collections.EMPTY_LIST;
        }


    }


}
