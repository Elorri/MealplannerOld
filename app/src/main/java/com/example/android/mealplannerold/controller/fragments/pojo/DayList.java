package com.example.android.mealplannerold.controller.fragments.pojo;


import com.example.android.mealplannerold.controller.fragments.ListFragment;
import com.example.android.mealplannerold.controller.interfaces.Item;

import java.util.List;


/**
 * Created by Elorri-user on 22/07/2015.
 */
public class DayList<T extends Item> extends ListFragment.StateAndBehaviour {

    public DayList(List<T> dataList) {
        super(dataList);
    }

}
