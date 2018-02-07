package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;
import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by aldi on 6/15/2017.
 */

@AutoParcelGson
public abstract class PaymentOptionsStoredValueCard implements Parcelable {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("balance")
    public abstract Float balance();

    @SerializedName("image_url")
    public abstract String imageUrl();

    @SerializedName("issuer_message")
    public abstract String issuerMessage();

    @SerializedName("fee")
    public abstract Float fee();

    @SerializedName("fee_details")
    public abstract List<FeeDetails> feeDetails();

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

    public static PaymentOptionsStoredValueCard create(String type, String id, Float balance, String imageUrl, String issuerMessage, Float fee, List<FeeDetails> feeDetails,
                                                String currency, String status, String name, String createdAt, String updatedAt) {

        return new AutoParcelGson_PaymentOptionsStoredValueCard(type, id, balance, imageUrl, issuerMessage, fee, feeDetails, currency, status, name, createdAt, updatedAt);
    }
}
