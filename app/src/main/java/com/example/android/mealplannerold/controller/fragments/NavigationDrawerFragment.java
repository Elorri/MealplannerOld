package com.example.android.mealplannerold.controller.fragments;


import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import com.example.android.mealplannerold.R;
import com.example.android.mealplannerold.controller.adapters.NavigationDrawerItemAdapter;
import com.example.android.mealplannerold.controller.interfaces.Item;
import com.example.android.mealplannerold.model.Eater;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements NavigationDrawerItemAdapter.ClickListener {
    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    RecyclerView mRecyclerView;

    NavigationDrawerItemAdapter itemAdapter;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
            mRecyclerView = (RecyclerView) layout.findViewById(R.id.drawer_menu);
            itemAdapter = new NavigationDrawerItemAdapter(getActivity(),getData(), this);
            mRecyclerView.setAdapter(itemAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            return layout;
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
            return null;
        }
    }

    public static List<Item> getData() {
        List<Item> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_action_about, R.drawable.ic_action_cloud, R.drawable.ic_action_camera, R.drawable.ic_action_group};
        //String[] titles=getResources().getStringArray(R.array.drawer_list);
        String[] titles={"Pierre", "Paul", "Jacques", "Toto"};

        for(int i=0;i<icons.length&&i<titles.length;i++){
            Item current=new Eater(icons[i],titles[i]);
            data.add(current);
        }
        return data;
    }


    @Override
    public void itemClicked(View view, int position) {
        Toast.makeText(getActivity(), "in NavigationDrawerFragment itemClicked", Toast.LENGTH_SHORT).show();
    }


    public void setUp(DrawerLayout drawerLayout, Toolbar toolbar) {
        this.mDrawerLayout = drawerLayout;
        this.mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
                getActivity().invalidateOptionsMenu();
            }

            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
                getActivity().invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);


        //Car la methode syncState() doit normalement etre dans le onPostCreate de l'activit�. Comme onPostCreate n'existe pas pour un fragment on cr�e un thread.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }
}
