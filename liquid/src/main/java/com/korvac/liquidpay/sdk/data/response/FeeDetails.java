package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;



import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by aldi on 6/13/2017.
 */

@AutoParcelGson
public abstract class FeeDetails implements Parcelable{

    @SerializedName("fee_type")
    public abstract String feeType();

    @SerializedName("amount")
    public abstract Float amount();

    @SerializedName("factor_type")
    public abstract String factorType();

    @SerializedName("factor")
    public abstract Float factor();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("description")
    public abstract String description();

    public static FeeDetails create(String feeType, Float amount, String factorType, Float factor, String name, String description) {
        return new AutoParcelGson_FeeDetails(feeType, amount, factorType, factor, name, description);
    }
}
