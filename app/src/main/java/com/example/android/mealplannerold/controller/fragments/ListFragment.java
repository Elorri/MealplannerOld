package com.example.android.mealplannerold.controller.fragments;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;

import com.example.android.mealplannerold.R;
import com.example.android.mealplannerold.controller.adapters.AppItemAdapter;
import com.example.android.mealplannerold.controller.interfaces.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.invalidateOptionsMenu;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements AppItemAdapter.ClickListener {
    List<Item> dataList;


    RecyclerView mRecyclerView;
    AppItemAdapter appItemAdapter;


    Boolean showItemActions = false;
    MenuItem help;
    MenuItem action_search;
    MenuItem analysis;
    MenuItem edit;
    MenuItem delete;
    MenuItem shopping_list;

    public static ListFragment getInstance(StateAndBehaviour stateAndBehavior) {
        try {
            ListFragment listFragment = new ListFragment();
            Bundle args = new Bundle();
            args.putSerializable("stateAndBehavior", stateAndBehavior);
            listFragment.setArguments(args);
            return listFragment;
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
            return null;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {

            StateAndBehaviour stateAndBehavior = (StateAndBehaviour) getArguments().get("stateAndBehavior");
            this.dataList = stateAndBehavior.getDataList();

            View layout = inflater.inflate(R.layout.fragment_list_search, container, false);
            mRecyclerView = (RecyclerView) layout.findViewById(R.id.list);


            return layout;
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
            return null;
        }

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        try {
            super.onViewCreated(view, savedInstanceState);

            setHasOptionsMenu(true);
            appItemAdapter = new AppItemAdapter(getActivity(), dataList, this);
            mRecyclerView.setAdapter(appItemAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


//            setHasOptionsMenu(true);
//            appItemAdapter = new AppItemAdapter(getActivity(), dataList, this);
//            mRecyclerView.setAdapter(appItemAdapter);
//            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));





        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }

    }

    private void addFragmentToBackStack() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        //Toast.makeText(getActivity(), "ItemActions added to backstack", Toast.LENGTH_SHORT).show();
        ft.addToBackStack("ItemActions");
        ft.commit();
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d("MealPlanner", "ListFragment onPause");
    }

    @Override
    public void itemClicked(View view, int position) {
        try {
            //Toast.makeText(getActivity(), "item " + position + " has been clicked", Toast.LENGTH_SHORT).show();
            updateActionBar();

        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }

    private void updateActionBar() {

        toggleShowItemAction();
        invalidateOptionsMenu(getActivity());
        ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.accent_pressed)));
        //actionBar.setTitle("");
        //addFragmentToBackStack();
    }

    public boolean toggleShowItemAction(){
        //Toast.makeText(getActivity(), "before toggle showItemActions "+showItemActions, Toast.LENGTH_SHORT).show();
        showItemActions=!showItemActions;
        return showItemActions;
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        try {
            super.onPrepareOptionsMenu(menu);
            help.setVisible(!showItemActions);
            action_search.setVisible(!showItemActions);
            analysis.setVisible(showItemActions);
            edit.setVisible(showItemActions);
            delete.setVisible(showItemActions);
            shopping_list.setVisible(showItemActions);
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        try {
            inflater.inflate(R.menu.menu_item_search, menu);
            help = menu.findItem(R.id.help);
            action_search = menu.findItem(R.id.action_search);
            analysis = menu.findItem(R.id.analysis);
            edit = menu.findItem(R.id.edit);
            delete = menu.findItem(R.id.delete);
            shopping_list = menu.findItem(R.id.shopping_list);

            final SearchView searchView = (SearchView) MenuItemCompat.getActionView(action_search);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    //Toast.makeText(getActivity(), "query has been submitted", Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    //Toast.makeText(getActivity(), "text query has changed", Toast.LENGTH_SHORT).show();
                    final List<Item> filteredModelList = filter(dataList, query);
                    appItemAdapter.animateTo(filteredModelList);
                    mRecyclerView.scrollToPosition(0);
                    return true;
                }

                private List<Item> filter(List<Item> models, String query) {
                    query = query.toLowerCase();

                    final List<Item> filteredModelList = new ArrayList<>();
                    for (Item model : models) {
                        final String text = model.getTitle().toLowerCase();
                        if (text.contains(query)) {
                            filteredModelList.add(model);
                        }
                    }
                    return filteredModelList;
                }
            });


        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }


    public static abstract class StateAndBehaviour<T extends Item> implements Serializable {
        List<T> dataList;

        protected StateAndBehaviour(List<T> dataList) {
            this.dataList=dataList;
        }
        public List<T> getDataList(){
            return dataList;
        }
    }

}
