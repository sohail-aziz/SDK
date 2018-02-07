package com.korvac.liquidpay.sdk.data.response;


import android.os.Parcelable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Create Payment Response Class
 */

@AutoParcelGson
public abstract class ResponsePayment implements Parcelable {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("payment_token")
    public abstract String paymentToken();

    @SerializedName("payment_code")
    public abstract String paymentCode();

    @SerializedName("service_type")
    public abstract String serviceType();

    @SerializedName("payment_status")
    public abstract String paymentStatus();

    @SerializedName("merchant_ref_no")
    public abstract String merchantRefNo();

    @SerializedName("original_currency")
    public abstract String originalCurrency();

    @SerializedName("original_amount")
    public abstract Float originalAmount();

    @SerializedName("exchange_rate")
    public abstract Float exchangeRate();

    @SerializedName("final_currency")
    public abstract String finalCurrency();

    @SerializedName("final_amount")
    public abstract Float finalAmount();

    @SerializedName("payee")
    public abstract String payee();

    @SerializedName("payer")
    public abstract String payer();

    @SerializedName("fee")
    public abstract Float fee();

    @SerializedName("fee_details")
    public abstract List<FeeDetails> feeDetails();

    @SerializedName("discount")
    public abstract Float discount();

    @SerializedName("discount_details")
    public abstract List<DiscountDetails> discountDetails();

    @SerializedName("source")
    public abstract String source();

    @SerializedName("metadata")
    public abstract String metadata();

    @SerializedName("created_at")
    public abstract String createdAt();

    @SerializedName("updated_at")
    public abstract String updatedAt();

    public static ResponsePayment create(String type, String id, String paymentToken, String paymentCode, String serviceType, String paymentStatus, String merchantRefNo, String originalCurrency, Float originalAmount, Float exchangeRate, String finalCurrency, Float finalAmount, String payee, String payer, Float fee, List<FeeDetails> feeDetails, Float discount, List<DiscountDetails> discountDetails, String source, String metadata, String createdAt, String updatedAt) {
        return new AutoParcelGson_ResponsePayment(type, id, paymentToken, paymentCode, serviceType, paymentStatus, merchantRefNo, originalCurrency, originalAmount, exchangeRate, finalCurrency, finalAmount, payee, payer, fee, feeDetails, discount, discountDetails, source, metadata, createdAt, updatedAt);
    }
}
