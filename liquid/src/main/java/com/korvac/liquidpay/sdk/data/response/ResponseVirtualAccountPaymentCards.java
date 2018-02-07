package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/23/17.
 */

@AutoParcelGson
public abstract class ResponseVirtualAccountPaymentCards implements Parcelable {
    @SerializedName("type")
    public abstract String type();

    @SerializedName("items")
    public abstract List<PaymentCard> items();

    public static ResponseVirtualAccountPaymentCards create(String type, List<PaymentCard> items) {
        return new AutoParcelGson_ResponseVirtualAccountPaymentCards(type, items);
    }
}
