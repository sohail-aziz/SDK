package com.korvac.liquidpay.sdk.data.response;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by sohail on 5/23/2017.
 */

@AutoParcelGson
public abstract class Validation {

    @SerializedName("description")
    public abstract String description();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("validate_status")
    public abstract Boolean validateStatus();

    public static Validation create(String description, String name, Boolean validStatus) {
        return new AutoParcelGson_Validation(description, name, validStatus);
    }

}
