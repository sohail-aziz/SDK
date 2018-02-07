package com.korvac.liquidpay.sdk.data.response;

import java.util.List;

/**
 * Created by sohail on 6/22/2017.
 */

public class ErrorList {

    //{"type":"error","errors":[{"code":"internal_server_error","message":"We currently cannot process your request.  Please contact contact@liquidpay.com if the problem persists."}]}
    public String type;
    public List<Error> errors;

    public ErrorList() {
    }

    @Override
    public String toString() {
        return "ErrorList{" +
                "type='" + type + '\'' +
                ", errorList=" + errors +
                '}';
    }
}
