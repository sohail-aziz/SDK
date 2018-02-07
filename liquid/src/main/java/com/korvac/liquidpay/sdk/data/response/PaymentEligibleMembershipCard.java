package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/21/17.
 */

@AutoParcelGson
public abstract class PaymentEligibleMembershipCard implements Parcelable {
    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("image_url")
    public abstract String imageUrl();

    @SerializedName("status")
    public abstract String status();

    @SerializedName("base_currency")
    public abstract String baseCurrency();

    @SerializedName("discount_card_type")
    public abstract String discountCardType();

    @SerializedName("factor")
    public abstract Integer factor();

    @SerializedName("factor_type")
    public abstract String factorType();

    @SerializedName("round_type")
    public abstract String roundType();

    @SerializedName("info_msg")
    public abstract String infoMsg();

    @SerializedName("promo_msg")
    public abstract String promoMsg();

    @SerializedName("start_at")
    public abstract String startAt();

    @SerializedName("end_at")
    public abstract String endAt();

    @SerializedName("created_at")
    public abstract String createdAt();

    public static PaymentEligibleMembershipCard create(String type,
                                                       String id,
                                                       String name,
                                                       String imageUrl,
                                                       String status,
                                                       String baseCurrency,
                                                       String discountCardType,
                                                       Integer factor,
                                                       String factorType,
                                                       String roundType,
                                                       String infoMsg,
                                                       String promoMsg,
                                                       String startAt,
                                                       String endAt,
                                                       String createdAt) {

        return new AutoParcelGson_PaymentEligibleMembershipCard(type, id, name, imageUrl, status,
                baseCurrency, discountCardType, factor, factorType, roundType, infoMsg, promoMsg,
                startAt, endAt, createdAt);
    }
}
