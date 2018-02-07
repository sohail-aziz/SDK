package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by Aldi on 11/15/2017.
 */

@AutoParcelGson
public abstract class AvailableMembershipCard implements Parcelable {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("image_url")
    public abstract String imageUrl();

    @SerializedName("discount_card_type")
    public abstract String discountCardType();

    @SerializedName("factor")
    public abstract Double factor();

    @SerializedName("factor_type")
    public abstract String factorType();

    @SerializedName("round_type")
    public abstract String roundType();

    @SerializedName("info_msg")
    public abstract String infoMsg();

    @SerializedName("promo_msg")
    public abstract String promoMsg();

    @SerializedName("terms_and_conditions")
    public abstract String termAndConditions();

    @SerializedName("start_at")
    public abstract String startAt();

    @SerializedName("end_at")
    public abstract String endAt();

    public static AvailableMembershipCard create(String type, String id, String name, String imageUrl,
                                                 String discountCardType, Double factor, String factorType,
                                                 String roundType, String infoMsg, String promoMsg, String termsAndCOnditions,
                                                 String startAt, String endAt) {

        return new AutoParcelGson_AvailableMembershipCard(type, id, name, imageUrl, discountCardType, factor,
                factorType, roundType, infoMsg, promoMsg, termsAndCOnditions, startAt, endAt);
    }
}
