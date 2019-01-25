package com.snipex.shantu.androidarchitecturecomponentsnavigation.view.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.R;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.adapter.CityAdapter;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.City;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.helper.ClickListener;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.helper.RecyclerTouchListener;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.viewModel.CityViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddCityFragment extends Fragment {

    private static final String TAG = AddCityFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private CityViewModel viewModel;
    private LiveData<List<City>> citiesLiveData;
    private CityAdapter adapter;

    public AddCityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        viewModel = ViewModelProviders.of(this).get(CityViewModel.class);
        recyclerView = view.findViewById(R.id.recyclerViewCity);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new CityAdapter();
        recyclerView.setAdapter(adapter);

        onItemClickListener();

        getCityList();
    }

    private void onItemClickListener() {
        recyclerView.addOnItemTouchListener( new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.e(TAG, "onClick: position"+position);
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.e(TAG, "onLongClick: position"+position);
            }
        }));
    }

    private void getCityList() {
        citiesLiveData = viewModel.getAllCities();
        citiesLiveData.observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
                Log.d(TAG, "onChanged: " + cities.size());
                adapter.setCityList(cities);
            }
        });
    }
}
