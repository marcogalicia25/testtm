package com.mswim.general.utils;

import com.mswim.general.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by mgalicia on 2/21/17.
 */

public class APIRetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            String authToken = Credentials.basic(BuildConfig.TOKEN, "X");
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);
            if (BuildConfig.DEBUG)
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            else logging.setLevel(HttpLoggingInterceptor.Level.NONE);

            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.HOST)
                    .client(okHttpClient)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
