package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;
import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Stored Value Card Topup Options Response class
 */

@AutoParcelGson
public abstract class StoredValueCardTopupOptions implements Parcelable{

    @SerializedName("store_value_cards")
    public abstract List<StoredValueCard> listSVC();

    @SerializedName("payment_cards")
    public abstract List<Card> listCard();

    public static StoredValueCardTopupOptions create(List<StoredValueCard> listSVC, List<Card> listCard) {
        return new AutoParcelGson_StoredValueCardTopupOptions(listSVC, listCard);
    }
}
