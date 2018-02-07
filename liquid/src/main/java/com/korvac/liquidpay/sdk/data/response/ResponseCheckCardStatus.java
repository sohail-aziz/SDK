package com.korvac.liquidpay.sdk.data.response;


import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by aldi on 6/15/2017.
 */

@AutoParcelGson
public abstract class ResponseCheckCardStatus implements Parcelable {

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

    @SerializedName("expiry_date")
    public abstract String expiryDate();

    public static ResponseCheckCardStatus create(String type, String id, String issuer, String cardBrand, String cardType, String status,
                                                 String currency, String firstSix, String lastFour, String cardHolder, String expiryDate) {

        return new AutoParcelGson_ResponseCheckCardStatus(type, id, issuer, cardBrand, cardType, status, currency, firstSix, lastFour, cardBrand, expiryDate);
    }
}
