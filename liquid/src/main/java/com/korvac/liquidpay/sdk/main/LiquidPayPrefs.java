package com.korvac.liquidpay.sdk.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.korvac.liquidpay.sdk.utils.PreConditions;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by sohail on 6/7/2017.
 */

@Singleton
public class LiquidPayPrefs {

    private static final String KEY_ACCESS_TOKEN = "liquid_pay_sdk_access_token";
    private static final String KEY_REFRESH_TOKEN = "liquid_pay_sdk_refresh_token";
    private static final String KEY_TOKEN_EXPIRY = "liquid_pay_sdk_token_expiry";
    public static final String KEY_TOKEN_EXPIRY_TIMESTAMP = "liquid_pay_sdk_token_expiry_time_stamp";


    private final SharedPreferences preferences;

    @Inject
    public LiquidPayPrefs(Context context) {

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public synchronized void setTokenInfo(String accessToken, String refreshToken, Long expiry) {

        PreConditions.checkNotNull(accessToken);
        PreConditions.checkNotNull(refreshToken);
        PreConditions.checkArgument(expiry > 0);

        setAccessToken(accessToken);
        setRefreshToken(refreshToken);
        setTokenExpiry(expiry * 1000); //convert to milliseconds
//        setTokenExpiry(expiry * 50); //convert to milliseconds


        long expiryTime = new Date().getTime() + (expiry * 1000);
//        long expiryTime = new Date().getTime() + (expiry * 50);

        setTokenExpiryTimestamp(expiryTime);

        Log.d(LiquidPayPrefs.class.getSimpleName(), "setTokenInfo: accessToken=" + accessToken);
        Log.d(LiquidPayPrefs.class.getSimpleName(), "setTokenInfo: refreshToken=" + refreshToken);
        Log.d(LiquidPayPrefs.class.getSimpleName(), "setTokenInfo: expiry=" + expiry);
        Log.d(LiquidPayPrefs.class.getSimpleName(), "setTokenInfo: expiryTimeStamp=" + expiryTime);

    }

    public synchronized void clearTokenInfo() {
        Log.d(LiquidPayPrefs.class.getSimpleName(), "clearTokenInfo");
        setAccessToken("");
        setRefreshToken("");
        setTokenExpiry(0l);
        setTokenExpiryTimestamp(0l);
    }

    private void setTokenExpiryTimestamp(long expiryTimestamp) {

        preferences.edit().putLong(KEY_TOKEN_EXPIRY_TIMESTAMP, expiryTimestamp).commit();
    }

    private void setAccessToken(String accessToken) {

        preferences.edit().putString(KEY_ACCESS_TOKEN, accessToken).commit();
    }

    public String getAccessToken() {
        return preferences.getString(KEY_ACCESS_TOKEN, "");
    }

    private void setRefreshToken(String refreshToken) {

        preferences.edit().putString(KEY_REFRESH_TOKEN, refreshToken).commit();
    }

    public String getRefreshToken() {
        return preferences.getString(KEY_REFRESH_TOKEN, "");
    }

    private void setTokenExpiry(Long expiry) {
        preferences.edit().putLong(KEY_TOKEN_EXPIRY, expiry).commit();
    }

    /**
     * Returns token expiry in milliseconds
     *
     * @return
     */
    public Long getTokenExpiry() {
        return preferences.getLong(KEY_TOKEN_EXPIRY, 0l);
    }

    /**
     * Returns token expiry time stamp (unix timestamp, epoch)
     *
     * @return
     */
    public Long getTokenExpiryTimeStamp() {
        return preferences.getLong(KEY_TOKEN_EXPIRY_TIMESTAMP, 0l);
    }
}
