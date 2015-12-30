package com.example.android.mealplannerold.model.db;

import android.database.sqlite.SQLiteDatabase;

import com.example.android.mealplannerold.controller.activities.MainActivity;

/**
 * Created by Elorri-user on 20/05/2015.
 */
public class DAO {
    protected static SQLiteDatabase db = null;

    MainActivity mainActivity;
    public DAO(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
        if (db == null) {
            db = (AccesBase.instance(mainActivity)).getWritableDatabase();
        }
    }


}
