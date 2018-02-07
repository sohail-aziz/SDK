package com.korvac.liquidpay.sdk.data.response;

/**
 * Created by sohail on 5/23/2017.
 */

public class Error {

    @com.google.gson.annotations.SerializedName("code")
    private String mCode;
    @com.google.gson.annotations.SerializedName("message")
    private String mMessage;

    public Error() {

    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "mCode='" + mCode + '\'' +
                ", mMessage='" + mMessage + '\'' +
                '}';
    }
}
//@AutoParcelGson
//public abstract class Error {
//
//    @SerializedName("code")
//    public abstract String code();
//
//    @SerializedName("message")
//    public abstract String message();
//
//    public static Error create(String code, String message) {
//        return new AutoParcelGson_Error(code, message);
//    }
//
//
//}


