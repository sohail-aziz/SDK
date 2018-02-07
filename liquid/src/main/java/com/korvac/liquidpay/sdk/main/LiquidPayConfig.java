package com.korvac.liquidpay.sdk.main;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Configuration class for all the app, device related configuration
 * e.g device Id, Model, type
 * Created by sohail on 5/5/2017.
 */

public final class LiquidPayConfig {

    private static final String DEVICE_TYPE_ANDROID = "A";
    private static final String DEVICE_OS_ANDROID = "android";


    public final String apiKey;
    public final String apiSecret;
    public final DeviceInfo deviceInfo;

    public String state;
    public String responseType;
    public String redirectUri;


    /**
     * @param context
     * @param apiKey
     * @param deviceId Unique Id for push notifications
     */
    public LiquidPayConfig(@NonNull Context context, @NonNull String apiKey, @NonNull String apiSecret, @Nullable String deviceId) {
        Log.d("LiquidPayConfig", "LiquidPayConfig.init");
        if (context == null || apiKey == null || apiSecret == null) {
            throw new IllegalArgumentException("init: context, apiKey or secret cannot be null");
        }
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        String deviceUID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        final String deviceIdStr = (deviceId != null) ? deviceId : "";
        deviceInfo = new DeviceInfo(DEVICE_TYPE_ANDROID, deviceIdStr, deviceUID, Build.MODEL, Build.VERSION.RELEASE);

    }

    public LiquidPayConfig(@NonNull Context context, @NonNull String apiKey, @NonNull String apiSecret, @Nullable String deviceId, String state, String responseType, String redirectUri) {
        Log.d("LiquidPayConfig", "LiquidPayConfig.init");
        if (context == null || apiKey == null || apiSecret == null) {
            throw new IllegalArgumentException("init: context, apiKey or secret cannot be null");
        }
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        String deviceUID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        final String deviceIdStr = (deviceId != null) ? deviceId : "";
        deviceInfo = new DeviceInfo(DEVICE_TYPE_ANDROID, deviceIdStr, deviceUID, Build.MODEL, Build.VERSION.RELEASE);


        //
        this.state = state;
        this.responseType = responseType;
        this.redirectUri = redirectUri;

    }

    /**
     * Class holding basic device info
     */
    public static class DeviceInfo {

        // String deviceId,
        public final String deviceType, gcmId, deviceUID, deviceModel, deviceOS;

        public DeviceInfo(String deviceType, String deviceId, String deviceUID, String deviceModel, String deviceOS) {
            this.deviceType = deviceType;
            this.gcmId = deviceId;
            this.deviceUID = deviceUID;
            this.deviceModel = deviceModel;
            this.deviceOS = deviceOS;


        }
    }

}
