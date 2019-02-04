package com.snipex.shantu.androidarchitecturecomponentsnavigation.view.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.R;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.adapter.PagerSliderAdapter;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.City;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.Weather;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.response.WeatherResponse;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.viewModel.CityViewModel;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.viewModel.WeatherViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private static final String TAG = HomeFragment.class.getSimpleName();
    private CityViewModel viewModel;
    private LiveData<List<City>> citiesLiveData;
    private ViewPager pager_slider;
    private PagerSliderAdapter adapter;
    private int dotsCount;
    private ImageView[] dots;
    private LinearLayout ll_pager_dots;


    private WeatherViewModel weatherViewModel;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        viewModel = ViewModelProviders.of(this).get(CityViewModel.class);
        pager_slider = view.findViewById(R.id.pager_slider);
        adapter = new PagerSliderAdapter(getContext());
        pager_slider.setAdapter(adapter);
        ll_pager_dots = view.findViewById(R.id.ll_pager_dots);
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);


        viewModel.getMyCities().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
                Log.d(TAG, "onChanged: cities Size " + cities.size());
                if (cities.size() > 0) {
                    getSliderDots(cities);
                    getCitiesWeather(cities);
                }
            }
        });


//        getWeatherDataFromServer();

        getWeatherDataFromDB();

    }


    private void getWeatherDataFromDB() {
        weatherViewModel.getWeatherListLiveData().observe(this, new Observer<List<Weather>>() {
            @Override
            public void onChanged(List<Weather> weathers) {
                Log.d(TAG, "onChanged: weather size" + weathers.size());
                if (weathers.size() > 0) {
                    adapter.setWeather(weathers);
                }
            }
        });
    }


    /**
     * get pager slider dots
     *
     * @param cities
     */
    private void getSliderDots(List<City> cities) {
        dotsCount = cities.size();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {

            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            ll_pager_dots.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

        pager_slider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    /**
     * get weather data from server and store to room database
     *
     * @param @null
     */
    /*private void getWeatherDataFromServer() {
        weatherViewModel.getWeatherResponseLiveData(cityID).observe(this, new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weatherResponse) {
                if (weatherResponse != null) {
                    Log.d(TAG, "onChanged: weatherResponse data " +
                            " id " + weatherResponse.getId() +
                            " code " + weatherResponse.getCode() +
                            " date Time " + weatherResponse.getDateTime() +
                            " temp " + weatherResponse.getMain().getTemp() +
                            " humidity " + weatherResponse.getMain().getHumidity() +
                            " pressure " + weatherResponse.getMain().getPressure() +
                            " temp maximum " + weatherResponse.getMain().getTempMaximum() +
                            " temp minimum " + weatherResponse.getMain().getTempMinimum() +
                            " wind speed " + weatherResponse.getWind().getSpeed() +
                            " wind degree " + weatherResponse.getWind().getDegree() +
                            " sunrise " + weatherResponse.getSys().getSunrise() +
                            " sunset " + weatherResponse.getSys().getSunset())
                    ;
                    insertWeatherDataToDB(
                            weatherResponse.getId(),
                            weatherResponse.getCityName(),
                            weatherResponse.getMain().getTemp(),
                            weatherResponse.getMain().getTempMinimum(),
                            weatherResponse.getMain().getTempMaximum(),
                            weatherResponse.getMain().getHumidity(),
                            weatherResponse.getMain().getPressure(),
                            weatherResponse.getWind().getSpeed(),
                            weatherResponse.getWind().getDegree(),
                            weatherResponse.getSys().getSunrise(),
                            weatherResponse.getSys().getSunset()
                    );

                }
            }
        });
    }*/

    /**
     * store into room database
     *
     * @param
     */
    private void insertWeatherDataToDB(int id, String cityName, String temp, String tempMinimum, String tempMaximum, String humidity, String pressure, String speed, String degree, String sunrise, String sunset) {

        Weather weather = new Weather(id, cityName, temp, tempMinimum, tempMaximum, humidity, pressure, speed, degree, sunrise, sunset);
        weatherViewModel.insert(weather);

        Log.d(TAG, "insertWeatherDataToDB: id " + id + " name " + cityName);

    }


    /**
     * for each cities get weather data from server
     * Note :: try to call this from work manager
     *
     * @param cities
     */
    private void getCitiesWeather(List<City> cities) {
        for (int i = 0; i < cities.size(); i++) {
            Log.d(TAG, "getCitiesWeather: cities " + cities.get(i).getId() + " " + cities.get(i).getName());
            getCitiesWeatherData(cities.get(i).getId());
        }
    }

    private void getCitiesWeatherData(int cityID) {
        weatherViewModel.getWeatherResponseLiveData(cityID).observe(this, new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weatherResponse) {
                if (weatherResponse != null) {
                    Log.d(TAG, "onChanged: weatherResponse data" +
                            "id " + weatherResponse.getId() +
                            " code " + weatherResponse.getCode() +
                            " date Time " + weatherResponse.getDateTime() +
                            " temp " + weatherResponse.getMain().getTemp() +
                            " humidity " + weatherResponse.getMain().getHumidity() +
                            " pressure " + weatherResponse.getMain().getPressure() +
                            " temp maximum " + weatherResponse.getMain().getTempMaximum() +
                            " temp minimum " + weatherResponse.getMain().getTempMinimum() +
                            " wind speed " + weatherResponse.getWind().getSpeed() +
                            " wind degree " + weatherResponse.getWind().getDegree() +
                            " sunrise " + weatherResponse.getSys().getSunrise() +
                            " sunset " + weatherResponse.getSys().getSunset())
                    ;
                    insertWeatherDataToDB(
                            weatherResponse.getId(),
                            weatherResponse.getCityName(),
                            weatherResponse.getMain().getTemp(),
                            weatherResponse.getMain().getTempMinimum(),
                            weatherResponse.getMain().getTempMaximum(),
                            weatherResponse.getMain().getHumidity(),
                            weatherResponse.getMain().getPressure(),
                            weatherResponse.getWind().getSpeed(),
                            weatherResponse.getWind().getDegree(),
                            weatherResponse.getSys().getSunrise(),
                            weatherResponse.getSys().getSunset()
                    );

                }
            }
        });

    }
}
