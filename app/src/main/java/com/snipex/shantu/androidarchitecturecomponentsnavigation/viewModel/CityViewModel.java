package com.snipex.shantu.androidarchitecturecomponentsnavigation.viewModel;

import android.app.Application;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.City;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.repository.CityRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CityViewModel extends AndroidViewModel {

    private CityRepository cityRepository;
    private LiveData<List<City>> allCities;
    private LiveData<List<City>> allMyCities;


    public CityViewModel(@NonNull Application application) {
        super(application);
        cityRepository = new CityRepository(application);
        this.allCities = cityRepository.getAllCities();
        this.allMyCities=cityRepository.getMyCities();
    }

    public LiveData<List<City>> getAllCities() {
        return allCities;
    }

    public void insert(City city) {
        cityRepository.insert(city);
    }

    public void update(City city) {
        cityRepository.update(city);
    }

    public LiveData<List<City>> getMyCities(){
        return allMyCities;
    }
}
