package com.snipex.shantu.androidarchitecturecomponentsnavigation.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.Weather;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.WeatherDao;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.WeatherDatabase;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.response.WeatherResponse;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.retrofit.ApiRequest;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.retrofit.RetrofitRequest;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private static final String TAG = WeatherRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    private LiveData<List<Weather>> weatherListLiveData;
    private WeatherDao weatherDao;

    public WeatherRepository(Application application) {
        apiRequest = RetrofitRequest.getInstance().getApi();

        WeatherDatabase db = WeatherDatabase.getDatabase(application);

        weatherDao=db.weatherDao();
        weatherListLiveData=weatherDao.getWeather();
    }

    public LiveData<List<Weather>> getWeatherListLiveData() {
        return weatherListLiveData;
    }

    public LiveData<WeatherResponse> getWeatherdata(String app_id, String id) {
        final MutableLiveData<WeatherResponse> data = new MutableLiveData<>();

        apiRequest.getWeatherData(app_id, id).enqueue(new Callback<WeatherResponse>() {

            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });

        return data;
    }

    public void insert(Weather weather) {
        new insertAsyncTask(weatherDao).execute(weather);
    }

    private static class insertAsyncTask extends AsyncTask<Weather, Void, Void> {

        private WeatherDao mAsyncTaskDao;

        private insertAsyncTask(WeatherDao dao) {
            this.mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Weather... weathers) {
            mAsyncTaskDao.insert(weathers[0]);
            Log.d(TAG, "doInBackground: weather data inserted");
            return null;
        }
    }
}
