package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by Aldi on 11/14/2017.
 */

@AutoParcelGson
public abstract class ResponseUnLinkCardFromVirtualAccount implements Parcelable {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("succeed")
    public abstract Boolean succeed();

    public static ResponseUnLinkCardFromVirtualAccount create(String type, String id, Boolean succeed) {
        return new AutoParcelGson_ResponseUnLinkCardFromVirtualAccount(type, id, succeed);
    }
}