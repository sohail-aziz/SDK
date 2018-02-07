package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/21/17.
 */

@AutoParcelGson
public abstract class VoucherApplicableOn implements Parcelable {
    @SerializedName("monday")
    public abstract Boolean monday();

    @SerializedName("tuesday")
    public abstract Boolean tuesday();

    @SerializedName("wednesday")
    public abstract Boolean wednesday();

    @SerializedName("thursday")
    public abstract Boolean thursday();

    @SerializedName("friday")
    public abstract Boolean friday();

    @SerializedName("saturday")
    public abstract Boolean saturday();

    @SerializedName("sunday")
    public abstract Boolean sunday();

    @SerializedName("holiday")
    public abstract Boolean holiday();

    @SerializedName("special_occasion")
    public abstract String specialOccasion();

    @SerializedName("payment_sources")
    public abstract List<String> paymentSources();

    @SerializedName("transaction_types")
    public abstract List<String> transactionTypes();

    public static VoucherApplicableOn create(
            Boolean monday,
            Boolean tuesday,
            Boolean wednesday,
            Boolean thursday,
            Boolean friday,
            Boolean saturday,
            Boolean sunday,
            Boolean holiday,
            String specialOccasion,
            List<String> paymentSources,
            List<String> transactionTypes) {

        return new AutoParcelGson_VoucherApplicableOn(
                monday,
                tuesday,
                wednesday,
                thursday,
                friday,
                saturday,
                sunday,
                holiday,
                specialOccasion,
                paymentSources,
                transactionTypes);
    }
}
