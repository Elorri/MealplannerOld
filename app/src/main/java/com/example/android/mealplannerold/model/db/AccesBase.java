package com.example.android.mealplannerold.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.mealplannerold.R;
import com.example.android.mealplannerold.controller.extra.Tools;

/**
 * Created by Elorri-user on 13/05/2015.
 */
public class AccesBase extends SQLiteOpenHelper {

    private static AccesBase instance = null;
    private Context context;

    // Si je décide de la mettre à jour, il faudra changer cet attribut
    protected final static int VERSION = 1;
    // Le nom du fichier qui représente ma base
    protected final static String NAME = "mealplanner.db";


    public AccesBase(Context context) {
        super(context, NAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(com.example.android.mealplannerold.model.db.FoodDAO.QUERY_CREATION);
            db.execSQL(com.example.android.mealplannerold.model.db.FoodQuantityDAO.QUERY_CREATION);
        db.execSQL(com.example.android.mealplannerold.model.db.IngredientDAO.QUERY_CREATION);
        db.execSQL(com.example.android.mealplannerold.model.db.RecipeDAO.QUERY_CREATION);
        db.execSQL(com.example.android.mealplannerold.model.db.CalendarDayDAO.QUERY_CREATION);
        Log.e("MealPlanner", "All tables created");



        insert(db, com.example.android.mealplannerold.model.db.FoodDAO.QUERY_INSERT_WITH_ID, R.raw.foods);
        insert(db, com.example.android.mealplannerold.model.db.FoodQuantityDAO.QUERY_INSERT, R.raw.food_quantity);
        insert(db, com.example.android.mealplannerold.model.db.IngredientDAO.QUERY_INSERT, R.raw.ingredients);
        insert(db, com.example.android.mealplannerold.model.db.RecipeDAO.QUERY_INSERT, R.raw.recipes);
        //insert(db, CalendarDayDAO.QUERY_INSERT, MainActivity.R_RAW_CALENDAR_DAYS); //Pas d'insert au départ.
        Log.e("MealPlanner", "Inserts done");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public static AccesBase instance(Context context) {
        if (instance == null) instance = new AccesBase(context);
        return instance;
    }

    protected void insert(SQLiteDatabase db, String queryInsert, int ressource_reference) {
        for (String line : (new Tools(context)).loadTextFile(ressource_reference)) {
            db.execSQL(queryInsert, line.split("\\|"));
        }
    }

}