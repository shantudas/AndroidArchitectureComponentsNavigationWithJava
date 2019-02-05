package com.snipex.shantu.androidarchitecturecomponentsnavigation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.R;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.City;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.databinding.ListEachRowAllCityBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class AllCityAdapter extends RecyclerView.Adapter<AllCityAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<City> cityList = new ArrayList<City>();

    public AllCityAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ListEachRowAllCityBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_each_row_all_city, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City city = cityList.get(position);
        holder.binding.setCity(city);
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public void setCityList(List<City> cities) {
        this.cityList = cities;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ListEachRowAllCityBinding binding;

        public ViewHolder(@NonNull ListEachRowAllCityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
