package com.snipex.shantu.androidarchitecturecomponentsnavigation.view.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageCityFragment extends Fragment {


    private FloatingActionButton fabAddCity;

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
    }
}
