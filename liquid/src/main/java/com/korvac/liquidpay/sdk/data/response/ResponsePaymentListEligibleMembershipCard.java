package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/21/17.
 */

@AutoParcelGson
public abstract class ResponsePaymentListEligibleMembershipCard implements Parcelable {
    @SerializedName("type")
    public abstract String type();

    @SerializedName("url")
    public abstract String url();

    @SerializedName("items")
    public abstract List<PaymentEligibleMembershipCard> items();

    @SerializedName("offset")
    public abstract Integer offset();

    @SerializedName("limit")
    public abstract Integer limit();

    @SerializedName("total_count")
    public abstract Integer totalCount();

    @SerializedName("has_more")
    public abstract Boolean hasMore();

    public static ResponsePaymentListEligibleMembershipCard create(String type,
                                                                   String url,
                                                                   List<PaymentEligibleMembershipCard> items,
                                                                   Integer offset,
                                                                   Integer limit,
                                                                   Integer totalCount,
                                                                   Boolean hasMore) {

        return new AutoParcelGson_ResponsePaymentListEligibleMembershipCard(
                type, url, items, offset, limit, totalCount, hasMore);

    }
}
