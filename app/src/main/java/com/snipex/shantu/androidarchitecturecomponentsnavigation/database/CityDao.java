package com.snipex.shantu.androidarchitecturecomponentsnavigation.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CityDao {

    @Query("SELECT * FROM city")
    LiveData<List<City>> getAllCities();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(City... cities);

    @Update
    void update(City city);

    @Query("SELECT * FROM city WHERE id = :itemId")
    LiveData<City> getCityItemById(int itemId);

    @Query("SELECT * FROM city Where my_city=1")
    LiveData<List<City>> getMyCities();
}
