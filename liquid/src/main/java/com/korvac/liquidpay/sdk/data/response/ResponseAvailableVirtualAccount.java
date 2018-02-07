package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by Aldi on 11/10/2017.
 */
@AutoParcelGson
public abstract class ResponseAvailableVirtualAccount implements Parcelable {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("url")
    public abstract String url();

    @SerializedName("items")
    public abstract List<VirtualAccount> items();

    @SerializedName("offset")
    public abstract Integer offset();

    @SerializedName("limit")
    public abstract Integer limit();

    @SerializedName("total_count")
    public abstract Integer totalCount();

    @SerializedName("has_more")
    public abstract Boolean hasMore();

    public static ResponseAvailableVirtualAccount create(String type, String url, List<VirtualAccount> items, Integer offset, Integer limit, Integer totalCount, Boolean hasMore) {
        return new AutoParcelGson_ResponseAvailableVirtualAccount(type, url, items, offset, limit, totalCount, hasMore);
    }

}
