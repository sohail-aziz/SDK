package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Stored Value Card Response class
 */

@AutoParcelGson
public abstract class StoredValueCard implements Parcelable {


    @SerializedName("id")
    public abstract String id();

    @SerializedName("balance")
    public abstract Float balance();

    @SerializedName("image_url")
    public abstract String imageUrl();

    @SerializedName("issuer_message")
    public abstract String issuerMessage();

    @SerializedName("auto_topup")
    public abstract Boolean autoTopup();

    @SerializedName("currency")
    public abstract String currency();

    @SerializedName("status")
    public abstract String status();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("created_at")
    public abstract String createdAt();

    @SerializedName("updated_at")
    public abstract String updatedAt();


    @SerializedName("type")
    public abstract String type();

    public static StoredValueCard create(String id, Float balance, String imageUrl, String issuerMessage, Boolean autoTopup, String currency, String status, String name, String createdAt, String updatedAt, String type) {
        return new AutoParcelGson_StoredValueCard(id, balance, imageUrl, issuerMessage, autoTopup, currency, status, name, createdAt, updatedAt, type);
    }
}
