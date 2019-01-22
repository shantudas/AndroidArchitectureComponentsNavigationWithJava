package com.snipex.shantu.androidarchitecturecomponentsnavigation.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class City {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "lat")
    private String latitude;

    @ColumnInfo(name = "lon")
    private String longitude;


    public City(int id, String name, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public static City[] populateData() {
        return new City[] {
                new City(1185111,"Satkhira","89.099998","22.716669"),
                new City(1185958,"Tulāpāra","89.23333","24.51667"),
                new City(1185099,"Sylhet","91.871674","24.51667"),
                new City(1185116,"Netrakona","90.716667","24.883329"),
        };
    }
}
