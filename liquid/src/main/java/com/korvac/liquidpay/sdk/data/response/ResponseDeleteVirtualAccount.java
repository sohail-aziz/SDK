package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by Aldi on 11/14/2017.
 */

@AutoParcelGson
public abstract class ResponseDeleteVirtualAccount implements Parcelable {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("succeed")
    public abstract Boolean succeed();

    public static ResponseDeleteVirtualAccount create(String type, String id, Boolean succeed) {
        return new AutoParcelGson_ResponseDeleteVirtualAccount(type, id, succeed);
    }
}