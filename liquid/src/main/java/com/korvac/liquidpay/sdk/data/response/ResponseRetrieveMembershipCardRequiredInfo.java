package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by Aldi on 11/15/2017.
 */
@AutoParcelGson
public abstract class ResponseRetrieveMembershipCardRequiredInfo implements Parcelable {

    @SerializedName("type")
    public abstract  String type();

    @SerializedName("items")
    public abstract ResponseRequiredField items();

    public static ResponseRetrieveMembershipCardRequiredInfo create(String type, ResponseRequiredField items) {
        return new AutoParcelGson_ResponseRetrieveMembershipCardRequiredInfo(type, items);
    }
}
