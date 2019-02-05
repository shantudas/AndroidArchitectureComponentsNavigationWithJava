package com.snipex.shantu.androidarchitecturecomponentsnavigation.database;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Weather {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "city_id")
    private int cityId;

    @ColumnInfo(name = "city_name")
    private String cityName;

    @ColumnInfo(name = "temp")
    private String temperature;

    @ColumnInfo(name = "temp_min")
    private String temperatureMinimum;

    @ColumnInfo(name = "temp_max")
    private String temperatureMaximum;

    @ColumnInfo(name = "humidity")
    private String humidity;

    @ColumnInfo(name = "pressure")
    private String pressure;

    @ColumnInfo(name = "wind_speed")
    private String windSpeed;

    @ColumnInfo(name = "wind_degree")
    private String windDegree;

    @ColumnInfo(name = "sunrise")
    private String sunrise;

    @ColumnInfo(name = "sunset")
    private String sunset;


    public Weather() {

    }

    public Weather(int cityId,String cityName, String temperature, String temperatureMinimum, String temperatureMaximum,
                   String humidity,
                   String pressure, String windSpeed, String windDegree, String sunrise, String sunset) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.temperature = temperature;
        this.temperatureMinimum = temperatureMinimum;
        this.temperatureMaximum = temperatureMaximum;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDegree = windDegree;
        this.sunrise = sunrise;
        this.sunset = sunset;

    }


    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperatureMinimum() {
        return temperatureMinimum;
    }

    public void setTemperatureMinimum(String temperatureMinimum) {
        this.temperatureMinimum = temperatureMinimum;
    }

    public String getTemperatureMaximum() {
        return temperatureMaximum;
    }

    public void setTemperatureMaximum(String temperatureMaximum) {
        this.temperatureMaximum = temperatureMaximum;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(String windDegree) {
        this.windDegree = windDegree;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
