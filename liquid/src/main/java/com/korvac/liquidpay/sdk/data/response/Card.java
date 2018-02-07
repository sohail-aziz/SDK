package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Card Response class
 */

@AutoParcelGson
public abstract class Card implements Parcelable {


    @SerializedName("type")
    public abstract String type();

    @SerializedName("issuer")
    public abstract String issuer();

    @SerializedName("card_brand")
    public abstract String cardBrand();

    @SerializedName("card_type")
    public abstract String cardType();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("fee_waiver")
    public abstract Boolean feeWaiver();

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


    public static Card create(String type, String issuer, String cardBrand, String cardType, String id, Boolean waiver, String status,
                              String currency, String bin, String lastFour, String cardHolder, String expiryYear, String expiryMonth) {
        return new AutoParcelGson_Card(type, issuer, cardBrand, cardType, id, waiver, status, currency, bin, lastFour, cardHolder, expiryYear, expiryMonth);
    }
}
