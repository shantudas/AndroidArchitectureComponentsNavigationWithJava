package com.snipex.shantu.androidarchitecturecomponentsnavigation.view.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.R;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.databinding.FragmentCityWeatherDetailsBinding;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.response.WeatherResponse;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.viewModel.WeatherViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class CityWeatherDetailsFragment extends Fragment {


    private static final String TAG=CityWeatherDetailsFragment.class.getSimpleName();
    WeatherViewModel viewModel;
    FragmentCityWeatherDetailsBinding binding;

    public CityWeatherDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_city_weather_details, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_city_weather_details, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel= ViewModelProviders.of(this).get(WeatherViewModel.class);
        int cityId=getArguments().getInt("argsCityId");
        Log.d(TAG, "onViewCreated: "+cityId);

        getWeatherInfo(cityId);


    }

    private void getWeatherInfo(int cityId) {
        viewModel.getWeatherResponseLiveData(cityId).observe(this, new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weatherResponse) {
                Log.d(TAG, "onChanged: cityName : "+weatherResponse.getCityName());
//                tvCityName.setText(weatherResponse.getCityName());

binding.setWeatherResponse(weatherResponse);

            }
        });
    }
}
