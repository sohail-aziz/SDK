
package com.korvac.liquidpay.sdk.data.response;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Card3D extends BaseCard {

//    {"type":"pa_req","id":"source_eyJzb3VyY2VfdHlwZSI6ImNyZWRpdGNhcmQiLCJjYXJkX2d1aWQiOiJkOWMzYjJiOS0yYmViLTRlNzAtYTkwNi03YmEwNGM5MjMwMDEiLCJjdXN0X25vIjoiMjE4MyJ9","acs_url":"https://uat.liquidpay.com/liquidms/3dSimulator/simulator.html","callback_url":"https://lqdpay.com/PGtest/cb/PaymentResult.aspx?result=OK","message":"We will need to bill your card $5 to verify it. But don’t worry, it’ll be reversed immediately."}


    private String acs_url;
    private String callback_url;
    private String id;
    private String message;

    public String getType() {
        return type;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getAcs_url() {
        return acs_url;
    }

    public void setAcs_url(String acs_url) {
        this.acs_url = acs_url;
    }

    @Override
    public String toString() {
        return "Card3D{" +
                "acs_url='" + acs_url + '\'' +
                ", callback_url='" + callback_url + '\'' +
                ", id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
