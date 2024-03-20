package com.readyweather.android.ui.place;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readyweather.R;
import com.readyweather.android.logic.model.Place;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    private Fragment fragment;
    private List<Place> placeList;

    public PlaceAdapter(Fragment fragment, List<Place> placeList) {
        this.fragment = fragment;
        this.placeList = placeList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView placeName;
        TextView placeAddress;

        public ViewHolder(View view) {
            super(view);
            placeName = view.findViewById(R.id.placeName);
            placeAddress = view.findViewById(R.id.placeAddress);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Place place = placeList.get(position);
        holder.placeName.setText(place.getName());
        holder.placeAddress.setText(place.getAddress());
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }
}