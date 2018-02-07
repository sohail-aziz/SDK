package com.korvac.liquidpay.sdk.main;

import java.util.Date;

/**
 * Created by sohail on 6/16/2017.
 */

public interface LiquidDateFormatter {


    String toServerDate(Date date);

    String toServerDateYearMonth(Date date);

    Date fromServerDate(String date);

    String getMonthMM(Date date);

    String getYearYYYY(Date date);

}
