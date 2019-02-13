package com.snipex.shantu.androidarchitecturecomponentsnavigation.view.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.R;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.adapter.AllCityAdapter;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.City;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.helper.ClickListener;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.helper.RecyclerTouchListener;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.viewModel.CityViewModel;

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
 * CityWeatherFragment is for getting city list item and,
 * on each item clicked send to CityWeatherDetailsFragment
 *
 * @author shantu
 */
public class CityWeatherFragment extends Fragment {

    private RecyclerView recyclerViewAllCity;
    private AllCityAdapter adapter;
    private CityViewModel cityViewModel;
    private List<City> cityList = new ArrayList<>();

    /**
     * empty constructor
     */
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewAllCity.setLayoutManager(layoutManager);
        adapter = new AllCityAdapter(getContext());
        recyclerViewAllCity.setAdapter(adapter);

        getCityList();  //  get city list from db

        cityListItemOnClicked();    //  city list item on item clicked

    }


    /**
     * City list item on clicked send user to CityWeatherDetailsFragment
     * using navigation graph action
     * passing city id through bundle
     */
    private void cityListItemOnClicked() {
        recyclerViewAllCity.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerViewAllCity, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                City city = cityList.get(position); // get on clicked city

                Bundle bundle = new Bundle();
                bundle.putInt("argsCityId", city.getId());

                Navigation.findNavController(view).navigate(R.id.actionToCityWeatherDetailsFragment, bundle);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    /**
     * getting city list from database
     * set adapter for recycler view
     */
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
