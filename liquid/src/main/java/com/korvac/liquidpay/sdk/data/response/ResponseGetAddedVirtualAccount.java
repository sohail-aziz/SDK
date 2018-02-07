package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by Aldi on 11/14/2017.
 */

@AutoParcelGson
public abstract class ResponseGetAddedVirtualAccount implements Parcelable {


    @SerializedName("type")
    public abstract  String type();

    @SerializedName("items")
    public abstract List<VirtualAccount> items();

    public static ResponseGetAddedVirtualAccount create(String type, List<VirtualAccount> items) {
        return new AutoParcelGson_ResponseGetAddedVirtualAccount(type, items);
    }

}
