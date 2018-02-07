package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by Aldi on 11/16/2017.
 */

@AutoParcelGson
public abstract class AvailableStoredValueCard implements Parcelable {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("currency_name")
    public abstract String currencyName();

    @SerializedName("image_url")
    public abstract String imageUrl();

    @SerializedName("message")
    public abstract String message();

    public static AvailableStoredValueCard create(String type, String id, String name, String currencyName, String imageUrl, String message) {

        return new AutoParcelGson_AvailableStoredValueCard(type, id, name, currencyName, imageUrl, message);

    }
}
