package com.example.android.mealplannerold.controller.adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.android.mealplannerold.controller.activities.MainActivity;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

import com.example.android.mealplannerold.R;


/**
 * Created by Elorri-user on 20/07/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    Activity activity;


    public ViewPagerAdapter(FragmentManager fragmentManager, Activity activity) {
        super(fragmentManager);
        this.activity = activity;
    }


    @Override
    public Fragment getItem(int position) {
        Log.d("MealPlanner", "in getItem"+position);
        return ((MainActivity)activity).onChangeTabsFragment(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return activity.getResources().getStringArray(R.array.tabs)[position];
    }

    public MaterialTabHost addTabs(MaterialTabHost tabHost, MaterialTabListener materialTabListener) {
        for (int i = 0; i < getCount(); i++) {
            tabHost.addTab(tabHost.newTab().setText(getPageTitle(i)).setTabListener(materialTabListener));
        }
        return tabHost;
    }
}
