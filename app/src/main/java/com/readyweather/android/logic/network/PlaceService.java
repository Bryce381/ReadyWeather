package com.readyweather.android.logic.network;

import com.readyweather.android.ReadyWeatherApplcation;
import com.readyweather.android.logic.model.PlaceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceService {
    @GET("v2/place?token=" + ReadyWeatherApplcation.TOKEN + "&lang=zh_CN")
    Call<PlaceResponse> searchPlaces(@Query("query") String query);
}
