
package com.korvac.liquidpay.sdk.data.response;


import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class ResponseCheckEmailVerification {

    @SerializedName("email_address")
    public abstract String emailAddress();

    @SerializedName("email_verified")
    public abstract Boolean IsEmailVerified();

    @SerializedName("type")
    public abstract String type();

    public static ResponseCheckEmailVerification create(String emailAddress, boolean emailVerified, String type) {

        return new AutoParcelGson_ResponseCheckEmailVerification(emailAddress, emailVerified, type);
    }
}
