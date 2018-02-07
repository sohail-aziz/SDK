package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by aldi on 6/13/2017.
 */

@AutoParcelGson
public abstract class ResponseCardBinDetails implements Parcelable{

    @SerializedName("type")
    public abstract String type();

    @SerializedName("issuer")
    public abstract String issuer();

    @SerializedName("bin")
    public abstract String bin();

    @SerializedName("card_brand")
    public abstract String cardBrand();

    @SerializedName("card_category")
    public abstract String cardCategory();

    @SerializedName("card_type")
    public abstract String cardType();

    @SerializedName("country")
    public abstract String country();

    @SerializedName("country_code")
    public abstract String countryCode();

    @SerializedName("currency")
    public abstract String currency();

    public static ResponseCardBinDetails create(String type, String issuer, String bin, String cardBrand, String cardCategory, String cardType, String country, String countryCode, String currency) {
        return new AutoParcelGson_ResponseCardBinDetails(type, issuer, bin, cardBrand, cardType, cardCategory, country, countryCode, currency);
    }
}
