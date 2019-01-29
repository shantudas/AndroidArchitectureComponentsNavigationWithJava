package com.snipex.shantu.androidarchitecturecomponentsnavigation.retrofit;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.constant.ApiConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {
    private static Retrofit retrofit;
    private static RetrofitRequest mInstance;
    private static final String BASE_URL = ApiConstants.API_BASE_URL;

    private RetrofitRequest() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitRequest getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitRequest();
        }
        return mInstance;
    }

    public ApiRequest getApi() {
        return retrofit.create(ApiRequest.class);
    }
}
