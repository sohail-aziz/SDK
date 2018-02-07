package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/22/17.
 */

@AutoParcelGson
public abstract class ResponseVoucherSendToFriend implements Parcelable {
    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("succeed")
    public abstract Boolean succeed();

    public static ResponseVoucherSendToFriend create(String type, String id, Boolean succeed) {
        return new AutoParcelGson_ResponseVoucherSendToFriend(type, id, succeed);
    }
}
