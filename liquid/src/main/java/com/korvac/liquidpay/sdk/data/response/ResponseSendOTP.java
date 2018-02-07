package com.korvac.liquidpay.sdk.data.response;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Send OTP response class
 * <p>
 * Created by sohail on 5/25/2017.
 */

@AutoParcelGson
public abstract class ResponseSendOTP {

    //{type=otp, resend_interval=-1.0, expiry=180.0, allow_resend=false}
    @SerializedName("type")
    public abstract String type();

    @SerializedName("resend_interval")
    public abstract double resendInterval();

    @SerializedName("expiry")
    public abstract double expiry();

    @SerializedName("allow_resend")
    public abstract boolean allowResend();

    public static ResponseSendOTP create(String type, double resendInterval, double expiry, boolean allowResend) {
        return new AutoParcelGson_ResponseSendOTP(type, resendInterval, expiry, allowResend);
    }
}
