package com.korvac.liquidpay.sdk.data.response;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by sohail on 5/26/2017.
 */

@AutoParcelGson
public abstract class ResponseSendVerifyEmail {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("accept_request")
    public abstract boolean acceptRequest();

    public static ResponseSendVerifyEmail create(String type, boolean acceptRequest) {
        return new AutoParcelGson_ResponseSendVerifyEmail(type, acceptRequest);
    }
}
