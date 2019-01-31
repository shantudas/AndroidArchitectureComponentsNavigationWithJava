package com.snipex.shantu.androidarchitecturecomponentsnavigation.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {City.class,Weather.class}, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {

    private static final String DB_NAME = "weather_database";

    public abstract CityDao cityDao();
    public abstract WeatherDao weatherDao();

    private static WeatherDatabase instance;

    public static WeatherDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (WeatherDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            WeatherDatabase.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallBack)
                            .build();
                }
            }
        }
        return instance;
    }

    /**
     * room call back
     */
    private static WeatherDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateNoteData(instance).execute();
        }
    };

    /**
     * populate data for room callback
     */
    private static class populateNoteData extends AsyncTask<Void, Void, Void> {

        private CityDao cityDao;

        private populateNoteData(WeatherDatabase db) {
            cityDao = db.cityDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cityDao.insert(City.populateData());
            return null;
        }
    }
}
