package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by Aldi on 11/16/2017.
 */

@AutoParcelGson
public abstract class ResponseAvailableStoredValueCard implements Parcelable {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("url")
    public abstract String url();

    @SerializedName("data")
    public abstract List<AvailableStoredValueCard> data();

    @SerializedName("offset")
    public abstract Integer offset();

    @SerializedName("limit")
    public abstract Integer limit();

    @SerializedName("total_count")
    public abstract Integer totalCount();

    @SerializedName("has_more")
    public abstract Boolean hasMore();

    public static ResponseAvailableStoredValueCard create(String type, String url, List<AvailableStoredValueCard> data, Integer offset, Integer limit, Integer totalCount, Boolean hasMore) {

        return new AutoParcelGson_ResponseAvailableStoredValueCard(type, url, data, offset, limit, totalCount, hasMore);

    }

}
