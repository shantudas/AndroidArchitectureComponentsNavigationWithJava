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
import com.snipex.shantu.androidarchitecturecomponentsnavigation.helper.Toolbar_ActionMode_Callback;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.viewModel.CityViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
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
    private ActionMode mActionMode;

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
        adapter = new CityAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        onItemClickListener();

        getCityList();
    }

    private void onItemClickListener() {
        recyclerView.addOnItemTouchListener( new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.e(TAG, "onClick: position"+position);
                //If ActionMode not null select item
                if (mActionMode != null)
                    onListItemSelect(position);
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.e(TAG, "onLongClick: position"+position);
                onListItemSelect(position);
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

    private void onListItemSelect(int position) {
        adapter.toggleSelection(position);//Toggle the selection

        boolean hasCheckedItems = adapter.getSelectedCount() > 0;//Check if any items are already selected or not


        if (hasCheckedItems && mActionMode == null)
            // there are some selected items, start the actionMode
            mActionMode = ((AppCompatActivity) getActivity())
                    .startSupportActionMode(new Toolbar_ActionMode_Callback(
                            getActivity(),adapter)
                    );
        else if (!hasCheckedItems && mActionMode != null)
            // there no selected items, finish the actionMode
            mActionMode.finish();

        if (mActionMode != null)
            //set action mode title on item selection
            mActionMode.setTitle(String.valueOf(adapter.getSelectedCount()) + " selected");

    }

    //Set action mode null after use
    public void setNullToActionMode() {
        if (mActionMode != null)
            mActionMode = null;
    }

}
