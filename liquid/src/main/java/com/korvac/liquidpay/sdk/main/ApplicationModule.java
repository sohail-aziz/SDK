package com.korvac.liquidpay.sdk.main;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.korvac.liquidpay.sdk.data.LiquidPayAPI;
import com.korvac.liquidpay.sdk.data.RepositoryImpl;
import com.korvac.liquidpay.sdk.domain.Repository;
import com.korvac.liquidpay.sdk.utils.Logger;
import com.korvac.liquidpay.sdk.utils.TimberLogger;

import javax.inject.Named;
import javax.inject.Singleton;

import auto.parcelgson.gson.AutoParcelGsonTypeAdapterFactory;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by sohail on 5/9/2017.
 */

@Module
class ApplicationModule {

    private final Context context;

    private static final String NAME_BASE_URL = "NAME_BASE_URL";

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return Constants.BASE_URL;
    }

    public ApplicationModule(Context context) {
        this.context = context.getApplicationContext();
    }


    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.context;
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient() {

        HttpLoggingInterceptor.Level LOG_LEVEL = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(LOG_LEVEL);

        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
//        return new OkHttpClient.Builder().build();

    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, @Named(NAME_BASE_URL) String baseUrl) {

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new AutoParcelGsonTypeAdapterFactory()).create();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    LiquidPayAPI provideLiquidPayAPI(Retrofit retrofit) {
        return retrofit.create(LiquidPayAPI.class);
    }

    @Provides
    @Singleton
    Repository provideRepository(RepositoryImpl repository) {
        return repository;
    }

    @Singleton
    @Provides
    Logger provideLogger() {
        return new TimberLogger();
    }

    @Singleton
    @Provides
    SharedPreferences providePreferences(Context context) {
        return context.getSharedPreferences("prefs_liquid_pay", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    LiquidDateFormatter provideDateFormatter(JodaDateFormatter jodaDateFormatter) {

        return jodaDateFormatter;
    }

}
