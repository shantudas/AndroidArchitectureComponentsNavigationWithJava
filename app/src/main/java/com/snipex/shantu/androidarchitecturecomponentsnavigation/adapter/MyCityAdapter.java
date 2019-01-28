package com.snipex.shantu.androidarchitecturecomponentsnavigation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.R;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.City;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MyCityAdapter extends RecyclerView.Adapter<MyCityAdapter.ViewHolder> {

    private List<City> cityList = new ArrayList<City>();
    private Context context;

    public MyCityAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyCityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_each_row_add_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCityAdapter.ViewHolder holder, int position) {
        City city = cityList.get(position);

        holder.tvCityName.setText(city.getName());
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCityName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCityName = (TextView) itemView.findViewById(R.id.tvCityName);
        }
    }

    public void setCityList(List<City> cities) {
        this.cityList = cities;
        notifyDataSetChanged();
    }
}
