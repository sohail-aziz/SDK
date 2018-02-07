
package com.korvac.liquidpay.sdk.data.response;


import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class ResponseValidateUserDetails {

    @SerializedName("list")
    public abstract java.util.List<Validation> list();

    @SerializedName("type")
    public abstract String type();

    public static ResponseValidateUserDetails create(java.util.List<Validation> list, String type) {
        return new AutoParcelGson_ResponseValidateUserDetails(list, type);
    }


}
