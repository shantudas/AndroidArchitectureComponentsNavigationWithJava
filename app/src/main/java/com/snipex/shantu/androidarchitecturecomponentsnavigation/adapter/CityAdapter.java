package com.snipex.shantu.androidarchitecturecomponentsnavigation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.R;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.City;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private List<City> cityArrayList = new ArrayList<City>();


    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_each_row_add_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position) {
        City city = cityArrayList.get(position);
        holder.tvCityName.setText(city.getName());
    }

    @Override
    public int getItemCount() {
        return cityArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvCityName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCityName = (TextView) itemView.findViewById(R.id.tvCityName);
        }
    }

    public void setCityList(List<City> cities) {
        this.cityArrayList = cities;
        notifyDataSetChanged();
    }


}
