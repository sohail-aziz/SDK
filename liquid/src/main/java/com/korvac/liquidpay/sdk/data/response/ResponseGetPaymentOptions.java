package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by aldi on 6/15/2017.
 */

@AutoParcelGson
public abstract class ResponseGetPaymentOptions implements Parcelable {

    @SerializedName("type")
    public abstract  String type();

    @SerializedName("items")
    public abstract PaymentOptions items();

    public static ResponseGetPaymentOptions create(String type, PaymentOptions items) {
        return new AutoParcelGson_ResponseGetPaymentOptions(type, items);
    }
}
