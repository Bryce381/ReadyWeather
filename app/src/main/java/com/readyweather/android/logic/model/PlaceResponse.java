package com.readyweather.android.logic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceResponse {
    private String status;
    private List<Place> places;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
    // getters and setters
}

