package com.korvac.liquidpay.sdk.data.response;


/**
 * Created by sohail on 6/21/2017.
 */


public class BaseCard {

    public static final String TYPE_3D = "pa_req";
    public static final String TYPE_NON_3D = "payment_card";


    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BaseCard{" +
                "type='" + type + '\'' +
                '}';
    }
}
