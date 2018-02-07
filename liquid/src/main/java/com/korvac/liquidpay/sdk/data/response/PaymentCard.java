package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/23/17.
 */

@AutoParcelGson
public abstract class PaymentCard implements Parcelable {
    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("issuer")
    public abstract String issuer();

    @SerializedName("card_brand")
    public abstract String cardBrand();

    @SerializedName("card_type")
    public abstract String cardType();

    @SerializedName("status")
    public abstract String status();

    @SerializedName("currency")
    public abstract String currency();

    @SerializedName("bin")
    public abstract String bin();

    @SerializedName("last_four")
    public abstract String lastFour();

    @SerializedName("cardholder")
    public abstract String cardholder();

    @SerializedName("expiry_year")
    public abstract String expiryYear();

    @SerializedName("expiry_month")
    public abstract String expiryMonth();

    @SerializedName("is_verified")
    public abstract Boolean isVerified();

    public static PaymentCard create(
            String type,
            String id,
            String issuer,
            String cardBrand,
            String cardType,
            String status,
            String currency,
            String bin,
            String lastFour,
            String cardholder,
            String expiryYear,
            String expiryMonth,
            Boolean isVerified){

        return new AutoParcelGson_PaymentCard(
                type,
                id,
                issuer,
                cardBrand,
                cardType,
                status,
                currency,
                bin,
                lastFour,
                cardholder,
                expiryYear,
                expiryMonth,
                isVerified);
    }
}
