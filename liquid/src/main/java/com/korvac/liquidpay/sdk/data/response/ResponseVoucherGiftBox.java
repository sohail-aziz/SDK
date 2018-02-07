package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/21/17.
 */

@AutoParcelGson
public abstract class ResponseVoucherGiftBox implements Parcelable {
    @SerializedName("type")
    public abstract String type();

    @SerializedName("url")
    public abstract String url();

    @SerializedName("items")
    public abstract List<VoucherGiftBox> items();

    public static ResponseVoucherGiftBox create(
            String type,
            String url,
            List<VoucherGiftBox> items) {

        return new AutoParcelGson_ResponseVoucherGiftBox(type, url, items);
    }
}
