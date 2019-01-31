package com.snipex.shantu.androidarchitecturecomponentsnavigation.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM weather")
    LiveData<List<Weather>> getWeather();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Weather weather);
}
