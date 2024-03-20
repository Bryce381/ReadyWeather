package com.readyweather.android.ui.place;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readyweather.R;
import com.readyweather.android.logic.model.Place;

import java.util.List;

public class PlaceFragment extends Fragment {
    private PlaceViewModel viewModel;
    private PlaceAdapter adapter;
    private RecyclerView recyclerView;
    private TextView searchPlaceEdit;
    private ImageView bgImageView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_place, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PlaceAdapter(this, viewModel.getPlaceList());
        recyclerView.setAdapter(adapter);
        searchPlaceEdit = getView().findViewById(R.id.searchPlaceEdit);
        bgImageView = getView().findViewById(R.id.bgImageView);
        searchPlaceEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = s.toString();
                if (!content.isEmpty()) {
                    viewModel.searchPlaces(content);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    bgImageView.setVisibility(View.VISIBLE);
                    viewModel.getPlaceList().clear();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        viewModel.getPlaceLiveData().observe(getViewLifecycleOwner(), result -> {
            List<Place> places = result.getOrNull();
            if (places != null) {
                recyclerView.setVisibility(View.VISIBLE);
                bgImageView.setVisibility(View.GONE);
                viewModel.getPlaceList().clear();
                viewModel.getPlaceList().addAll(places);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getActivity(), "未能查询到任何地点", Toast.LENGTH_SHORT).show();
                if (result.exceptionOrNull() != null) {
                    result.exceptionOrNull().printStackTrace();
                }
            }
        });
    }
}
