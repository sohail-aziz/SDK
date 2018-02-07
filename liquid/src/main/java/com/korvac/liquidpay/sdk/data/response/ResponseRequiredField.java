package com.korvac.liquidpay.sdk.data.response;

import android.os.Parcelable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by Aldi on 11/14/2017.
 */

@AutoParcelGson
public abstract class ResponseRequiredField implements Parcelable {

    @SerializedName("fields")
    public abstract List<RequiredField> fields();

    public static ResponseRequiredField create(List<RequiredField> fields) {
        return new AutoParcelGson_ResponseRequiredField(fields);
    }

}
