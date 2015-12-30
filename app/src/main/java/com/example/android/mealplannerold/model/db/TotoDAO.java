package com.example.android.mealplannerold.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Elorri on 30/12/2015.
 */
public class TotoDAO {
    private static SQLiteDatabase db;

    public TotoDAO(Context context){
        db=(AccesBase.instance(context)).getWritableDatabase();
    }
}
