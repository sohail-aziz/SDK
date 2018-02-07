package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/21/17.
 */

@AutoParcelGson
public abstract class VoucherGiftBox implements Parcelable {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("image")
    public abstract String image();

    @SerializedName("cover_image")
    public abstract String coverImage();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("info_msg")
    public abstract String infoMsg();

    @SerializedName("promo_msg")
    public abstract String promoMsg();

    @SerializedName("quantity")
    public abstract Integer quantity();

    @SerializedName("factor_type")
    public abstract String factorType();

    @SerializedName("factor")
    public abstract Integer factor();

    @SerializedName("round_type")
    public abstract String roundType();

    @SerializedName("max_count")
    public abstract Integer maxCount();

    @SerializedName("available_count")
    public abstract Integer availableCount();

    @SerializedName("issued_at")
    public abstract String issuedAt();

    @SerializedName("expired_at")
    public abstract String expiredAt();

    @SerializedName("sender")
    public abstract String sender();

    @SerializedName("applicable_on")
    public abstract VoucherApplicableOn applicableOn();

    public static VoucherGiftBox create(
            String type,
            String id,
            String image,
            String coverImage,
            String name,
            String infoMsg,
            String promoMsg,
            Integer quantity,
            String factorType,
            Integer factor,
            String roundType,
            Integer maxCount,
            Integer availableCount,
            String issuedAt,
            String expiredAt,
            String sender,
            VoucherApplicableOn applicableOn) {

        return new AutoParcelGson_VoucherGiftBox(
                type,
                id,
                image,
                coverImage,
                name,
                infoMsg,
                promoMsg,
                quantity,
                factorType,
                factor,
                roundType,
                maxCount,
                availableCount,
                issuedAt,
                expiredAt,
                sender,
                applicableOn);
    }
}
