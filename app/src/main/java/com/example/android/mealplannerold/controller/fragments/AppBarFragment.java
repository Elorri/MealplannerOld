package com.example.android.mealplannerold.controller.fragments;


import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import  android.support.v4.app.Fragment;

import com.example.android.mealplannerold.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppBarFragment extends Fragment {

    private Toolbar toolbar;

    public AppBarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            View v = inflater.inflate(R.layout.fragment_app_bar, container, false);
            toolbar = (Toolbar) v.findViewById(R.id.app_bar);
            return v;
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
            return null;
        }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        try {
            super.onActivityCreated(savedInstanceState);
            ((ActionBarActivity) getActivity()).setSupportActionBar(toolbar);
            ((ActionBarActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

            NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
            drawerFragment.setUp((DrawerLayout) getActivity().findViewById(R.id.drawer_layout), toolbar);

        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }
}
