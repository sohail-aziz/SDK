package com.korvac.liquidpay.sdk.data.response;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by sohail on 8/29/2017.
 */

@AutoParcelGson
public abstract class ResponseDeleteCard {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("succeed")
    public abstract Boolean isSucceeded();

    public static ResponseDeleteCard create(String type, String id, Boolean succeed) {
        return new AutoParcelGson_ResponseDeleteCard(type, id, succeed);
    }

}

