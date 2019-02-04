package com.snipex.shantu.androidarchitecturecomponentsnavigation.viewModel;

import android.app.Application;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.constant.ApiConstants;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.Weather;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.response.WeatherResponse;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.repository.WeatherRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class WeatherViewModel extends AndroidViewModel {

    private static final String TAG = WeatherViewModel.class.getSimpleName();
    private WeatherRepository repository;
    private LiveData<WeatherResponse> weatherResponseLiveData;

    private LiveData<List<Weather>> weatherListLiveData;

    public WeatherViewModel(@NonNull Application application) {
        super(application);

        repository = new WeatherRepository(application);
        weatherResponseLiveData = repository.getWeatherdata(ApiConstants.APP_ID, "1185099");

        weatherListLiveData=repository.getWeatherListLiveData();
    }

    public LiveData<List<Weather>> getWeatherListLiveData() {
        return weatherListLiveData;
    }

    public LiveData<WeatherResponse> getWeatherResponseLiveData() {
        return weatherResponseLiveData;
    }


    public void insert(Weather weather){
        repository.insert(weather);
    }
}
