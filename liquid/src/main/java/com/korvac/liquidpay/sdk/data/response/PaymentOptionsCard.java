package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by aldi on 6/15/2017.
 */

@AutoParcelGson
public abstract class PaymentOptionsCard implements Parcelable {

    @SerializedName("fee_details")
    public abstract List<FeeDetails> feeDetails();

    @SerializedName("discount_details")
    public abstract List<DiscountDetails> discountDetails();

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

    @SerializedName("status")
    public abstract String status();

    @SerializedName("currency")
    public abstract String currency();

    @SerializedName("first_six")
    public abstract String firstSix();

    @SerializedName("last_four")
    public abstract String lastFour();

    @SerializedName("cardholder")
    public abstract String cardholder();

//    @SerializedName("expiry_date")
//    public abstract String expiryDate();

    @SerializedName("expiry_year")
    public abstract String expiryYear();

    @SerializedName("expiry_month")
    public abstract String expiryMonth();

    @SerializedName("fee")
    public abstract Float fee();

    @SerializedName("discount")
    public abstract Float discount();

    public static PaymentOptionsCard create(List<FeeDetails> feeDetails, List<DiscountDetails> discountDetails, String type,
                                            String issuer, String cardBrand, String cardType, String id, String status, String currency,
                                            String firstSix, String lastFour, String cardHolder, String expiryYear, String expiryMonth, Float fee, Float discount) {


        return new AutoParcelGson_PaymentOptionsCard(feeDetails, discountDetails, type, issuer, cardBrand, cardType, id, status, currency,
                firstSix, lastFour, cardHolder, expiryYear, expiryMonth, fee, discount);
    }
}

