package com.example.android.mealplannerold.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Elorri on 30/12/2015.
 */
public class DAO {
    protected static SQLiteDatabase db = null;


    public DAO(Context context) {
        if (db == null) {
            db = (AccesBase.instance(context)).getWritableDatabase();
        }
    }


}