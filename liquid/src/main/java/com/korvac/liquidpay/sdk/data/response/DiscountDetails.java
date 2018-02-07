package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


import auto.parcelgson.AutoParcelGson;

/**
 * Discount Details Response Class
 */

@AutoParcelGson
public abstract class DiscountDetails implements Parcelable {

    @SerializedName("discount_type")
    public abstract  String discountType();
    
    @SerializedName("amount")
    public abstract  Float amount();

    @SerializedName("factor_type")
    public abstract  String factorType();

    @SerializedName("factor")
    public abstract  Float factor();

    @SerializedName("name")
    public abstract  String name();

    @SerializedName("description")
    public abstract  String description();

    public static DiscountDetails create(String discountType, Float amount, String factorType, Float factor, String name, String description) {
        return new AutoParcelGson_DiscountDetails(discountType, amount, factorType, factor, name, description);
    }

}
