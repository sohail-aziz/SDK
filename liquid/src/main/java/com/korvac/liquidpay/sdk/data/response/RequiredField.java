package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by Aldi on 11/10/2017.
 */

@AutoParcelGson
public abstract class RequiredField implements Parcelable {

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

    public static RequiredField create(String fieldName, String fieldDisplay, String fieldType, String fieldValue, Boolean fieldRequired, String fieldRemarks) {
        return new AutoParcelGson_RequiredField(fieldName, fieldDisplay, fieldType, fieldValue, fieldRequired, fieldRemarks);
    }
}