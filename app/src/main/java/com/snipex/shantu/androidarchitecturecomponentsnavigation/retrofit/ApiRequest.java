package com.snipex.shantu.androidarchitecturecomponentsnavigation.retrofit;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("data/2.5/weather/")
    Call<Weather> getWatherData(
            @Query("APPID") String app_id,
            @Query("id") String id
    );
}
