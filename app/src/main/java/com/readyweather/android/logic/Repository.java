package com.readyweather.android.logic;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.readyweather.android.logic.model.Place;
import com.readyweather.android.logic.model.PlaceResponse;
import com.readyweather.android.logic.network.ReadyWeatherNetwork;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Repository {
    private static Repository instance;

    private Repository() {
        // Private constructor to prevent instantiation
    }

    public static synchronized Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public LiveData<Result<List<Place>>> searchPlaces(String query) {
        MutableLiveData<Result<List<Place>>> liveData = new MutableLiveData<>();
        CompletableFuture.runAsync(() -> {
            try {
                PlaceResponse placeResponse = ReadyWeatherNetwork.searchPlaces(query).get();
                if ("ok".equals(placeResponse.getStatus())) {
                    List<Place> places = placeResponse.getPlaces();
                    liveData.postValue(Result.success(places));
                } else {
                    liveData.postValue(Result.failure(new RuntimeException("response status is " + placeResponse.getStatus())));
                }
            } catch (Exception e) {
                liveData.postValue(Result.failure(e));
            }
        });
        return liveData;
    }
}

