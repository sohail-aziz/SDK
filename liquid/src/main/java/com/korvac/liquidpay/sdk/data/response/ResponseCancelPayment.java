package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by Aldi on 11/7/2017.
 */
@AutoParcelGson
public abstract class ResponseCancelPayment implements Parcelable {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("succeed")
    public abstract Boolean succeed();

    public static ResponseCancelPayment create(String type, String id, Boolean succeed) {
        return new AutoParcelGson_ResponseCancelPayment(type, id, succeed);
    }

}