package com.readyweather.android.logic.network;


import com.readyweather.android.logic.model.PlaceResponse;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadyWeatherNetwork {
    static PlaceService placeService = ServiceCreator.create(PlaceService.class);

    public static CompletableFuture<PlaceResponse> searchPlaces(String query) {
        CompletableFuture<PlaceResponse> future = new CompletableFuture<>();

        placeService.searchPlaces(query).enqueue(new Callback<PlaceResponse>() {
            @Override
            public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
                if (response.body() != null) {
                    future.complete(response.body());
                } else {
                    future.completeExceptionally(new RuntimeException("response body is null"));
                }
            }

            @Override
            public void onFailure(Call<PlaceResponse> call, Throwable t) {
                future.completeExceptionally(t);
            }
        });

        return future;
    }
}
