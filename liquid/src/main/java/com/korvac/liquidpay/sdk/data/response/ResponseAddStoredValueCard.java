package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Add Stored Value Card Response class
 */

@AutoParcelGson
public abstract class ResponseAddStoredValueCard implements Parcelable {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("balance")
    public abstract Float balance();

    @SerializedName("currency")
    public abstract String currency();

    @SerializedName("image_url")
    public abstract String imageUrl();

    @SerializedName("issuer_message")
    public abstract String issuerMessage();

    @SerializedName("auto_topup")
    public abstract Boolean autoTopup();

    @SerializedName("status")
    public abstract String status();

    @SerializedName("created_at")
    public abstract String createdAt();

    @SerializedName("updated_at")
    public abstract String updatedAt();

    public static ResponseAddStoredValueCard create(String type, String id, String name, Float balance, String currency, String imageUrl, String issuerMessage, Boolean autoTopup, String status, String createdAt, String updatedAt) {
        return new AutoParcelGson_ResponseAddStoredValueCard(type, id, name, balance, currency, imageUrl, issuerMessage, autoTopup, status, createdAt, updatedAt);
    }
}
