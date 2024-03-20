package com.readyweather.android.ui.place;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.readyweather.android.logic.Result;
import com.readyweather.android.logic.model.Place;
import com.readyweather.android.logic.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PlaceViewModel extends ViewModel {
    private MutableLiveData<String> searchLiveData = new MutableLiveData<>();
    private List<Place> placeList = new ArrayList<>();
    private LiveData<Result<List<Place>>> placeLiveData;

    public PlaceViewModel() {
        placeLiveData = Transformations.switchMap(searchLiveData, query -> Repository.getInstance().searchPlaces(query));
    }

    public void searchPlaces(String query) {
        searchLiveData.setValue(query);
    }

    public LiveData<Result<List<Place>>> getPlaceLiveData() {
        return placeLiveData;
    }

    public List<Place> getPlaceList() {
        return placeList;
    }
}

