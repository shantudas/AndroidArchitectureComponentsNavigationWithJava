package com.snipex.shantu.androidarchitecturecomponentsnavigation.viewModel;

import android.app.Application;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.constant.ApiConstants;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.model.Weather;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.repository.WeatherRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class WeatherViewModel extends AndroidViewModel {

    private static final String TAG = WeatherViewModel.class.getSimpleName();
    private WeatherRepository repository;
    private LiveData<Weather> weatherResponseLiveData;

    public WeatherViewModel(@NonNull Application application) {
        super(application);

        repository = new WeatherRepository();
        weatherResponseLiveData = repository.getWeatherdata(ApiConstants.APP_ID, "1188909");
    }

    public LiveData<Weather> getWeatherResponseLiveData() {
        return weatherResponseLiveData;
    }
}
