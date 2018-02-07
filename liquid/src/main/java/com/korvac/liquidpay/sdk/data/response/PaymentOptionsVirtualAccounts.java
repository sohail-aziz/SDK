package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by aldi on 6/15/2017.
 */

@AutoParcelGson
public abstract class PaymentOptionsVirtualAccounts implements Parcelable {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("issuer")
    public abstract String issuer();

    @SerializedName("currency")
    public abstract String currency();

    @SerializedName("fee")
    public abstract Float fee();

    @SerializedName("fee_details")
    public abstract List<FeeDetails> feeDetails();

    @SerializedName("discount")
    public abstract Float discount();

    @SerializedName("discount_details")
    public abstract List<DiscountDetails> discountDetails();

    public static PaymentOptionsVirtualAccounts create(
            String type,
            String id,
            String name,
            String issuer,
            String currency,
            Float fee,
            List<FeeDetails> feeDetails,
            Float discount,
            List<DiscountDetails> discountDetails) {


        return new AutoParcelGson_PaymentOptionsVirtualAccounts(
                type,
                id,
                name,
                issuer,
                currency,
                fee,
                feeDetails,
                discount,
                discountDetails);
    }
}

