package com.snipex.shantu.androidarchitecturecomponentsnavigation.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WeatherResponse {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("cod")
    @Expose
    private String code;

    @SerializedName("dt")
    @Expose
    private String dateTime;

    @SerializedName("main")
    @Expose
    private Main main;

    @SerializedName("wind")
    @Expose
    private Wind wind;

    @SerializedName("sys")
    @Expose
    private Sys sys;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public class Main {

        private String temp;

        @SerializedName("temp_min")
        @Expose
        private String tempMinimum;

        @SerializedName("temp_max")
        @Expose
        private String tempMaximum;

        private String humidity;

        private String pressure;

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getTempMinimum() {
            return tempMinimum;
        }

        public void setTempMinimum(String tempMinimum) {
            this.tempMinimum = tempMinimum;
        }

        public String getTempMaximum() {
            return tempMaximum;
        }

        public void setTempMaximum(String tempMaximum) {
            this.tempMaximum = tempMaximum;
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
    }

    public class Wind{

        private String speed;

        @SerializedName("deg")
        @Expose
        private String Degree;

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getDegree() {
            return Degree;
        }

        public void setDegree(String degree) {
            Degree = degree;
        }
    }

    public class Sys{

        private String sunrise;
        private String sunset;

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

}
