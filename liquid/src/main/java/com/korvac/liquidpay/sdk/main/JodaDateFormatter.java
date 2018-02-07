package com.korvac.liquidpay.sdk.main;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Joda DateTime formatter
 * Created by sohail on 6/16/2017.
 */

@Singleton
public class JodaDateFormatter implements LiquidDateFormatter {


    @Inject
    public JodaDateFormatter() {
    }

    @Override
    public String toServerDate(Date date) {


        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dateTime = new DateTime(date);
        return dateTimeFormat.print(dateTime);

    }

    @Override
    public String toServerDateYearMonth(Date date) {
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyyMM");
        DateTime dateTime = new DateTime(date);
        return dateTimeFormat.print(dateTime);
    }

    @Override
    public Date fromServerDate(String date) {
        //TODO implement this method
        return null;
    }

    @Override
    public String getMonthMM(Date date) {
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("MM");
        DateTime dateTime = new DateTime(date);
        return dateTimeFormat.print(dateTime);

    }

    @Override
    public String getYearYYYY(Date date) {
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyy");
        DateTime dateTime = new DateTime(date);
        return dateTimeFormat.print(dateTime);
    }
}
