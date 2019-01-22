package com.snipex.shantu.androidarchitecturecomponentsnavigation.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CityDao {
    @Query("SELECT * FROM city")
    LiveData<List<City>> getAllCities();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(City... cities);
}
