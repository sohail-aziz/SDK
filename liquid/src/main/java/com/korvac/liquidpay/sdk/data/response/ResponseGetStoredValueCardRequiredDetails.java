package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;


import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 *  Get Stored Value Card Required Details Response class
 */

@AutoParcelGson
public abstract class ResponseGetStoredValueCardRequiredDetails implements Parcelable {

    @SerializedName("type")
    public abstract  String type();

    @SerializedName("fields")
    public abstract List<StoredValueCardRequiredDetails> fields();

    public static ResponseGetStoredValueCardRequiredDetails create(String type, List<StoredValueCardRequiredDetails> fields) {
        return new AutoParcelGson_ResponseGetStoredValueCardRequiredDetails(type, fields);
    }
}
