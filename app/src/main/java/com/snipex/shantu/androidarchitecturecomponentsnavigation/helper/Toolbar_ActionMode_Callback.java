package com.snipex.shantu.androidarchitecturecomponentsnavigation.helper;

import android.content.Context;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;

import com.snipex.shantu.androidarchitecturecomponentsnavigation.R;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.adapter.CityAdapter;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.database.City;
import com.snipex.shantu.androidarchitecturecomponentsnavigation.view.fragments.AddCityFragment;

import java.util.ArrayList;

import androidx.appcompat.view.ActionMode;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

public class Toolbar_ActionMode_Callback implements ActionMode.Callback {

    private Context context;
    private CityAdapter recyclerView_adapter;



    public Toolbar_ActionMode_Callback(Context context, CityAdapter recyclerView_adapter) {
        this.context = context;
        this.recyclerView_adapter = recyclerView_adapter;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.menu_add_city, menu);//Inflate the menu over action mode
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

        //Sometimes the meu will not be visible so for that we need to set their visibility manually in this method
        //So here show action menu according to SDK Levels
        if (Build.VERSION.SDK_INT < 11) {
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_save), MenuItemCompat.SHOW_AS_ACTION_NEVER);
        } else {
            menu.findItem(R.id.action_save).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:

                break;
        }
        return false;
    }


    @Override
    public void onDestroyActionMode(ActionMode mode) {

        //When action mode destroyed remove selected selections and set action mode to null

            recyclerView_adapter.removeSelection();  // remove selection
            Fragment recyclerFragment = new AddCityFragment();//Get recycler fragment
            if (recyclerFragment != null)
                ((AddCityFragment) recyclerFragment).setNullToActionMode();//Set action mode null
    }
}
