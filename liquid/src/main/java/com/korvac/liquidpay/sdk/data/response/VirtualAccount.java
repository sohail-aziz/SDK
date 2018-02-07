package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by Aldi on 11/14/2017.
 */

@AutoParcelGson
public abstract class VirtualAccount implements Parcelable {

    @SerializedName("type")
    public abstract String type();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("issuer")
    public abstract String issuer();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("image_url")
    public abstract String imageUrl();

    @SerializedName("card_image_url")
    public abstract String cardImageUrl();

    @SerializedName("currency_code")
    public abstract String currencyCode();

    @SerializedName("pre_auth_amount")
    public abstract Double preAuthAmount();

    @SerializedName("linked_card")
    public abstract String linkedCard();

    @SerializedName("info_msg")
    public abstract String infoMsg();

    @SerializedName("promo_msg")
    public abstract String promoMsg();

    @SerializedName("terms_and_conditions")
    public abstract String termAndConditions();

    public static VirtualAccount create(String type, String id, String issuer, String name, String imageUrl,
                                        String cardImageUrl, String currencyCode, Double preAuthAMount, String linkedCard,
                                        String infoMsg, String promoMsg, String termAndConditions) {

        return new AutoParcelGson_VirtualAccount(type, id, issuer, name, imageUrl, cardImageUrl, currencyCode,
                preAuthAMount, linkedCard, infoMsg, promoMsg, termAndConditions);
    }
}
