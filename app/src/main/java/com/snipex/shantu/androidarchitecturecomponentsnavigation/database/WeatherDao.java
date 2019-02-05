package com.snipex.shantu.androidarchitecturecomponentsnavigation.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM weather")
    LiveData<List<Weather>> getWeather();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Weather weather);

    @Delete
    void delete(Weather weather);

    @Query("SELECT * FROM weather WHERE city_id = :itemId")
    LiveData<Weather> getWeatherItemById(int itemId);
}
