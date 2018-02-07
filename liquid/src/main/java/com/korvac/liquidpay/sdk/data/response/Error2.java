
package com.korvac.liquidpay.sdk.data.response;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Error2 {

    @SerializedName("code")
    private String mCode;
    @SerializedName("message")
    private String mMessage;

    public Error2() {

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

}
