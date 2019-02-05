package com.snipex.shantu.androidarchitecturecomponentsnavigation.view.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.R;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.adapter.MyCityAdapter;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.City;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.Weather;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.viewModel.CityViewModel;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.viewModel.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageCityFragment extends Fragment {


    private FloatingActionButton fabAddCity;
    private CityViewModel viewModel;
    private WeatherViewModel weatherViewModel;
    private LiveData<List<City>> citiesLiveData;
    private MyCityAdapter adapter;
    private static String TAG = ManageCityFragment.class.getSimpleName();
    private RecyclerView recyclerViewMyCity;
    private List<City> cityArrayList = new ArrayList<City>();


    public ManageCityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fabAddCity = view.findViewById(R.id.fabAddCity);
        fabAddCity.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.toAddCity, null));

        recyclerViewMyCity = view.findViewById(R.id.recyclerViewMyCity);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewMyCity.setLayoutManager(manager);
        adapter = new MyCityAdapter(getActivity());
        recyclerViewMyCity.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(CityViewModel.class);
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        getMyCities();


        // For swipe delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                City city = cityArrayList.get(viewHolder.getAdapterPosition());
                Log.d(TAG, "onSwiped: city name " + city.getName()+ " "+city.getId());

                city.setMyCity(false);
                viewModel.update(city);

                getWeatherItem(city.getId());


                Toast.makeText(getActivity(), city.getName() + " deleted as my city", Toast.LENGTH_LONG).show();

//                getMyCities();

            }
        }).attachToRecyclerView(recyclerViewMyCity);


    }

    private void getWeatherItem(int id) {
        Log.d(TAG, "getWeatherItem: id " + id);
        weatherViewModel.getWeatherItem(id).observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                weatherViewModel.delete(weather);
            }
        });
    }

    private void getMyCities() {
        viewModel.getMyCities().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
                adapter.setCityList(cities);
                cityArrayList = cities;
                Log.e(TAG, "onChanged: " + cities.size());
            }
        });
    }
}
