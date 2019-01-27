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

    @ColumnInfo(name = "my_city")
    private boolean myCity;

    public City(int id, String name, String latitude, String longitude, boolean myCity) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.myCity = myCity;
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

    public boolean isMyCity() {
        return myCity;
    }

    public void setMyCity(boolean myCity) {
        this.myCity = myCity;
    }

    public static City[] populateData() {
        return new City[]{
                new City(1188909, "Dhaka", "23.8103", "90.4125", false),
                new City(1185128, "Rajshahi", "24.3636", "88.6241", false),
                new City(1185099, "Sylhet", "24.8949", "91.8687", false),
                new City(6413609, "Chattogram", "22.3569", "91.7832", false),
                new City(1336135, "Khulna", "22.8456", "89.5403", false),
                new City(1336137, "Barisal", "22.7010", "90.3535", false),
                new City(1185162, "Mymensingh", "24.7471", "90.4203", false),
                new City(7671048, "Rangpur", "25.8483", "88.9414", false),
        };
    }
}
