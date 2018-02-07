package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;
import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Stored Value Card Required Details Response class
 */

@AutoParcelGson
public abstract class StoredValueCardRequiredDetails implements Parcelable {

    @SerializedName("field_name")
    public abstract String fieldName();

    @SerializedName("field_display")
    public abstract String fieldDisplay();

    @SerializedName("field_type")
    public abstract String fieldType();

    @SerializedName("field_value")
    public abstract String fieldValue();

    @SerializedName("field_required")
    public abstract Boolean fieldRequired();

    @SerializedName("field_remarks")
    public abstract String fieldRemarks();

    public static StoredValueCardRequiredDetails create(String fieldName, String fieldDisplay, String fieldType, String fieldValue, Boolean fieldRequired, String fieldRemarks) {
        return new AutoParcelGson_StoredValueCardRequiredDetails(fieldName, fieldDisplay, fieldType, fieldValue, fieldRequired, fieldRemarks);
    }
}
