package com.example.android.mealplannerold.model;


import com.example.android.mealplannerold.controller.interfaces.Item;

/**
 * Created by Elorri-user on 02/08/2015.
 */
public class Eater implements Item {
    int avatar;
    String name;

    public Eater(int avatar, String name){
        this.avatar=avatar;
        this.name=name;
    }


    @Override
    public int getIconId() {
        return avatar;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getShortDesc() {
        return null;
    }
}
