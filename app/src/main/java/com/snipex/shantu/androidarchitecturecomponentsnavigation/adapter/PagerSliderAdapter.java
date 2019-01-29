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
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class PagerSliderAdapter extends PagerAdapter {

    private List<City> cityList = new ArrayList<City>();
    private Context context;

    public PagerSliderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View myPagerLayout = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_each_pager, container, false);
        City city=cityList.get(position);

        TextView tvCityName = (TextView) myPagerLayout.findViewById(R.id.tvCityName);
        tvCityName.setText(city.getName());

        container.addView(myPagerLayout, 0);
        return myPagerLayout;
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
}
