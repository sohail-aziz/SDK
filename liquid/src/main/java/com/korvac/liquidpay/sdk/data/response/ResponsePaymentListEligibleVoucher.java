package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/21/17.
 */

@AutoParcelGson
public abstract class ResponsePaymentListEligibleVoucher implements Parcelable {
    @SerializedName("type")
    public abstract String type();

    @SerializedName("url")
    public abstract String url();

    @SerializedName("items")
    public abstract List<Voucher> items();

    public static ResponsePaymentListEligibleVoucher create(
            String type,
            String url,
            List<Voucher> items) {

        return new AutoParcelGson_ResponsePaymentListEligibleVoucher(type, url, items);
    }
}
