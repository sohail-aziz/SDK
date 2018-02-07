package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;


import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 *  TopUp Stored Value Card Response class
 */

@AutoParcelGson
public abstract class ResponseTopUpStoredValueCard implements Parcelable {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("originator")
    public abstract String originator();

    @SerializedName("recipient")
    public abstract String recipient();

    @SerializedName("topup_status")
    public abstract String topupStatus();

    @SerializedName("currency")
    public abstract String currency();

    @SerializedName("amount")
    public abstract Float amount();

    @SerializedName("final_currency")
    public abstract String finalCurrency();

    @SerializedName("final_amount")
    public abstract Float finalAmount();

    @SerializedName("fee")
    public abstract Float fee();

    @SerializedName("fee_details")
    public abstract List<FeeDetails> feeDetails();

    @SerializedName("cashback")
    public abstract Float cashback();

    @SerializedName("cashback_details")
    public abstract List<Object> cashbackDetails();

    @SerializedName("created_at")
    public abstract String createdAt();

    @SerializedName("updated_at")
    public abstract String updatedAt();

    public static ResponseTopUpStoredValueCard create(String type, String id, String originator, String recipient, String topupStatus, String currency, Float amount, String finalCurrency, Float finalAmount, Float fee, List<FeeDetails> feeDetails, Float cashback, List<Object> cashbackDetails, String createdAt, String updatedAt) {
        return new AutoParcelGson_ResponseTopUpStoredValueCard(type, id, originator, recipient, topupStatus, currency, amount, finalCurrency, finalAmount, fee, feeDetails, cashback, cashbackDetails, createdAt, updatedAt);
    }
}
