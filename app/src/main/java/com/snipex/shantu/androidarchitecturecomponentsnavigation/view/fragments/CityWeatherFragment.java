package com.snipex.shantu.androidarchitecturecomponentsnavigation.view.fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.R;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.adapter.AllCityAdapter;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.City;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.helper.ClickListener;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.helper.RecyclerTouchListener;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.viewModel.CityViewModel;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CityWeatherFragment extends Fragment {

    private RecyclerView recyclerViewAllCity;
    private LinearLayoutManager layoutManager;
    private AllCityAdapter adapter;
    private CityViewModel cityViewModel;
    private List<City> cityList = new ArrayList<City>();

    public CityWeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cityViewModel = ViewModelProviders.of(this).get(CityViewModel.class);
        recyclerViewAllCity = view.findViewById(R.id.recyclerViewAllCity);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewAllCity.setLayoutManager(layoutManager);
        adapter = new AllCityAdapter(getContext());
        recyclerViewAllCity.setAdapter(adapter);

        recyclerViewAllCity.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerViewAllCity, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                City city = cityList.get(position);

                Bundle bundle = new Bundle();
                bundle.putInt("-args-city-id", city.getId());

                Navigation.findNavController(view).navigate(R.id.actionToCityWeatherDetailsFragment,bundle);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        getCityList();

    }

    private void getCityList() {
        cityViewModel.getAllCities().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
                cityList = cities;
                adapter.setCityList(cities);
            }
        });
    }
}
