package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;


import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 *  Get Added Stored Value Card Response class
 */

@AutoParcelGson
public abstract class ResponseGetStoredValueCard implements Parcelable {

    @SerializedName("type")
    public abstract  String type();

    @SerializedName("items")
    public abstract List<StoredValueCard> items();

    public static ResponseGetStoredValueCard create(String type, List<StoredValueCard> items) {
        return new AutoParcelGson_ResponseGetStoredValueCard(type, items);
    }
}
