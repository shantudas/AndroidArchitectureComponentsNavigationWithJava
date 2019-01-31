package com.snipex.shantu.androidarchitecturecomponentsnavigation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.R;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.City;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.Weather;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.databinding.LayoutEachPagerBinding;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.viewModel.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class PagerSliderAdapter extends PagerAdapter {

    private static final String TAG = PagerSliderAdapter.class.getSimpleName();
    private List<City> cityList = new ArrayList<City>();
    private List<Weather> weatherList = new ArrayList<Weather>();
    private Context context;
    LayoutEachPagerBinding binding;
    private LayoutInflater layoutInflater;
    private WeatherViewModel weatherViewModel;



    public PagerSliderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(container.getContext());
        }


        LayoutEachPagerBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_each_pager, container, false);

       /* View myPagerLayout = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_each_pager, container, false);

        City city = cityList.get(position);

        TextView tvCityName = myPagerLayout.findViewById(R.id.tvCityName);
        tvCityName.setText(city.getName());

        container.addView(myPagerLayout, 0);
        return myPagerLayout;*/

        City city = cityList.get(position);
        binding.setCity(city);

        Weather weather=weatherList.get(position);
        binding.setWeather(weather);

        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(View collection, int position, Object view) {
        ((ViewPager) collection).removeView((View) view);
    }


    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    public void setPager(List<City> cities) {
        this.cityList = cities;
        notifyDataSetChanged();
    }

    public void setWeather(List<Weather> weatherList){
        this.weatherList=weatherList;
        notifyDataSetChanged();
    }
}
