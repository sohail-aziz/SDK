package com.korvac.liquidpay.sdk.data.response;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by sohail on 8/29/2017.
 */

@AutoParcelGson
public abstract class ResponseListCards {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("items")
    public abstract List<CardNon3D> cardList();

    public static ResponseListCards create(String type, List<CardNon3D> cards) {
        return new AutoParcelGson_ResponseListCards(type, cards);
    }
}
