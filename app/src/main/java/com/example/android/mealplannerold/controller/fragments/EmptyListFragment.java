package com.example.android.mealplannerold.controller.fragments;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.mealplannerold.R;

/**
 * Created by Elorri-user on 20/07/2015.
 */
public class EmptyListFragment extends Fragment {

    int list_name;


    public EmptyListFragment() {
        // Required empty public constructor
    }

    public static EmptyListFragment getInstance(int list_name) {
        try {
            EmptyListFragment emptyListFragment = new EmptyListFragment();
            Bundle args = new Bundle();
            args.putSerializable("list_name", list_name);
            emptyListFragment.setArguments(args);
            return emptyListFragment;
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
            return null;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            View layout = inflater.inflate(R.layout.fragment_empty_list, container, false);
            TextView t=(TextView)layout.findViewById(R.id.no_list);
            this.list_name =  (Integer)getArguments().get("list_name");
            t.setText(getString(R.string.noListMsg_start)+ " " +getString(list_name)+" "+getString(R.string.noListMsg_end));
            return layout;
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
            return null;
        }
    }


    @Override
    public void onPause() {
        try{
        super.onPause();
        Log.d("MealPlanner", "EmptyListFragment onPause");
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }
}

