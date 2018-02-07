package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by aldi on 6/15/2017.
 */

@AutoParcelGson
public abstract class PaymentOptions implements Parcelable {

    @SerializedName("store_value_cards")
    public abstract List<PaymentOptionsStoredValueCard> listStoredValueCard();

    @SerializedName("payment_cards")
    public abstract List<PaymentOptionsCard> listCreditOrDebitCard();

    @SerializedName("virtual_accounts")
    public abstract List<PaymentOptionsVirtualAccounts> listVirtualAccounts();

    public static PaymentOptions create(
            List<PaymentOptionsStoredValueCard> listStoredValueCard,
            List<PaymentOptionsCard> listCreditOrDebitCard,
            List<PaymentOptionsVirtualAccounts> listVirtualAccounts) {

        return new AutoParcelGson_PaymentOptions(
                listStoredValueCard, listCreditOrDebitCard, listVirtualAccounts);
    }

}
