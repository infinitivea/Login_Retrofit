package com.example.login;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "https://equiploansystem.000webhostapp.com/API/";
    private static RetrofitClient mInstance;
    private static Retrofit retrofit = null;

    public RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public RetrofitApi getApi() {
        return retrofit.create(RetrofitApi.class);
    }
}
