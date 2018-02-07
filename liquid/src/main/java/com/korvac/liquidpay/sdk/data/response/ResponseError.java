
package com.korvac.liquidpay.sdk.data.response;


import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class ResponseError {

    @SerializedName("errors")
    public abstract List<Error> errors();

    @SerializedName("type")
    public abstract String type();

    public static ResponseError create(List<Error> errorList, String type) {
        return new AutoParcelGson_ResponseError(errorList, type);
    }


}
