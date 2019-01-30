package com.snipex.shantu.androidarchitecturecomponentsnavigation.repository;

import android.util.Log;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.model.Weather;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.retrofit.ApiRequest;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.retrofit.RetrofitRequest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private static final String TAG = WeatherRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public WeatherRepository() {
        apiRequest = RetrofitRequest.getInstance().getApi();
    }

    public LiveData<Weather> getWeatherdata(String app_id, String id) {
        final MutableLiveData<Weather> data = new MutableLiveData<>();

        apiRequest.getWatherData(app_id, id).enqueue(new Callback<Weather>() {

            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });

        return data;
    }
}
