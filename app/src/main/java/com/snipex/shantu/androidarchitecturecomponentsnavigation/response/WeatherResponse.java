package com.snipex.shantu.androidarchitecturecomponentsnavigation.response;

import com.google.gson.annotations.SerializedName;

import androidx.room.PrimaryKey;

public class WeatherResponse {

    @PrimaryKey
    @SerializedName("id")
    public int id;

    @SerializedName("cod")
    private String code;

    @SerializedName("name")
    private String name;

}
