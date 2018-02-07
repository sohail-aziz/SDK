package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by Aldi on 11/10/2017.
 */

@AutoParcelGson
public abstract class ResponseRetrieveVirtualAccountRequiredInfo implements Parcelable {

    @SerializedName("type")
    public abstract  String type();

    @SerializedName("items")
    public abstract ResponseRequiredField items();

    public static ResponseRetrieveVirtualAccountRequiredInfo create(String type, ResponseRequiredField items) {
        return new AutoParcelGson_ResponseRetrieveVirtualAccountRequiredInfo(type, items);
    }
}
