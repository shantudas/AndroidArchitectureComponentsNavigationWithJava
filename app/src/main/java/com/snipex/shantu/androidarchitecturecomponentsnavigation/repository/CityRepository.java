package com.snipex.shantu.androidarchitecturecomponentsnavigation.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.City;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.CityDao;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.WeatherDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;


public class CityRepository {

    private CityDao cityDao;
    private LiveData<List<City>> allCities;


    public CityRepository(Application application) {
        WeatherDatabase db = WeatherDatabase.getDatabase(application);
        cityDao = db.cityDao();
        allCities = cityDao.getAllCities();
    }

    public LiveData<List<City>> getAllCities() {
        return allCities;
    }

    public void insert(City city) {
        new insertAsyncTask(cityDao).execute(city);
    }

    private static class insertAsyncTask extends AsyncTask<City, Void, Void> {

        private CityDao mAsyncTaskDao;

        private insertAsyncTask(CityDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(City... cities) {
            mAsyncTaskDao.insert(cities[0]);
            return null;
        }
    }

    public void update(City city) {
        new updateAsyncTask(cityDao).execute(city);
    }

    private static class updateAsyncTask extends AsyncTask<City, Void, Void> {

        private CityDao mAsyncTaskDao;

        private updateAsyncTask(CityDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(City... cities) {
            mAsyncTaskDao.update(cities[0]);
            return null;
        }
    }



}
