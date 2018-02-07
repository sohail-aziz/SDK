
package com.korvac.liquidpay.sdk.data.response;


import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * AutoValue based Parcelable POJO class for Login and RefreshToken Response, and submit registration.
 */
@AutoParcelGson
public abstract class ResponseLogin implements Parcelable {

    @SerializedName("access_token")
    public abstract String accessToken();

    @SerializedName("expires_in")
    public abstract Long expiresIn();

    @SerializedName("refresh_token")
    public abstract String refreshToken();

    @SerializedName("subject_type")
    public abstract String subjectType();

    @SerializedName("token_type")
    public abstract String tokenType();

    @SerializedName("type")
    public abstract String type();


    public static ResponseLogin create(String accessToken, Long expiresIn, String refreshToken, String subjectType, String tokenType, String type) {
        return new AutoParcelGson_ResponseLogin(accessToken, expiresIn, refreshToken, subjectType, tokenType, type);
    }


}
