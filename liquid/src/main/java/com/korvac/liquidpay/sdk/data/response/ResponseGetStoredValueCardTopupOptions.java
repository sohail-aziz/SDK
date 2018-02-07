package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;


import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 *  Get Stored Value Card Topup Options Details Response class
 */

@AutoParcelGson
public abstract class ResponseGetStoredValueCardTopupOptions implements Parcelable {

    @SerializedName("type")
    public abstract  String type();

    @SerializedName("items")
    public abstract StoredValueCardTopupOptions items();

    public static ResponseGetStoredValueCardTopupOptions create(String type, StoredValueCardTopupOptions items) {
        return new AutoParcelGson_ResponseGetStoredValueCardTopupOptions(type, items);
    }
}
